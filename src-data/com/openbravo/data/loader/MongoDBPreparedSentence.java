/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.data.loader;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.openbravo.basic.BasicException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author TATIOTI-IDEAPAD
 */
public class MongoDBPreparedSentence extends MongoDBSentence {

    private static final Logger logger = Logger.getLogger("com.openbravo.data.loader.MongoDBPreparedSentence");
    
    private String m_collectionName;
    private Map<Integer, String> m_writeParamColumnMap;
    private Map<Integer, String> m_readParamColumnMap;
    private BasicDBObject m_findDBObject = new BasicDBObject();
    private BasicDBObject m_updateDBObject = new BasicDBObject();
    private BasicDBObject m_insertDBObject = new BasicDBObject();
    
    private boolean m_find = false;
    private boolean m_update = false;
    private boolean m_insert = false;
    private String m_sortColumn = "";
    private String m_maxColumn = "";
    private boolean m_countAll = false;
    private String[] m_lessThanColumn = null;
    private String[] m_greaterThanColumn = null;
    private String m_nullColumn = "";
    private DBCursor m_dbCursor;
    
    protected SerializerRead m_SerRead = null;
    protected SerializerWrite m_SerWrite = null;
    
    public MongoDBPreparedSentence(Session s, String collectionName, Map<Integer, String> writeParamColumnMap, Map<Integer, String> readParamColumnMap,
            SerializerWrite serWrite, SerializerRead serRead) {
        super(s);
        m_SerRead = serRead;
        m_SerWrite = serWrite;
        m_collectionName = collectionName;
        m_writeParamColumnMap = writeParamColumnMap;
        m_readParamColumnMap = readParamColumnMap;
        m_find = true;
    }
    
    public MongoDBPreparedSentence(Session s, String collectionName, Map<Integer, String> writeParamColumnMap, Map<Integer, String> readParamColumnMap, 
            SerializerRead serRead) {
        super(s);
        m_SerRead = serRead;
        m_collectionName = collectionName;
        m_writeParamColumnMap = writeParamColumnMap;
        m_readParamColumnMap = readParamColumnMap;
        m_find = true;
    }
    
    public MongoDBPreparedSentence(Session s, String collectionName, Map<Integer, String> writeParamColumnMap, Map<Integer, String> readParamColumnMap, 
            BasicDBObject findObject, SerializerWrite serWrite, SerializerRead serRead)
    {
        super(s);
        m_SerWrite = serWrite;
        m_SerRead = serRead;
        m_writeParamColumnMap = writeParamColumnMap;
        m_readParamColumnMap = readParamColumnMap;
        m_find = true;
        m_findDBObject = findObject;
    }
    
    public MongoDBPreparedSentence(Session s, String collectionName, Map<Integer, String> readParamMap, BasicDBObject findObject,
            SerializerRead serRead)
    {
        super(s);
        m_SerRead = serRead;
        m_collectionName = collectionName;
        m_readParamColumnMap = readParamMap;
        m_find = true;
        m_findDBObject = findObject;
    }
    
    public MongoDBPreparedSentence(Session s, String collectionName, Map<Integer, String> readParamMap, SerializerRead serRead)
    {
        super(s);
        m_SerRead = serRead;
        m_collectionName = collectionName;
        m_readParamColumnMap = readParamMap;
        m_find = true;
    }
    
    public MongoDBPreparedSentence(Session s, String collectionName, Map<Integer, String> writeParamMap,
            SerializerWrite serWrite, boolean insert) {
        super(s);
        m_collectionName = collectionName;
        m_writeParamColumnMap = writeParamMap;
        m_SerWrite = serWrite;
        if (insert)
            m_insert = true;
        else
            m_update = true;
    }
    
    public MongoDBPreparedSentence setSortColumn(String sortColumn) {
        this.m_sortColumn = sortColumn;
        return this;
    }
    
