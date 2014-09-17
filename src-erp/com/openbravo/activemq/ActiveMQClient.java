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

//    author : Ing. Tatioti Mbogning Raoul(tatiotir@itkamer.com)

package com.openbravo.activemq;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.CompletionListener;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author TATIOTI-IDEAPAD
 */
public class ActiveMQClient {
    
    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private Boolean error = false;
    private final String activemqBrokerUrl;
    private final String activemqUsername;
    private final String activemqPassword;

    public Boolean error() {
        return error;
    }
    
    public ActiveMQClient(String activemqBrokerUrl, String activemqUsername, String activemqPassword)
    {
        this.activemqBrokerUrl = activemqBrokerUrl;
        this.activemqUsername = activemqUsername;
        this.activemqPassword = activemqPassword;
    }
    
    public boolean init()
    {
        try {
            factory = new ActiveMQConnectionFactory(activemqUsername, activemqPassword, activemqBrokerUrl);
            // Create a connection
            connection = factory.createConnection();

            // Setup session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            
            return true;
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean sendMessage(String message, String queueName)
    {
        try {
            // Setup message destination directory
            Destination destination = session.createQueue(queueName);
            
            // Setup message sender
            MessageProducer sender = session.createProducer(destination);
            
            // Send the message
            TextMessage textMessage = session.createTextMessage(message);
            sender.send(textMessage);
            
            return true;
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean sendMessage(Serializable messageObject, String queueName)
    {
        try {
            // Setup message destination directory
            Destination destination = session.createQueue(queueName);
            
            // Setup message sender
            MessageProducer sender = session.createProducer(destination);
            
            // Send the message
            ObjectMessage objectMessage = session.createObjectMessage(messageObject);
            sender.send(objectMessage);
            
            return true;
        } catch (JMSException ex) {
            error = true;
            Logger.getLogger(ActiveMQClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Message consumeMessage(String queueName)
    {
        try {
            Destination destination = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(destination);
            
            return consumer.receive();
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
