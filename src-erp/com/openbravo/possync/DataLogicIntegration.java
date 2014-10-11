//    Openbravo POS is a point of sales application designed for touch screens.
//    http://www.openbravo.com/product/pos
//    Copyright (c) 2007 openTrends Solucions i Sistemes, S.L
//    Modified by Openbravo SL on March 22, 2007
//    These modifications are copyright Openbravo SL
//    Author/s: A. Romero
//    You may contact Openbravo SL at: http://www.openbravo.com
//
//    This file is part of Openbravo POS.
//
//    Openbravo POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Openbravo POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Openbravo POS.  If not, see <http://www.gnu.org/licenses/>.
package com.openbravo.possync;

import java.util.List;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;

/**
 *
 * @author adrianromero Created on 5 de marzo de 2007, 19:56
 *
 */
public class DataLogicIntegration extends BeanFactoryDataSingle {

    protected Session s;

    /**
     * Creates a new instance of DataLogicIntegration
     */
    public DataLogicIntegration() {
    }

    public void init(Session s) {
        this.s = s;
    }

    public void syncCustomersBefore() throws BasicException {
        new StaticSentence(s, "UPDATE CUSTOMERS SET VISIBLE = " + s.DB.FALSE())
                .exec();
    }

    public void syncCustomer(final CustomerInfoExt customer)
            throws BasicException {

        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
				// Sync the Customer in a transaction

                // Try to update
                if (new PreparedSentence(s,
                        "UPDATE CUSTOMERS SET SEARCHKEY = ?, NAME = ?, NOTES = ?, TAXID = ?, ADDRESS = ?, ADDRESS2 = ?, CITY = ?, REGION = ?, "
                                + "COUNTRY = ?, EMAIL = ?, PHONE = ?, PHONE2 = ?, FAX = ?, IMAGE = ?, VISIBLE = "
                        + s.DB.TRUE() + " WHERE ID = ?",
                        SerializerWriteParams.INSTANCE).exec(new DataParams() {
                            public void writeValues() throws BasicException {
                                setString(1, customer.getSearchkey());
                                setString(2, customer.getName());
                                setString(3, customer.getNotes());
                                setString(4, customer.getTaxid());
                                setString(5, customer.getAddress());
                                setString(6, customer.getAddress2());
                                setString(7, customer.getCity());
                                setString(8, customer.getRegion());
                                setString(9, customer.getCountry());
                                setString(10, customer.getEmail());
                                setString(11, customer.getPhone());
                                setString(12, customer.getPhone2());
                                setString(13, customer.getFax());
                                setString(14, customer.getImage());
                                setString(15, customer.getId());
                            }
                        }) == 0) {

                    // If not updated, try to insert
                    new PreparedSentence(
                        s,
                        "INSERT INTO CUSTOMERS(ID, SEARCHKEY, NAME, NOTES, TAXID, ADDRESS, ADDRESS2, CITY, REGION, "
                            + "COUNTRY, EMAIL, PHONE, PHONE2, FAX, IMAGE, VISIBLE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                        + s.DB.TRUE() + ")",
                        SerializerWriteParams.INSTANCE)
                        .exec(new DataParams() {
                            public void writeValues() throws BasicException {
                                setString(1, customer.getId());
                                setString(2, customer.getSearchkey());
                                setString(3, customer.getName());
                                setString(4, customer.getNotes());
                                setString(5, customer.getTaxid());
                                setString(6, customer.getAddress());
                                setString(7, customer.getAddress2());
                                setString(8, customer.getCity());
                                setString(9, customer.getRegion());
                                setString(10, customer.getCountry());
                                setString(11, customer.getEmail());
                                setString(12, customer.getPhone());
                                setString(13, customer.getPhone2());
                                setString(14, customer.getFax());
                                setString(15, customer.getImage());
                            }
                        });
                }