    public MongoDBPreparedSentence setMaxColumn(String maxColumn) {
        this.m_maxColumn = maxColumn;
        return this;
    }
    
    public MongoDBPreparedSentence setCountAll(boolean bool)
    {
        this.m_countAll = bool;
        return this;
    }
    
    public MongoDBPreparedSentence setNullColumn(String nullColumn)
    {
        this.m_nullColumn = nullColumn;
        return this;
    }
    
    public MongoDBPreparedSentence setLessThanColumn(String[] lessThanColumn)
    {
        this.m_lessThanColumn = lessThanColumn;
        return this;
    }
    
    public MongoDBPreparedSentence setGreaterThanColumn(String[] greaterThanColumn)
    {
        this.m_greaterThanColumn = greaterThanColumn;
        return this;
    }

    private static final class MongoDBPreparedSentencePars implements DataWrite {

        private Map<Integer, String> m_writeParamColumnMap;
        private BasicDBObject m_dbObject;

        /** Creates a new instance of SQLParameter */
        MongoDBPreparedSentencePars(BasicDBObject dbObject, Map<Integer, String> paramColumnMap) {
            m_writeParamColumnMap = paramColumnMap;
            m_dbObject = dbObject;
        }

        @Override
        public void setInt(int paramIndex, Integer iValue) throws BasicException {
            m_dbObject.append(m_writeParamColumnMap.get(paramIndex), iValue);
        }
        
        @Override
        public void setString(int paramIndex, String sValue) throws BasicException {
            m_dbObject.append(m_writeParamColumnMap.get(paramIndex), sValue);
        }
        
        @Override
        public void setDouble(int paramIndex, Double dValue) throws BasicException {
            m_dbObject.append(m_writeParamColumnMap.get(paramIndex), dValue);
        }   
        
        @Override
        public void setBoolean(int paramIndex, Boolean bValue) throws BasicException {
            if (bValue != null) {
                m_dbObject.append(m_writeParamColumnMap.get(paramIndex), bValue);
            }
        }
        
        @Override
        public void setTimestamp(int paramIndex, java.util.Date dValue) throws BasicException { 
            if (dValue != null)
            {
                m_dbObject.append(m_writeParamColumnMap.get(paramIndex), new Timestamp(dValue.getTime()));
            }
        }       
//        public void setBinaryStream(int paramIndex, java.io.InputStream in, int length) throws DataException {
//            try {
//                m_ps.setBinaryStream(paramIndex, in, length);
//            } catch (SQLException eSQL) {
//                throw new DataException(eSQL);
//            }
//        }
        @Override
        public void setBytes(int paramIndex, byte[] value) throws BasicException {
            m_dbObject.append(m_writeParamColumnMap.get(paramIndex), value);
        }
        
        @Override
        public void setObject(int paramIndex, Object value) throws BasicException {
            m_dbObject.append(m_writeParamColumnMap.get(paramIndex), value);
        }
    }
    
