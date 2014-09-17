//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2014 uniCenta
//    http://www.unicenta.com
//
//    This file is part of uniCenta oPOS
//
//    uniCenta oPOS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   uniCenta oPOS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with uniCenta oPOS.  If not, see <http://www.gnu.org/licenses/>.
package com.openbravo.pos.ticket;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.format.Formats;
import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 *
 * @author adrianromero
 *
 */
public class ProductInfoExt {

    private static final long serialVersionUID = 7587696873036L;

    /**
     *
     */
    protected String m_ID;

    /**
     *
     */
    protected String m_sRef;

    /**
     *
     */
    protected String m_sCode;

    /**
     *
     */
    protected String m_sName;

    /**
     *
     */
    protected boolean m_bCom;

    /**
     *
     */
    protected boolean m_bScale;

    /**
     *
     */
    protected double m_dPriceBuy;

    /**
     *
     */
    protected double m_dPriceSell;

    /**
     *
     */
    protected String categoryid;

    /**
     *
     */
    protected String taxcategoryid;

    /**
     *
     */
    protected String attributesetid;

    /**
     *
     */
    protected BufferedImage m_Image;

    /**
     *
     */
    protected double m_stockCost;

    /**
     *
     */
    protected double m_stockVolume;
// ADDED JG 20.12.10 - Kitchen Print

    /**
     *
     */
        protected boolean m_bKitchen;
// ADDED JG 25.06.11 - Service
    private boolean m_bService;
    
    /**
     *
     */
    protected Properties attributes;
// ADDED JG 13 Nov 12 - Display

    /**
     *
     */
        protected String m_sDisplay;
// ADDED JDL 19.12.12 - Variable price product

    /**
     *
     */
        protected boolean m_bVprice;
// ADDED JDL 09.02.13

    /**
     *
     */
        protected boolean m_bVerpatrib;
// ADDED JDL 10.04.13

    /**
     *
     */
        protected String m_sTextTip;
// ADDED JDL 25.05.13

    /**
     *
     */
        protected boolean m_bWarranty;
    
    
    /*
     * Creates new ProductInfo
     */

    /**
     *
     */
    
    public ProductInfoExt() {
        m_ID = null;
        m_sRef = "0000";
        m_sCode = "0000";
        m_sName = null;
        m_bCom = false;
        m_bScale = false;
        categoryid = null;
        taxcategoryid = null;
        attributesetid = null;
        m_dPriceBuy = 0.0;
        m_dPriceSell = 0.0;
        m_stockCost = 0.0;
        m_stockVolume = 0.0;
        m_Image = null;
// ADDED JG 20.12.10 - Kitchen Print
        m_bKitchen = false;
// **
// ADDED JG 25.06.11 - Is Service
        m_bService = false;
// **
// ADDED JG 13 Nov 12 - Display  
        m_sDisplay = null;

        attributes = new Properties();

// ADDED JDL 19.12.12 - Variable price product
        m_bVprice = false;
//        
// ADDED JDL 09.02.13 
        m_bVerpatrib = false;
// ADDED JDL 10.04.13 
        m_sTextTip = null;
// ADDED JDL 25.05.13         
        m_bWarranty = false;


    }

    /**
     *
     * @return
     */
    public final String getID() {
        return m_ID;
    }

    /**
     *
     * @param id
     */
    public final void setID(String id) {
        m_ID = id;
    }

    /**
     *
     * @return
     */
    public final String getReference() {
        return m_sRef;
    }

    /**
     *
     * @param sRef
     */
    public final void setReference(String sRef) {
        m_sRef = sRef;
    }

    /**
     *
     * @return
     */
    public final String getCode() {
        return m_sCode;
    }

    /**
     *
     * @param sCode
     */
    public final void setCode(String sCode) {
        m_sCode = sCode;
    }

    /**
     *
     * @return
     */
    public final String getName() {
        return m_sName;
    }

    /**
     *
     * @param sName
     */
    public final void setName(String sName) {
        m_sName = sName;
    }

// ADDED JG 13 Nov 12 - Display

    /**
     *
     * @return
     */
        public final String getDisplay() {
        return m_sDisplay;
    }

    /**
     *
     * @param sDisplay
     */
    public final void setDisplay(String sDisplay) {
        m_sDisplay = sDisplay;
    }
//**   

    /**
     *
     * @return
     */
    public final boolean isCom() {
        return m_bCom;
    }

    /**
     *
     * @param bValue
     */
    public final void setCom(boolean bValue) {
        m_bCom = bValue;
    }

    /**
     *
     * @return
     */
    public final boolean isScale() {
        return m_bScale;
    }

    /**
     *
     * @param bValue
     */
    public final void setScale(boolean bValue) {
        m_bScale = bValue;
    }

// ADDED JG 20.12.10 - Kitchen Print

    /**
     *
     * @return
     */
        public final boolean isKitchen() {
        return m_bKitchen;
    }

    /**
     *
     * @param bValue
     */
    public final void setKitchen(boolean bValue) {
        m_bKitchen = bValue;
    }
// **

// ADDED JG 25.06.11 - Is Service

    /**
     *
     * @return
     */
        public final boolean isService() {
        return m_bService;
    }

    /**
     *
     * @param bValue
     */
    public final void setService(boolean bValue) {
        m_bService = bValue;
    }
// **

// ADDED JDL 19.12.12 - Variable price product

    /**
     *
     * @return
     */
        public final boolean isVprice() {
        return m_bVprice;
    }