                return null;
            }
        };
        t.execute();
    }

	// When you press pos synch for the same queue, it will produce an error
    // previous version delete from products_cat
    public void syncProductsBefore() throws BasicException {
        new StaticSentence(s, "").exec();
    }

    public void syncTaxCategory(final TaxCategoryInfo taxcat)
            throws BasicException {

        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
				// Sync the Tax in a transaction

                // Try to update
                if (new PreparedSentence(s,
                        "UPDATE TAXCATEGORIES SET NAME = ?  WHERE ID = ?",
                        SerializerWriteParams.INSTANCE).exec(new DataParams() {
                            public void writeValues() throws BasicException {
                                setString(1, taxcat.getName());
                                setString(2, taxcat.getID());
                            }
                        }) == 0) {

                    // If not updated, try to insert
                    new PreparedSentence(
                            s,
                            "INSERT INTO TAXCATEGORIES(ID, NAME) VALUES (?, ?)",
                            SerializerWriteParams.INSTANCE)
                            .exec(new DataParams() {
                                public void writeValues() throws BasicException {
                                    setString(1, taxcat.getID());
                                    setString(2, taxcat.getName());
                                }
                            });
                }

                return null;
            }
        };
        t.execute();
    }

    public void syncTax(final TaxInfo tax) throws BasicException {

        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
				// Sync the Tax in a transaction

                // Try to update
                if (new PreparedSentence(
                        s,
                        "UPDATE TAXES SET NAME = ?, CATEGORY = ?, CUSTCATEGORY = ?, PARENTID = ?, RATE = ?, RATECASCADE = ? WHERE ID = ?",
                        SerializerWriteParams.INSTANCE).exec(new DataParams() {
                            public void writeValues() throws BasicException {
                                setString(1, tax.getName());
                                setString(2, tax.getTaxCategoryID());
                                setString(3, tax.getTaxCustCategoryID());
                                setString(4, tax.getParentID());
                                setDouble(5, tax.getRate());
                                setBoolean(6, tax.isCascade());
                                setString(7, tax.getId());
                            }
                        }) == 0) {

                    // If not updated, try to insert
                    new PreparedSentence(
                            s,
                            "INSERT INTO TAXES(ID, NAME, CATEGORY, CUSTCATEGORY, PARENTID, RATE, RATECASCADE) VALUES (?, ?, ?, ?, ?, ?, ?)",
                            SerializerWriteParams.INSTANCE)
                            .exec(new DataParams() {
                                public void writeValues() throws BasicException {
                                    setString(1, tax.getId());
                                    setString(2, tax.getName());
                                    setString(3, tax.getTaxCategoryID());
                                    setString(4, tax.getTaxCustCategoryID());
                                    setString(5, tax.getParentID());
                                    setDouble(6, tax.getRate());
                                    setBoolean(7, tax.isCascade());
                                }
                            });
                }

                return null;
            }
        };
        t.execute();
    }

    public void syncCategory(final CategoryInfo cat) throws BasicException {

        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
				// Sync the Category in a transaction

                // Try to update
                if (new PreparedSentence(
                        s,
                        "UPDATE CATEGORIES SET NAME = ?, IMAGE = ? WHERE ID = ?",
                        SerializerWriteParams.INSTANCE).exec(new DataParams() {
                            public void writeValues() throws BasicException {
                                setString(1, cat.getName());
                                setBytes(2, ImageUtils.writeImage(cat.getImage()));
                                setString(3, cat.getID());
                            }
                        }) == 0) {

                    // If not updated, try to insert
                    new PreparedSentence(
                            s,
                            "INSERT INTO CATEGORIES(ID, NAME, IMAGE) VALUES (?, ?, ?)",
                            //"INSERT INTO  CATEGORIES(ID,NAME, IMAGE) SELECT  ?,?,? WHERE NOT EXISTS (SELECT (?,?,?) FROM CATEGORIES) ",
                            SerializerWriteParams.INSTANCE)
                            .exec(new DataParams() {
                                public void writeValues() throws BasicException {
                                    setString(1, cat.getID());
                                    setString(2, cat.getName());
                                    setBytes(3, ImageUtils.writeImage(cat
                                                    .getImage()));
                                }
                            });
                }
                return null;
            }
        };
        t.execute();
    }

    public void syncProduct(final ProductInfoExt prod) throws BasicException {

        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
				// Sync the Product in a transaction

                // Try to update
                // Ing Tatioti Mbogning Raoul(tatiotir) add column DISPLAY
                if (new PreparedSentence(
                        s,
                        "UPDATE PRODUCTS SET REFERENCE = ?, CODE = ?, NAME = ?, PRICEBUY = ?, PRICESELL = ?, CATEGORY = ?, TAXCAT = ?, IMAGE = ?, DISPLAY = ? WHERE ID = ?",
                        SerializerWriteParams.INSTANCE).exec(new DataParams() {
                            public void writeValues() throws BasicException {
                                setString(1, prod.getReference());
                                setString(2, prod.getCode());
                                setString(3, prod.getName());
						// setBoolean(x, p.isCom());
                                // setBoolean(x, p.isScale());
                                setDouble(4, prod.getPriceBuy());
                                setDouble(5, prod.getPriceSell());
                                setString(6, prod.getCategoryID());
                                setString(7, prod.getTaxCategoryID());
                                setBytes(8, ImageUtils.writeImage(prod.getImage()));
                                setString(9, "<html>" + prod.getName());
						// setDouble(x, 0.0);
                                // setDouble(x, 0.0);
                                setString(10, prod.getID());
                            }
                        }) == 0) {

                    // If not updated, try to insert
                    // Ing Tatioti Mbogning Raoul(tatiotir) add column DISPLAY
                    new PreparedSentence(
                            s,
                            "INSERT INTO PRODUCTS (ID, REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, CATEGORY, TAXCAT, IMAGE, STOCKCOST, STOCKVOLUME, DISPLAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                            SerializerWriteParams.INSTANCE)
                            .exec(new DataParams() {
                                public void writeValues() throws BasicException {
                                    setString(1, prod.getID());
                                    setString(2, prod.getReference());
                                    setString(3, prod.getCode());
                                    setString(4, prod.getName());
                                    setBoolean(5, prod.isCom());
                                    setBoolean(6, prod.isScale());
                                    setDouble(7, prod.getPriceBuy());
                                    setDouble(8, prod.getPriceSell());
                                    setString(9, prod.getCategoryID());
                                    setString(10, prod.getTaxCategoryID());
                                    setBytes(11, ImageUtils.writeImage(prod.getImage()));
                                    setDouble(12, 0.0);
                                    setDouble(13, 0.0);
                                    setString(14, "<html>" + prod.getName());
                                }
                            });
                }
                
                // Insert in catalog
                new StaticSentence(
                        s,
                        // Tatioti Mbogning Raoul - Insert into the product catalog if the products aren't already there,
                        "INSERT IGNORE INTO PRODUCTS_CAT(PRODUCT,CATORDER) SELECT ?, NULL FROM PRODUCTS_CAT WHERE NOT EXISTS (SELECT 1 FROM PRODUCTS_CAT WHERE PRODUCT = ?)",
                        // "INSERT INTO PRODUCTS_CAT (PRODUCT,CATORDER) VALUES (?, NULL)",
                        new SerializerWriteBasic(Datas.STRING, Datas.STRING)
                ).exec(prod.getID(), prod.getID());

                return null;
            }
        };
        t.execute();
    }

	// red1 - TicketInfo check for DataRead.getDataField().length > 9
    // (R.ATTRIBUTES, inserted, c.taxid jumps over)
    public List getTickets() throws BasicException {
        return new PreparedSentence(
                s,
                "SELECT T.ID, T.TICKETTYPE, T.TICKETID, R.DATENEW, R.MONEY, R.ATTRIBUTES, P.ID, P.NAME, C.ID, C.SEARCHKEY, C.NAME, C.TAXID FROM RECEIPTS R JOIN TICKETS T ON R.ID = T.ID LEFT OUTER JOIN PEOPLE P ON T.PERSON = P.ID LEFT OUTER JOIN CUSTOMERS C ON T.CUSTOMER = C.ID WHERE (T.TICKETTYPE = 0 OR T.TICKETTYPE = 1) AND T.STATUS = 0",
                null, new SerializerReadClass(TicketInfo.class)).list();
    }

    public List getTicketLines(final String ticket) throws BasicException {
        return new PreparedSentence(
                s,
                "SELECT L.TICKET, L.LINE, L.PRODUCT, L.ATTRIBUTESETINSTANCE_ID, L.UNITS, L.PRICE, T.ID, T.NAME, T.CATEGORY, T.CUSTCATEGORY, T.PARENTID, T.RATE, T.RATECASCADE, T.RATEORDER, L.ATTRIBUTES "
                + "FROM TICKETLINES L, TAXES T WHERE L.TAXID = T.ID AND L.TICKET = ? ORDER BY L.LINE" // red1 ,
                // "SELECT L.TICKET, L.LINE, L.PRODUCT, L.UNITS, L.PRICE, T.ID, T.NAME, T.CATEGORY, T.CUSTCATEGORY, T.PARENTID, T.RATE, T.RATECASCADE, T.RATEORDER, L.ATTRIBUTES "
                // +
                // red1
                // "FROM TICKETLINES L, TAXES T WHERE L.TAXID = T.ID AND L.TICKET = ?"
                , SerializerWriteString.INSTANCE, new SerializerReadClass(
                        TicketLineInfo.class)).list(ticket);
    }

    public List getTicketPayments(final String ticket) throws BasicException {
        return new PreparedSentence(s,
                "SELECT TOTAL, PAYMENT FROM PAYMENTS WHERE RECEIPT = ?",
                SerializerWriteString.INSTANCE, new SerializerRead() {
                    public Object readValues(DataRead dr) throws BasicException {
                        return new PaymentInfoTicket(dr.getDouble(1), dr
                                .getString(2));
                    }
                }).list(ticket);
    }

    public void execTicketUpdate() throws BasicException {
        new StaticSentence(s, "UPDATE TICKETS SET STATUS = 1 WHERE STATUS = 0")
                .exec();
    }
}