    @Override
    public DataResultSet openExec(Object params) throws BasicException {
        closeExec();
        
        DBCollection collection = m_s.getMongoDBDatabase().getCollection(m_collectionName);
        
        if (m_SerWrite != null)
        {
            if (m_insert)
                m_SerWrite.writeValues(new MongoDBPreparedSentencePars(m_insertDBObject, m_writeParamColumnMap), params);
            else if (m_find)
                m_SerWrite.writeValues(new MongoDBPreparedSentencePars(m_findDBObject, m_writeParamColumnMap), params);
            else if (m_update)
                m_SerWrite.writeValues(new MongoDBPreparedSentencePars(m_updateDBObject, m_writeParamColumnMap), params);
        }
        
        if (!m_nullColumn.isEmpty())
            m_findDBObject.append(m_nullColumn, new BasicDBObject("$exists", true));
        
        if (m_lessThanColumn != null)
        {
            for (int i = 0; i < m_lessThanColumn.length; ++i)
            {
                Object lessThanValue = m_findDBObject.get(m_lessThanColumn[i]);
                m_findDBObject.removeField(m_lessThanColumn[i]);
                m_findDBObject.append(m_lessThanColumn[i], new BasicDBObject("$lt", lessThanValue));
            }
        }
        
        if (m_greaterThanColumn != null)
        {
            for (int i = 0; i < m_greaterThanColumn.length; ++i)
            {
                Object greaterThanValue = m_findDBObject.get(m_greaterThanColumn[i]);
                m_findDBObject.removeField(m_greaterThanColumn[i]);
                m_findDBObject.append(m_greaterThanColumn[i], new BasicDBObject("$gt", greaterThanValue));
            }
        }
            
        // Insert statement
        if (m_insert)
        {
            collection.insert(m_insertDBObject);
        }
        else if (m_find)
        {
            
            
            if (!m_sortColumn.isEmpty())
            {
                m_dbCursor = collection.find(m_findDBObject).sort(new BasicDBObject(m_sortColumn, 1));
                return new MongoDBDataResultSet(m_dbCursor, m_readParamColumnMap, m_SerRead);
            }
            
            if (!m_maxColumn.isEmpty())
            {
                Iterator<DBObject> it = collection.aggregate(new BasicDBObject("$match", m_findDBObject), new BasicDBObject("$group", new BasicDBObject("_id", "null").append("MAX", new BasicDBObject("$max", "$" + m_maxColumn)))).results().iterator();
                //Iterator<DBObject> it = collection.aggregate(new BasicDBObject("$group", new BasicDBObject("_id", "null").append("MAX", new BasicDBObject("$max", "$" + m_maxColumn)))).results().iterator();
                DBObject maxObject = new BasicDBObject();
                if (it.hasNext())
                    maxObject = it.next();
                return new MongoDBDataResultSet(maxObject, m_readParamColumnMap, m_SerRead);
            }
            
            if (m_countAll)
            {
                Iterator<DBObject> it = collection.aggregate(new BasicDBObject("$match", m_findDBObject), new BasicDBObject("$group", new BasicDBObject("_id", "null").append("COUNT", new BasicDBObject("$sum", 1)))).results().iterator();
                //Iterator<DBObject> it = collection.aggregate(new BasicDBObject("$group", new BasicDBObject("_id", "null").append("MAX", new BasicDBObject("$max", "$" + m_maxColumn)))).results().iterator();
                DBObject maxObject = new BasicDBObject();
                if (it.hasNext())
                    maxObject = it.next();
                return new MongoDBDataResultSet(maxObject, m_readParamColumnMap, m_SerRead);
            }
            m_dbCursor = collection.find(m_findDBObject);
            return new MongoDBDataResultSet(m_dbCursor, m_readParamColumnMap, m_SerRead);
        }
        else if (m_update)
        {
            String findKey = ((String)m_writeParamColumnMap.get((Integer)m_writeParamColumnMap.keySet().toArray()[m_writeParamColumnMap.size() - 1]));
            String key = findKey.replace('s', ' ').trim();
            m_findDBObject.append(key, m_updateDBObject.get(findKey));
            
            // Remove the find criteria in the update object
            m_updateDBObject.remove(m_writeParamColumnMap.get((Integer)m_writeParamColumnMap.keySet().toArray()[m_writeParamColumnMap.size() - 1]));
            collection.findAndModify(m_findDBObject, null, null, true, m_updateDBObject, true, true);
            return new SentenceUpdateResultSet(0);
        }
        return null;
    }

    @Override
    public DataResultSet moreResults() throws BasicException {
        return new MongoDBDataResultSet(m_dbCursor, m_readParamColumnMap, m_SerRead);
    }

    @Override
    public void closeExec() throws BasicException {
        if (m_dbCursor != null)
            m_dbCursor.close();
    }
    
}
