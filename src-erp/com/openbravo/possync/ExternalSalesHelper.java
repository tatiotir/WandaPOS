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

import java.util.Properties;
import com.openbravo.basic.BasicException;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.DataLogicSystem;

public class ExternalSalesHelper {

//    private ExternalSalesImpl externalSales;
//    private WebServiceImpl externalCustomers;
//
//    private String m_sERPUser;
//    private String m_sERPPassword;
//    private String m_iERPOrg;
    private String m_iERPId;
    private String m_iERPPos;
    private String activemqBrokerUrl;
    private String activemqUsername;
    private String activemqPassword;
    private String ordersQueue;
    private String productsQueue;
    private String customersQueue;

    public String getActivemqBrokerUrl() {
        return activemqBrokerUrl;
    }

    public void setActivemqBrokerUrl(String activemqBrokerUrl) {
        this.activemqBrokerUrl = activemqBrokerUrl;
    }

    public String getActivemqUsername() {
        return activemqUsername;
    }

    public void setActivemqUsername(String activemqUsername) {
        this.activemqUsername = activemqUsername;
    }

    public String getActivemqPassword() {
        return activemqPassword;
    }

    public void setActivemqPassword(String activemqPassword) {
        this.activemqPassword = activemqPassword;
    }

    public String getOrdersQueue() {
        return ordersQueue;
    }

    public void setOrdersQueue(String ordersQueue) {
        this.ordersQueue = ordersQueue;
    }

    public String getProductsQueue() {
        return productsQueue;
    }

    public void setProductsQueue(String productsQueue) {
        this.productsQueue = productsQueue;
    }

    public String getCustomersQueue() {
        return customersQueue;
    }

    public void setCustomersQueue(String customersQueue) {
        this.customersQueue = customersQueue;
    }
    
    public String getM_iERPId() {
        return m_iERPId;
    }

    public void setM_iERPId(String m_iERPId) {
        this.m_iERPId = m_iERPId;
    }

    public String getM_iERPPos() {
        return m_iERPPos;
    }

    public void setM_iERPPos(String m_iERPPos) {
        this.m_iERPPos = m_iERPPos;
    }

    /**
     * Creates a new instance of WebServiceHelper
     * @param dlsystem
     * @throws com.openbravo.basic.BasicException
     */
    public ExternalSalesHelper(DataLogicSystem dlsystem) throws BasicException /*, ServiceException, MalformedURLException*/ {

        Properties prop = dlsystem.getResourceAsProperties("wandapos.properties");
        if (prop == null) {
            throw new BasicException(AppLocal.getIntString("message.propsnotdefined"));
        } else {
            String brokerUrl = prop.getProperty("activemq-brokerUrl");
            if (brokerUrl == null || brokerUrl.equals("")) {
                throw new BasicException(AppLocal.getIntString("message.urlnotdefined"));
            } else {

                // Get activeMQ paramaters and Queues
                // Add by Ing Tatioti Mbogning Raoul (tatiotir@itkamer.com || tatiotir@gmail.com)
                activemqBrokerUrl = prop.getProperty("activemq-brokerUrl");
                activemqUsername = prop.getProperty("activemq-username");
                activemqPassword = prop.getProperty("activemq-password");
                ordersQueue = prop.getProperty("orders-queue");
                productsQueue = prop.getProperty("products-queue");
                customersQueue = prop.getProperty("customers-queue");
                m_iERPPos = prop.getProperty("pos");
                m_iERPId = prop.getProperty("id");

//                url = url.trim();
//
//                // transform the URL for backwards compatibility
//                if (url.endsWith("/ExternalSales")) {
//                    url = url.substring(0, url.length() - 14);
//                }
//
//                externalSales = new ExternalSalesImplServiceLocator().getExternalSales(new URL(url + "/ExternalSales"));
//                externalCustomers = new WebServiceImplServiceLocator().getWebService(new URL(url + "/WebService"));
//
//                m_sERPUser = prop.getProperty("user");
//                m_sERPPassword = prop.getProperty("password");
//                if (m_sERPUser != null && m_sERPPassword != null && m_sERPPassword.startsWith("crypt:")) {
//                    // La clave esta encriptada.
//                    AltEncrypter cypher = new AltEncrypter("key" + m_sERPUser);
//                    m_sERPPassword = cypher.decrypt(m_sERPPassword.substring(6));
//                }
//                m_sERPPassword = getPasswordHash(m_sERPPassword);
//                m_iERPId = prop.getProperty("id");
//                m_iERPOrg = prop.getProperty("org");
            }
        }
    }

//    public Customer[] getCustomers() throws RemoteException {
//        try {
//            // 2.40 service
//            return externalCustomers.getCustomers(m_iERPId, m_sERPUser, m_sERPPassword);
//        } catch (RemoteException e) {
//            // 2.30 service not exists
//            return new Customer[0];
//        }
//    }
//
//    public Customer[] getCustomerFromActiveMQ() {
//        ActiveMQClient mqClient = new ActiveMQClient(activemqBrokerUrl, activemqUsername, activemqPassword);
//        //Consume custo
//        mqClient.consumeMessage(customersQueue);
//        return null;
//    }
//
//    public Product[] getProductsCatalogFromActiveMQ() {
//        ActiveMQClient mqClient = new ActiveMQClient(activemqBrokerUrl, activemqUsername, activemqPassword);
//        //Consume products
//        mqClient.consumeMessage(productsQueue);
//        return null;
//    }
//
//    public Product[] getProductsCatalog() throws RemoteException {
//        try {
//            // 2.40 service
//            return externalSales.getProductsPlusCatalog(m_iERPId, m_iERPOrg, m_iERPPos, m_sERPUser, m_sERPPassword);
//        } catch (RemoteException e) {
//            // 2.30 service
//            return externalSales.getProductsCatalog(m_iERPId, m_iERPOrg, m_iERPPos, m_sERPUser, m_sERPPassword);
//        }
//    }
//
//    public boolean uploadOrders(Order[] orderstoupload) throws RemoteException {
//        return externalSales.uploadOrders(m_iERPId, m_iERPOrg, m_iERPPos, orderstoupload, m_sERPUser, m_sERPPassword);
//    }
//
//    private static String getPasswordHash(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-1");
//            byte raw[] = md.digest(password.getBytes("UTF-8"));
//            return Base64Encoder.encode(raw);
//        } catch (NoSuchAlgorithmException e) {
//            return null; // never happens :-)
//        } catch (UnsupportedEncodingException e) {
//            return null; // never happens :-)
//        }
//    }
}
