/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.data.loader;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.openbravo.basic.BasicException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author TATIOTI-IDEAPAD
 */
public abstract class MongoDBBatchSentence extends MongoDBBaseSentence {
    /**
     *
     */
    protected Session m_s;    

    /**
     *
     */
    protected HashMap<String, String> m_parameters;
    
    /** Creates a new instance of BatchSentence
     * @param s */
    public MongoDBBatchSentence(Session s) {
        m_s = s;
        m_parameters = new HashMap<>();
    }
    
    /**
     *
     * @param name
     * @param replacement
     */
    public void putParameter(String name, String replacement) {
        m_parameters.put(name, replacement);
    }
    
    /**
     *
     * @return
     * @throws BasicException
     */
    protected abstract Reader getReader() throws BasicException;
    
    /**
     *
     */
    public class ExceptionsResultSet implements DataResultSet {
        
        List l;
        int m_iIndex;
        
        /**
         *
         * @param l
         */
        public ExceptionsResultSet(List l) {
            this.l = l;
            m_iIndex = -1;
        }
        
        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
        @Override
        public Integer getInt(int columnIndex) throws BasicException {
            throw new BasicException(LocalRes.getIntString("exception.nodataset"));
        }

        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
        @Override
        public String getString(int columnIndex) throws BasicException {
            throw new BasicException(LocalRes.getIntString("exception.nodataset"));
        }

        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
        @Override
        public Double getDouble(int columnIndex) throws BasicException {
            throw new BasicException(LocalRes.getIntString("exception.nodataset"));
        }

        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
        @Override
        public Boolean getBoolean(int columnIndex) throws BasicException {
            throw new BasicException(LocalRes.getIntString("exception.nodataset"));
        }

        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
        @Override
        public java.util.Date getTimestamp(int columnIndex) throws BasicException {
            throw new BasicException(LocalRes.getIntString("exception.nodataset"));
        }

        //public java.io.InputStream getBinaryStream(int columnIndex) throws DataException;

        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
                @Override
        public byte[] getBytes(int columnIndex) throws BasicException {
            throw new BasicException(LocalRes.getIntString("exception.nodataset"));
        }

        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
        @Override
        public Object getObject(int columnIndex) throws BasicException  {
            throw new BasicException(LocalRes.getIntString("exception.nodataset"));
        }

    //    public int getColumnCount() throws DataException;

        /**
         *
         * @return
         * @throws BasicException
         */
                @Override
        public DataField[] getDataField() throws BasicException {
            throw new BasicException(LocalRes.getIntString("exception.nodataset"));
        }

        /**
         *
         * @return
         * @throws BasicException
         */
        @Override
        public Object getCurrent() throws BasicException {
            if (m_iIndex < 0 || m_iIndex >= l.size()) {
                throw new BasicException(LocalRes.getIntString("exception.outofbounds"));
            } else {
                return l.get(m_iIndex);
            }
        }
        
        /**
         *
         * @return
         * @throws BasicException
         */
        @Override
        public boolean next() throws BasicException {
            return ++m_iIndex < l.size();
        }

        /**
         *
         * @throws BasicException
         */
        @Override
        public void close() throws BasicException {
        }

        /**
         *
         * @return
         */
        @Override
        public int updateCount() {
            return 0;
        }
    }
    
    /**
     *
     * @throws BasicException
     */
    @Override
    public final void closeExec() throws BasicException {
    }
    
    /**
     *
     * @return
     * @throws BasicException
     */
    @Override
    public final DataResultSet moreResults() throws BasicException {
        return null;
    }
    
    /**
     *
     * @param params
     * @return
     * @throws BasicException
     */
    @Override
    public DataResultSet openExec(Object params) throws BasicException {
        try {
            BufferedReader br = new BufferedReader(getReader());
            
            String sLine;
            while ((sLine = br.readLine()) != null) {
                sLine = sLine.trim();
                if (!sLine.equals("") && !sLine.startsWith("--")) {
                    
                    String collection = sLine.split(";")[0].split("=")[1];
                    String insertStatement = sLine.split(";")[1].replace('{', ' ').replace('}', ' ');
                    insertStatement = insertStatement.trim();
                    
                    String[] mapSplit = insertStatement.split(",");
                    BasicDBObject insertObject = new BasicDBObject();
                    for (int i = 0; i < mapSplit.length; ++i) {
                        String[] keyValue = mapSplit[i].split(":");
                        if (keyValue[1].contains("$APP_ID"))
                            keyValue[1] = m_parameters.get("APP_ID");
                        else if (keyValue[1].contains("$APP_NAME"))
                            keyValue[1] = m_parameters.get("APP_NAME");
                        else if (keyValue[1].contains("$APP_VERSION"))
                            keyValue[1] = m_parameters.get("APP_VERSION");
                        if (keyValue[1].contains("$FILE"))
                        {
                            String filepath = keyValue[1].trim().substring(6);
                            insertObject.append(keyValue[0].trim(), ImageUtils.getBytesFromResource(filepath));
                        }
                        else
                        {
                            insertObject.append(keyValue[0].trim(), keyValue[1].trim());
                        }
                    }
                    DBCollection dbCollection = m_s.getMongoDBDatabase().getCollection(collection);
                    dbCollection.insert(insertObject);
                }
            }
        } catch (IOException ex) {
            
        }
        return new ExceptionsResultSet(new ArrayList());
    }    
       
    private static class VarParams implements SerializableWrite {
        
        private List l;
        
        public VarParams(List l) {
            this.l = l;
        }
        
        @Override
        public void writeValues(DataWrite dp) throws BasicException {
            for (int i = 0; i < l.size(); i++) {
                Object v = l.get(i);
                if (v instanceof String) {
                    dp.setString(i + 1, (String) v);
                } else if (v instanceof byte[]) {
                    dp.setBytes(i + 1, (byte[]) l.get(i));
                } else {
                    dp.setObject(i + 1, v);
                }                
            }
        }
    }
}
