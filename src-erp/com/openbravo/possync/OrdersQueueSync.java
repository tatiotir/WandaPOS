// Openbravo POS is a point of sales application designed for touch screens.
// http://www.openbravo.com/product/pos
// Copyright (c) 2007 openTrends Solucions i Sistemes, S.L
// Modified by Openbravo SL on March 22, 2007
// These modifications are copyright Openbravo SL
// Author/s: A. Romero
// You may contact Openbravo SL at: http://www.openbravo.com
//
// Contributor: Redhuan D. Oon - ActiveMQ XML string creation for MClient.sendmessage()
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
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import com.openbravo.compiere.model.I_I_Order;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.ProcessAction;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import javax.xml.stream.XMLStreamException;

public class OrdersQueueSync implements ProcessAction {

    private final DataLogicSystem dlsystem;
    private final DataLogicIntegration dlintegration;
    private ExternalSalesHelper externalsales;

    /**
     * Creates a new instance of OrdersSync
     * @param dlsystem
     * @param dlintegration
     */
    public OrdersQueueSync(DataLogicSystem dlsystem, DataLogicIntegration dlintegration) {
        this.dlsystem = dlsystem;
        this.dlintegration = dlintegration;
        externalsales = null;
    }

    @Override
    public MessageInf execute() throws BasicException {
        if (externalsales == null) {
            externalsales = new ExternalSalesHelper(dlsystem);
        }
        List<TicketInfo> ticketlist = dlintegration.getTickets();
        for (TicketInfo ticket : ticketlist) {
            ticket.setLines(dlintegration.getTicketLines(ticket.getId()));
            ticket.setPayments(dlintegration.getTicketPayments(ticket.getId()));
        }
        if (ticketlist.isEmpty()) {
            return new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.zeroorders"));
        } else {
            
            ActiveMQClient mqClient = new ActiveMQClient(externalsales.getActivemqBrokerUrl(), externalsales.getActivemqUsername(), externalsales.getActivemqPassword());
            
            if (!mqClient.init())
                return new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.serviceexception"));
            
            if (mqClient.sendMessage(transformTickets(ticketlist), externalsales.getOrdersQueue())) {
                dlintegration.execTicketUpdate();
                return new MessageInf(MessageInf.SGN_SUCCESS, AppLocal.getIntString("message.syncordersok"), AppLocal.getIntString("message.syncordersinfo", ticketlist.size()));
            }
            return new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.exception"));
        }
    }

    private String transformTickets(List<TicketInfo> ticketlist) {
//red1 - START XML inception for ActiveMQ
        try {
            StringWriter res = new StringWriter();
            XMLStreamWriter writer = XMLOutputFactory.newInstance()
                    .createXMLStreamWriter(res);
            writer.writeStartDocument();
            writer.writeStartElement("entityDetail");
            writer.writeStartElement("type");
            writer.writeCharacters(I_I_Order.Table_Name);
            writer.writeEndElement();
            for (TicketInfo ticket : ticketlist) {
                if (ticket != null) {
                    Calendar datenew = Calendar.getInstance();
                    datenew.setTime(ticket.getDate());
                    CustomerInfoExt bp;
                    if (ticket.getCustomerId() == null) {
                        bp = null;
                    } else {
                        bp = ticket.getCustomer();
                    }
                    for (int j = 0; j < ticket.getLines().size(); j++) {
//red1 - convert to XML for ActiveMQ
                        writer.writeStartElement("detail");
                        writer.writeStartElement(I_I_Order.COLUMNNAME_DocTypeName);
                        writer.writeCharacters("POS Order");
                        writer.writeEndElement();
//red1 - convert to XML
                        
                        // Add by Ing Tatioti Mbogning Raoul
                        // Finish the synchronisation of all informations about Partner
                        if (bp != null) {
                            writer.writeStartElement(I_I_Order.COLUMNNAME_BPartnerValue);
                            writer.writeCharacters(bp.getName());
                            writer.writeEndElement();
                            
                            writer.writeStartElement(I_I_Order.COLUMNNAME_Postal);
                            writer.writeCharacters(bp.getPostal());
                            writer.writeEndElement();
                            
                            writer.writeStartElement(I_I_Order.COLUMNNAME_Address1);
                            writer.writeCharacters(bp.getAddress());
                            writer.writeEndElement();
                            
                            writer.writeStartElement(I_I_Order.COLUMNNAME_Address2);
                            writer.writeCharacters(bp.getAddress2());
                            writer.writeEndElement();
                            
                            writer.writeStartElement(I_I_Order.COLUMNNAME_Phone);
                            writer.writeCharacters(bp.getPhone2().isEmpty() ? bp.getPhone() : bp.getPhone2());
                            writer.writeEndElement();
                            
                            writer.writeStartElement(I_I_Order.COLUMNNAME_City);
                            writer.writeCharacters(bp.getCity());
                            writer.writeEndElement();
                            
                            writer.writeStartElement(I_I_Order.COLUMNNAME_EMail);
                            writer.writeCharacters(bp.getEmail());
                            writer.writeEndElement();
                            
                            writer.writeStartElement(I_I_Order.COLUMNNAME_RegionName);
                            writer.writeCharacters(bp.getRegion());
                            writer.writeEndElement();
                        }
                        
                        writer.writeStartElement(I_I_Order.COLUMNNAME_AD_Client_ID);
                        writer.writeCharacters(externalsales.getM_iERPId());
                        writer.writeEndElement();
                        
                        writer.writeStartElement("PosLocatorName");
                        writer.writeCharacters(externalsales.getM_iERPPos());
                        writer.writeEndElement();
                        
                        writer.writeStartElement(I_I_Order.COLUMNNAME_DocumentNo);
                        writer.writeCharacters(Integer.toString(ticket.getTicketId()));
                        writer.writeEndElement();
                        
                        writer.writeStartElement(I_I_Order.COLUMNNAME_DateOrdered);
                        writer.writeCharacters(new java.sql.Timestamp(datenew.getTime().getTime()).toString());
                        writer.writeEndElement();
                        
                        TicketLineInfo line = ticket.getLines().get(j);
                        writer.writeStartElement(I_I_Order.COLUMNNAME_ProductValue);
                        writer.writeCharacters(line.getProductName());
                        writer.writeEndElement();
                            
                        writer.writeStartElement(I_I_Order.COLUMNNAME_QtyOrdered);
                        writer.writeCharacters(Double.toString(line.getMultiply()));
                        writer.writeEndElement();
                        
                        writer.writeStartElement(I_I_Order.COLUMNNAME_PriceActual);
                        writer.writeCharacters(Double.toString(line.getPrice()));
                        writer.writeEndElement();

                        writer.writeStartElement(I_I_Order.COLUMNNAME_TaxAmt);
                        writer.writeCharacters(Double.toString(ticket.getTax()));
                        writer.writeEndElement();
//TODO get User Code as SalesRep_ID (see ImportQueue2AD)
                        writer.writeEndElement();//detail
                    }
                    //orders[i].setLines(orderLine);
                }
            }
//red1
            writer.writeEndElement();//entityDetail
            writer.writeEndDocument();
            return res.toString();
        } catch (XMLStreamException ex) {
            return "ERROR creating XML";
        }
    }

    private static int parseInt(String sValue) {
        try {
            return Integer.parseInt(sValue);
        } catch (NumberFormatException eNF) {
            return 0;
        }
    }
}
