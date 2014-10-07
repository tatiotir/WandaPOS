
/**
 * ActiveMQ Client which can send and receive message from or to Queue
 * @author Ing Tatioti Mbogning Raoul(tatiotir@itkamer, tatiotir@gmail.com)
 * @version 1.0
 * @poweredby IT Kamer LTD Cameroon.
 */

package com.openbravo.activemq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private String activemqBrokerUrl = "";
    private String activemqUsername = "";
    private String activemqPassword = "";

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
            factory = new ActiveMQConnectionFactory(activemqBrokerUrl);
            // Create a connection
            if (!activemqUsername.isEmpty() && !activemqPassword.isEmpty())
            	connection = factory.createConnection(activemqUsername, activemqPassword);
            else
            	connection = factory.createConnection();

            connection.start();
            // Setup session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            return true;
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void close()
    {
    	try {
    		connection.stop();
			connection.close();
		} catch (JMSException e) {
		}
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
            sender.close();
            
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
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
            
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public Message consumeMessage(String queueName)
    {
        try {
            Destination destination = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(destination);
            
            Message message = consumer.receive(1000);
            if (message != null)
            {
                consumer.close();
            	return message;
            }
            else
            {
                consumer.close();
            	return session.createTextMessage("");
            }
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Message> consumeAllMessages(String queueName)
    {
    	try {
            Destination destination = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(destination);
            
            ArrayList<Message> messageList = new ArrayList<>();
            Message message = consumer.receive(1000);
            while (message != null)
            {
            	messageList.add(message);
                message = consumer.receive(1000);
            }
            
            consumer.close();
            return messageList;
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean eraseQueue(String queueName)
    {
    	try {
            Destination destination = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(destination);
            
            Message message = consumer.receive(1000);
            while (message != null)
            {
                message = consumer.receive(1000);
            }
            return true;
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}