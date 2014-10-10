// Openbravo POS is a point of sales application designed for touch screens.
// http://www.openbravo.com/product/pos
// Copyright (c) 2007 openTrends Solucions i Sistemes, S.L
// Modified by Openbravo SL on March 22, 2007
// These modifications are copyright Openbravo SL
// Author/s: A. Romero
// You may contact Openbravo SL at: http://www.openbravo.com
//
// Contributor: Redhuan D. Oon - ActiveMQ XML read from MClient.readmessage()
// Please refer to notes at http://red1.org/adempiere/viewtopic.php?f=29&t=1356
//
// This file is part of Openbravo POS.
//
// Openbravo POS is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Openbravo POS is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with Openbravo POS. If not, see <http://www.gnu.org/licenses/>.
package com.openbravo.possync;

import com.openbravo.activemq.ActiveMQClient;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.ProcessAction;
import com.openbravo.pos.inventory.MovementReason;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.ws.externalsales.Category;
import com.openbravo.ws.externalsales.Product;
import com.openbravo.ws.externalsales.ProductPlus;
import com.openbravo.ws.externalsales.Tax;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;
import org.compiere.model.MBPartner;
import org.compiere.model.MLocation;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MUser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProductsQueueSync implements ProcessAction {

    private final DataLogicSystem dlsystem;
    private final DataLogicIntegration dlintegration;
    private final DataLogicSales dlsales;
    private final String warehouse;
    private ExternalSalesHelper externalsales;
    private String appUser = "";

    /**
     * Creates a new instance of ProductsSync
     *
     * @param dlsystem
     * @param dlintegration
     * @param warehouse
     * @param dlsales
     */
    public ProductsQueueSync(DataLogicSystem dlsystem, DataLogicIntegration dlintegration, DataLogicSales dlsales, String warehouse, String appUser) {
        this.dlsystem = dlsystem;
        this.dlintegration = dlintegration;
        this.dlsales = dlsales;
        this.warehouse = warehouse;
        externalsales = null;
    }

    @Override
    public MessageInf execute() throws BasicException {
        try {
            if (externalsales == null) {
                externalsales = new ExternalSalesHelper(dlsystem);
            }
//red1 using XML string from ActiveMQ -- automatically check either path (so do not use same if u want to synch customers also)
//red1 convert string to models deprecated approach below
            ActiveMQClient mqClient = new ActiveMQClient(externalsales.getActivemqBrokerUrl(), externalsales.getActivemqUsername(), externalsales.getActivemqPassword());

            if (!mqClient.init()) {
                return new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.exception"));
            }

            int productsSynch = 0;
            int customersSynch = 0;
            
            System.out.println("Yeea here :!!!!! ");
            
            ArrayList<Message> productsMessageList = mqClient.consumeAllMessages(externalsales.getM_iERPPos() + externalsales.getProductsQueue());
            ArrayList<ProductPlus[]> productsList = new ArrayList<>();
            for (int i = 0; i < productsMessageList.size(); ++i)
            {
                System.out.println("xml : " + ((TextMessage)productsMessageList.get(i)).getText());
                productsList.add(importQueue2Products(((TextMessage)productsMessageList.get(i)).getText()));
            }
            
            if (!productsList.isEmpty()) {
                for (ProductPlus[] products : productsList) {
                    //dlintegration.syncProductsBefore();
                    Date now = new Date();
                    for (Product product : products) {
                        if (product == null) {
                            break;
                        }
// Synchonization of taxcategories
                        TaxCategoryInfo tc = new TaxCategoryInfo(product.getTax().getId(), product.getTax().getName());
                        dlintegration.syncTaxCategory(tc);
// Synchonization of taxes
                        TaxInfo t = new TaxInfo(product.getTax().getId(), product.getTax().getName(), tc.getID(), null, null, product.getTax().getPercentage() / 100, false, 0);
                        dlintegration.syncTax(t);
// Synchonization of categories
                        CategoryInfo c = new CategoryInfo(product.getCategory().getId(), product.getCategory().getName(), null, product.getCategory().getDescription(), true);
                        dlintegration.syncCategory(c);
// Synchonization of products
                        ProductInfoExt p = new ProductInfoExt();
                        p.setID(product.getId());
                        p.setReference(product.getId());
                        p.setCode(product.getEan() == null || product.getEan().equals("") ? product.getId() : product.getEan());
                        p.setName(product.getName());
                        p.setCom(false);
                        p.setScale(false);
                        p.setPriceBuy(product.getPurchasePrice());
                        p.setPriceSell(product.getListPrice());
                        p.setCategoryID(c.getID());
                        p.setTaxCategoryID(tc.getID());
                        p.setImage(ImageUtils.readImage(product.getImageUrl()));
                        dlintegration.syncProduct(p);
// Synchronization of stock
                        if (product instanceof ProductPlus) {
                            ProductPlus productplus = (ProductPlus) product;
                            double diff = productplus.getQtyonhand() - dlsales.findProductStock(warehouse, p.getID(), null);
                            Object[] diary = new Object[9];
                            diary[0] = UUID.randomUUID().toString();
                            diary[1] = now;
                            diary[2] = diff > 0.0
                                    ? MovementReason.IN_MOVEMENT.getKey()
                                    : MovementReason.OUT_MOVEMENT.getKey();
                            diary[3] = warehouse;
                            diary[4] = p.getID();
                            diary[5] = p.getAttributeSetID();
                            diary[6] = diff;
                            diary[7] = p.getPriceBuy();
                            diary[8] = appUser;
                            dlsales.getStockDiaryInsert().exec(diary);
                            ++productsSynch;
                        }
                    }
                }
// datalogic.syncProductsAfter();
            }

            ArrayList<Message> customersMessageList = mqClient.consumeAllMessages(externalsales.getM_iERPPos() + externalsales.getCustomersQueue());
            ArrayList<CustomerInfoExt[]> customersList = new ArrayList<>();
            for (int i = 0; i < customersMessageList.size(); ++i)
            {
                customersList.add(importQueue2Customers(((TextMessage)customersMessageList.get(i)).getText()));
            }
            
            if (!customersList.isEmpty()) {
                dlintegration.syncCustomersBefore();
                for (CustomerInfoExt[] customers : customersList)
                {
                    for (CustomerInfoExt customer : customers) {
                        // Synch this customer
                        dlintegration.syncCustomer(customer);
                        ++customersSynch;
                    }
                }
            }

            if (productsList.isEmpty() && customersList.isEmpty()) {
                return new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.zeroproducts"));
            } else {
                return new MessageInf(MessageInf.SGN_SUCCESS, AppLocal.getIntString("message.syncproductsok"), AppLocal.getIntString("message.syncproductsinfo", productsSynch, customersSynch));
            }
        } catch (ServiceException | JMSException e) {
            throw new BasicException(AppLocal.getIntString("message.serviceexception"), e);
        } catch (MalformedURLException e) {
            throw new BasicException(AppLocal.getIntString("message.malformedurlexception"), e);
        } catch (SAXException | IOException | ParserConfigurationException e) {
        }
        return new MessageInf(MessageInf.SGN_CAUTION, AppLocal.getIntString("message.zerocustomers"));
    }

    private ProductPlus[] importQueue2Products(String productsXML) throws SAXException, IOException, ParserConfigurationException {

        if (productsXML.equals("")) {
            return new ProductPlus[0];
        }
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new ByteArrayInputStream(productsXML.getBytes()));
        Element docEle = doc.getDocumentElement();
        NodeList records = docEle.getElementsByTagName("detail");

        ProductPlus[] productList = new ProductPlus[records.getLength()];
        for (int i = 0; i < records.getLength(); i++) {

            //check if XML type is about Products
            if (!records.item(i).getFirstChild().getTextContent().equals(MProduct.Table_Name)) {
                break;
            }
            NodeList details = records.item(i).getChildNodes();
            ProductPlus product = new ProductPlus();

            Tax newtax = new Tax();
            newtax.setPercentage(0.0d);

            Category cate = new Category();

            for (int j = 0; j < details.getLength(); j++) {
                Node n = details.item(j);
                String column = n.getNodeName();
                if (column.equals("ProductName")) {
                    product.setName(n.getTextContent());
                } else if (column.equals(MProduct.COLUMNNAME_M_Product_Category_ID)) {
                    cate.setId(n.getTextContent());
                    product.setCategory(cate);
                } else if (column.equals("CategoryName")) {
                    cate.setName(n.getTextContent());
                    product.setCategory(cate);
                } else if (column.equals(MProduct.COLUMNNAME_M_Product_ID)) {
                    product.setId(n.getTextContent());
                } else if (column.equals(MProduct.COLUMNNAME_C_TaxCategory_ID)) {
                    newtax.setId(n.getTextContent());
                    product.setTax(newtax);
                } else if (column.equals("TaxName")) {
                    newtax.setName(n.getTextContent());
                    product.setTax(newtax);
                } else if (column.equals("QtyOnHand")) {
                    product.setQtyonhand(Double.parseDouble(n.getTextContent()));
                } else if (column.equals(MProductPrice.COLUMNNAME_PriceList)) {
                    product.setListPrice(Double.parseDouble(n.getTextContent()));
                } else if (column.equals(MProductPrice.COLUMNNAME_PriceLimit)) {
                    product.setPurchasePrice(Double.parseDouble(n.getTextContent()));
                } else if (column.equals(MProduct.COLUMNNAME_UPC)) {
                    product.setEan(n.getTextContent());
                }
            }
            productList[i] = product;
        }
        
        return productList;
    }

    private CustomerInfoExt[] importQueue2Customers(String customersXML) throws ParserConfigurationException, SAXException, IOException {
        if (customersXML.equals("")) {
            return new CustomerInfoExt[0];
        }
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new ByteArrayInputStream(customersXML.getBytes()));

        Element docEle = doc.getDocumentElement();
        NodeList records = docEle.getElementsByTagName("detail");
        CustomerInfoExt[] customer = new CustomerInfoExt[records.getLength()];

        for (int i = 0; i < records.getLength(); i++) {
            if (!records.item(i).getFirstChild().getTextContent().equals(MBPartner.Table_Name)) {
                continue;
            }
            customer[i] = new CustomerInfoExt("");
            NodeList details = records.item(i).getChildNodes();
            for (int j = 0; j < details.getLength(); j++) {
                Node n = details.item(j);
                String column = n.getNodeName();
                switch (column)
                {
                    case "CustomerName" :
                        customer[i].setName(n.getTextContent());
                        break;
                    case MBPartner.COLUMNNAME_Value :
                        customer[i].setSearchkey(n.getTextContent());
                        break;
                    case MBPartner.COLUMNNAME_C_BPartner_ID : 
                        customer[i].setId(n.getTextContent());
                        break;
                    case MBPartner.COLUMNNAME_Description :
                        customer[i].setNotes(n.getTextContent());
                        break;
                    case MBPartner.COLUMNNAME_TaxID :
                        customer[i].setTaxid(n.getTextContent());
                        break;
                    case MLocation.COLUMNNAME_Address1 : 
                        customer[i].setAddress(n.getTextContent());
                        break;
                    case MLocation.COLUMNNAME_Address2 :
                        customer[i].setAddress2(n.getTextContent());
                        break;
                    case MLocation.COLUMNNAME_City :
                        customer[i].setCity(n.getTextContent());
                        break;
                    case "RegionName" :
                        customer[i].setRegion(n.getTextContent());
                        break;
                    case "Country" :
                        customer[i].setCountry(n.getTextContent());
                        break;
                    case "Image" :
                        customer[i].setImage(n.getTextContent());
                        break;
                    case MUser.COLUMNNAME_EMail :
                        customer[i].setEmail(n.getTextContent());
                        break;
                    case MUser.COLUMNNAME_Fax :
                        customer[i].setFax(n.getTextContent());
                        break;
                    case MUser.COLUMNNAME_Name :
                        customer[i].setName(n.getTextContent());
                        break;
                    case MUser.COLUMNNAME_Phone :
                        customer[i].setPhone(n.getTextContent());
                        break;
                    case MUser.COLUMNNAME_Phone2 :
                        customer[i].setPhone2(n.getTextContent());
                        break;
                }
            }
        }
        return customer;
    }
}
