/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.data.loader;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.openbravo.basic.BasicException;
import java.util.Map;

/**
 *
 * @author TATIOTI-IDEAPAD
 */
public abstract class MongoDBSentence extends MongoDBBaseSentence {

    protected Session m_s;

    public MongoDBSentence(Session s) {
        super();
        m_s = s;
    }

    protected static final class MongoDBDataResultSet implements DataResultSet {

        private DBCursor m_dbCursor = null;
        private DBObject m_dbObject = null;
        private DBObject m_currentObject = new BasicDBObject();
        private Map<Integer, String> m_columnMap;
        private SerializerRead m_serread;
//        private int m_iColumnCount;

        /**
         *
         * @param cursor
         * @param columnMap
         * @param rs
         * @param serread
         */
        public MongoDBDataResultSet(DBCursor cursor, Map<Integer, String> columnMap, SerializerRead serread) {
            m_dbCursor = cursor;
            m_serread = serread;
            m_columnMap = columnMap;
        }
        
        public MongoDBDataResultSet(DBObject dbObject, Map<Integer, String> columnMap, SerializerRead serread) {
            m_dbObject = dbObject;
            m_serread = serread;
            m_columnMap = columnMap;
        }

        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
        @Override
        public Integer getInt(int columnIndex) throws BasicException {
            if (m_dbCursor != null)
                return (Integer)m_currentObject.get(m_columnMap.get(columnIndex));
            else
                return (Integer)m_dbObject.get(m_columnMap.get(columnIndex));
        }

        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
        @Override
        public String getString(int columnIndex) throws BasicException {
            if (m_dbCursor != null)
                return (String)m_currentObject.get(m_columnMap.get(columnIndex));
            else
                return (String)m_dbObject.get(m_columnMap.get(columnIndex));
        }

        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
        @Override
        public Double getDouble(int columnIndex) throws BasicException {
            if (m_dbCursor != null)
                return (Double)m_currentObject.get(m_columnMap.get(columnIndex));
            else
                return (Double)m_dbObject.get(m_columnMap.get(columnIndex));
        }

        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
        @Override
        public Boolean getBoolean(int columnIndex) throws BasicException { 
            if (m_dbCursor != null)
                return (Boolean)m_currentObject.get(m_columnMap.get(columnIndex));
            else
                return (Boolean)m_dbObject.get(m_columnMap.get(columnIndex));
        }

        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
        @Override
        public java.util.Date getTimestamp(int columnIndex) throws BasicException {
            if (m_dbCursor != null)
                return (java.util.Date)m_currentObject.get(m_columnMap.get(columnIndex));
            else
                return (java.util.Date)m_dbObject.get(m_columnMap.get(columnIndex));
        }

        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
        @Override
        public byte[] getBytes(int columnIndex) throws BasicException {
            if (m_dbCursor != null)
                return (byte[])m_currentObject.get(m_columnMap.get(columnIndex));
            else
                return (byte[])m_dbObject.get(m_columnMap.get(columnIndex));
        }

        /**
         *
         * @param columnIndex
         * @return
         * @throws BasicException
         */
        @Override
        public Object getObject(int columnIndex) throws BasicException {
            if (m_dbCursor != null)
                return m_currentObject.get(m_columnMap.get(columnIndex));
            else
                return m_dbObject.get(m_columnMap.get(columnIndex));
        }

        /**
         *
         * @return @throws BasicException
         */
        @Override
        public DataField[] getDataField() throws BasicException {
            DataField[] df = new DataField[m_columnMap.keySet().size()];
            for (int i = 0; i < df.length; i++) {
                df[i] = new DataField();
                df[i].Name = m_columnMap.get(i);
                    //df[i].Size = md.getColumnDisplaySize(i + 1);
                //df[i].Type = md.getColumnType(i + 1);
            }
            return df;
        }

        /**
         *
         * @return @throws BasicException
         */
        @Override
        public Object getCurrent() throws BasicException {
            return m_serread.readValues(this);
        }

        /**
         *
         * @return @throws BasicException
         */
        @Override
        public boolean next() throws BasicException {
            if (m_dbCursor != null)
            {
                if (m_dbCursor.hasNext())
                {
                    m_currentObject = m_dbCursor.next();
                    return true;
                }
                else
                    return false;
            }
            else
                return !m_dbObject.keySet().isEmpty();
        }

        /**
         *
         * @throws BasicException
         */
        @Override
        public void close() throws BasicException {
            if (m_dbCursor != null)
                m_dbCursor.close();
        }

        /**
         *
         * @return @throws BasicException
         */
        @Override
        public int updateCount() throws BasicException {
            return -1; // es decir somos datos.
        }
    }
}