    // ADDED JDL 09.02.13 - for Chris

    /**
     *
     * @return
     */
        public final boolean isVerpatrib() {
        return m_bVerpatrib;
    }

    /**
     *
     * @return
     */
    public final String getTextTip() {
        return m_sTextTip;
    }

    /**
     *
     * @return
     */
    public final boolean getWarranty() {
        return m_bWarranty;
    }

    /**
     *
     * @param bValue
     */
    public final void setWarranty(boolean bValue) {
        m_bWarranty = bValue;
    }

    /**
     *
     * @return
     */
    public final String getCategoryID() {
        return categoryid;
    }

    /**
     *
     * @param sCategoryID
     */
    public final void setCategoryID(String sCategoryID) {
        categoryid = sCategoryID;
    }

    /**
     *
     * @return
     */
    public final String getTaxCategoryID() {
        return taxcategoryid;
    }

    /**
     *
     * @param value
     */
    public final void setTaxCategoryID(String value) {
        taxcategoryid = value;
    }

    /**
     *
     * @return
     */
    public final String getAttributeSetID() {
        return attributesetid;
    }

    /**
     *
     * @param value
     */
    public final void setAttributeSetID(String value) {
        attributesetid = value;
    }

    /**
     *
     * @return
     */
    public final double getPriceBuy() {
        return m_dPriceBuy;
    }

    /**
     *
     * @param dPrice
     */
    public final void setPriceBuy(double dPrice) {
        m_dPriceBuy = dPrice;
    }

    /**
     *
     * @return
     */
    public final double getPriceSell() {
        return m_dPriceSell;
    }

    /**
     *
     * @param dPrice
     */
    public final void setPriceSell(double dPrice) {
        m_dPriceSell = dPrice;
    }

    /**
     *
     * @return
     */
    public final double getStockVolume() {
        return m_stockVolume;
    }

    /**
     *
     * @param dStockVolume
     */
    public final void setStockVolume(double dStockVolume) {
        m_stockVolume = dStockVolume;
    }

    /**
     *
     * @return
     */
    public final double getStockCost() {
        return m_stockCost;
    }

    /**
     *
     * @param dPrice
     */
    public final void setStockCost(double dPrice) {
        m_stockCost = dPrice;
    }
      
    /**
     *
     * @param value
     */
    public final void setTextTip(String value) {
        m_sTextTip = value;
    }

    /**
     *
     * @param tax
     * @return
     */
    public final double getPriceSellTax(TaxInfo tax) {
        return m_dPriceSell * (1.0 + tax.getRate());
    }

    /**
     *
     * @return
     */
    public String printPriceSell() {
        return Formats.CURRENCY.formatValue(new Double(getPriceSell()));
    }

    /**
     *
     * @param tax
     * @return
     */
    public String printPriceSellTax(TaxInfo tax) {
        return Formats.CURRENCY.formatValue(new Double(getPriceSellTax(tax)));
    }

    /**
     *
     * @return
     */
    public BufferedImage getImage() {
        return m_Image;
    }

    /**
     *
     * @param img
     */
    public void setImage(BufferedImage img) {
        m_Image = img;
    }

    /**
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return attributes.getProperty(key);
    }

    /**
     *
     * @param key
     * @param defaultvalue
     * @return
     */
    public String getProperty(String key, String defaultvalue) {
        return attributes.getProperty(key, defaultvalue);
    }

    /**
     *
     * @param key
     * @param value
     */
    public void setProperty(String key, String value) {
        attributes.setProperty(key, value);
    }

    /**
     *
     * @return
     */
    public Properties getProperties() {
        return attributes;
    }

    /**
     *
     * @return
     */
    public static SerializerRead getSerializerRead() {
        return new SerializerRead() {
            @Override
            public Object readValues(DataRead dr) throws BasicException {
                ProductInfoExt product = new ProductInfoExt();
                product.m_ID = dr.getString(1);
                product.m_sRef = dr.getString(2);
                product.m_sCode = dr.getString(3);
                product.m_sName = dr.getString(4);
                product.m_bCom = dr.getBoolean(5).booleanValue();
                product.m_bScale = dr.getBoolean(6).booleanValue();
                product.m_dPriceBuy = dr.getDouble(7).doubleValue();
                product.m_dPriceSell = dr.getDouble(8).doubleValue();
                product.taxcategoryid = dr.getString(9);
                product.categoryid = dr.getString(10);
                product.attributesetid = dr.getString(11);
                product.m_Image = ImageUtils.readImage(dr.getBytes(12));
                product.attributes = ImageUtils.readProperties(dr.getBytes(13));
                product.m_bKitchen = dr.getBoolean(14).booleanValue();
                product.m_bService = dr.getBoolean(15).booleanValue();
// ADDED JG 13 Nov 12 - Display
                product.m_sDisplay = dr.getString(16);
// ADDED JDL 19.12.12   
                product.m_bVprice = dr.getBoolean(17).booleanValue();
// ADDED JDL 09.0.2.13 for Chris
                product.m_bVerpatrib = dr.getBoolean(18).booleanValue();
// ADDED JDL 09.04.13
                product.m_sTextTip = dr.getString(19);

// ADDED JDL 25.04.13
                product.m_bWarranty = dr.getBoolean(20).booleanValue();
                return product;
            }
        };
    }

    @Override
    public final String toString() {
        return m_sRef + " - " + m_sName;
    }
}
