/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.mq;

import com.openbravo.pos.forms.AppProperties;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author TATIOTI-IDEAPAD
 */
public class MessageSender {
    
    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private Destination destination;
    
    public MessageSender(AppProperties appProperties)
    {
    }
    
    private void activeMqInitialisation(AppProperties appProperties)
    {
        try {
            factory = new ActiveMQConnectionFactory(appProperties.getProperty("activemq.username"), 
                    appProperties.getProperty("activemq.password"), appProperties.getProperty("activemq.broker.url"));
            // Create a connection
            connection = factory.createConnection();

            // Setup session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
        } catch (JMSException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage(String message, String queueName)
    {
        try {
            // Setup message destination directory
            destination = session.createQueue(queueName);
            
            // Setup message sender
            MessageProducer sender = session.createProducer(destination);
            
            // Send the message
            TextMessage textMessage = session.createTextMessage(message);
            sender.send(textMessage);
            
        } catch (JMSException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage(Serializable messageObject, String queueName)
    {
        try {
            // Setup message destination directory
            destination = session.createQueue(queueName);
            
            // Setup message sender
            MessageProducer sender = session.createProducer(destination);
            
            // Send the message
            ObjectMessage objectMessage = session.createObjectMessage(messageObject);
            sender.send(objectMessage);
        } catch (JMSException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
