//    Wanda POS - Africa's Gift to the World
//    Copyright (c) 2014-2015 IT-Kamer & previous Unicenta POS and Openbravo POS works
//    www.erp-university-africa.com
//
//    This file is part of Wanda POS
//
//    Wanda POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   Wanda POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Wanda POS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.data.loader;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.openbravo.data.gui.JMessageDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.pos.forms.AppLocal;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author adrianromero
 * Created on February 6, 2007, 4:06 PM
 *
 */
public final class Session {
    
    private final String m_surl;
    private final String m_sappuser;
    private final String m_spassword;
    private final String m_database;
    private final String m_host;
    private final Integer m_port;
    
    private Connection m_c;
    private MongoClient m_mongoClient;
    private boolean mongoDBSession;
    private boolean m_bInTransaction;

    /**
     *
     */
    public final SessionDB DB;
    
    /** Creates a new instance of Session
     * @param url
     * @param user
     * @param password
     * @throws java.sql.SQLException */
    public Session(String url, String user, String password) throws SQLException {
        m_surl = url;
        m_sappuser = user;
        m_spassword = password;
        m_database = "";
        m_host = "";
        m_port = -1;
        
        m_mongoClient = null;
        m_c = null;
        m_bInTransaction = false;
        
        connect(); // no lazy connection

        DB = getDiff();
    }
    
    /**
     * Create session for MongoDB database engine
     * 
     * @param host
     * @param port
     * @param database
     * @param user
     * @param password 
     */
    public Session(String host, Integer port, String database, String user, String password)
    {
        m_surl = "";
        m_c = null;
        m_bInTransaction = false;
        m_host = host;
        m_port = port;
        m_database = database;
        m_sappuser = user;
        m_spassword = password;
        
        connectMongoDB();
        DB = new SessionDBMongoDB();
        mongoDBSession = true;
    }

    public boolean isMongoDBSession() {
        return mongoDBSession;
    }

    public void setMongoDBSession(boolean mongoDBSession) {
        this.mongoDBSession = mongoDBSession;
    }

    public boolean isM_bInTransaction() {
        return m_bInTransaction;
    }

    public void setM_bInTransaction(boolean m_bInTransaction) {
        this.m_bInTransaction = m_bInTransaction;
    }
    
    public void connectMongoDB()
    {
        if (!m_sappuser.isEmpty() && !m_spassword.isEmpty() && !m_database.isEmpty()) {
            try {
                MongoCredential credential = MongoCredential.createMongoCRCredential(m_sappuser, m_database, m_spassword.toCharArray());
                m_mongoClient = new MongoClient(new ServerAddress(m_host, m_port), Arrays.asList(credential));
            } catch (UnknownHostException ex) {}
        } else {
            try {
                m_mongoClient = new MongoClient(new ServerAddress(m_host, m_port));
            } catch (UnknownHostException ex) {}
        }
    }

    public DB getMongoDBDatabase() {
        return m_mongoClient.getDB(m_database);
    }
    
    /**
     *
     * @throws SQLException
     */
    public void connect() throws SQLException {
        
        // primero cerramos si no estabamos cerrados
        close();
        
        // creamos una nueva conexion.
        m_c = (m_sappuser == null && m_spassword == null)
        ? DriverManager.getConnection(m_surl)
        : DriverManager.getConnection(m_surl, m_sappuser, m_spassword);         
        m_c.setAutoCommit(true);
        m_bInTransaction = false;
    }     

    /**
     *
     */
    public void close() {
        
        if (m_c != null) {
            try {
                if (m_bInTransaction) {
                    m_bInTransaction = false; // lo primero salimos del estado
                    m_c.rollback();
                    m_c.setAutoCommit(true);  
                }            
                m_c.close();
            } catch (SQLException e) {
                // me la como
            } finally {
                m_c = null;
            }
        }
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        
        if (!m_bInTransaction) {
            ensureConnection();
        }
        return m_c;
    }
    
    /**
     *
     * @throws SQLException
     */
    public void begin() throws SQLException {
        
        if (m_bInTransaction) {
            throw new SQLException("Already in transaction");
        } else {
            ensureConnection();
            m_c.setAutoCommit(false);
            m_bInTransaction = true;
        }
    }

    /**
     *
     * @throws SQLException
     */
    public void commit() throws SQLException {
        if (m_bInTransaction) {
            m_bInTransaction = false; // lo primero salimos del estado
            m_c.commit();
            m_c.setAutoCommit(true);          
        } else {
            throw new SQLException("Transaction not started");
        }
    }

    /**
     *
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        if (m_bInTransaction) {
            m_bInTransaction = false; // lo primero salimos del estado
            m_c.rollback();
            m_c.setAutoCommit(true);            
        } else {
            throw new SQLException("Transaction not started");
        }
    }

    /**
     *
     * @return
     */
    public boolean isTransaction() {
        return m_bInTransaction;
    }
    
    private void ensureConnection() throws SQLException {
        // solo se invoca si isTransaction == false
        
        boolean bclosed;
        try {
            bclosed = m_c == null || m_c.isClosed();
        } catch (SQLException e) {
            bclosed = true;
        }

        // reconnect if closed
        if (bclosed) {
            connect();
        }
    }  

    /**
     *
     * @return
     * @throws SQLException
     */
    public String getURL() throws SQLException {
        return getConnection().getMetaData().getURL();
    }

    private SessionDB getDiff() throws SQLException {

        String sdbmanager = getConnection().getMetaData().getDatabaseProductName();
        switch (sdbmanager) {
            case "HSQL Database Engine":
                return new SessionDBHSQLDB();
            case "MySQL":
                return new SessionDBMySQL();
            case "PostgreSQL":
                return new SessionDBPostgreSQL();
            case "Oracle":
                return new SessionDBOracle();
            case "Apache Derby":
                return new SessionDBDerby();
            default:
                return new SessionDBGeneric(sdbmanager);
        }
    }
}
