/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.data.loader;

import com.openbravo.basic.BasicException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TATIOTI-IDEAPAD
 */
public abstract class MongoDBBaseSentence implements SentenceList, SentenceFind, SentenceExec {

    /**
     *
     * @param params
     * @return
     * @throws BasicException
     */
        public abstract DataResultSet openExec(Object params) throws BasicException;

    /**
     *
     * @return
     * @throws BasicException
     */
    public abstract DataResultSet moreResults() throws BasicException;

    /**
     *
     * @throws BasicException
     */
    public abstract void closeExec() throws BasicException;
    
    /**
     *
     * @return
     * @throws BasicException
     */
        @Override
    public final int exec() throws BasicException {
        return exec((Object) null);
    }

    /**
     *
     * @param params
     * @return
     * @throws BasicException
     */
    @Override
    public final int exec(Object... params) throws BasicException {
        return exec((Object) params);
    }

    /**
     *
     * @param params
     * @return
     * @throws BasicException
     */
    @Override
    public final int exec(Object params) throws BasicException {        
        DataResultSet SRS = openExec(params);
        if (SRS == null) {
            throw new BasicException(LocalRes.getIntString("exception.noupdatecount"));
        }
        int iResult = SRS.updateCount();
        SRS.close();
        closeExec();
        return iResult;
    }

    /**
     *
     * @return
     * @throws BasicException
     */
    @Override
    public final List list() throws BasicException {
        return list((Object) null);
    }

    /**
     *
     * @param params
     * @return
     * @throws BasicException
     */
    @Override
    public final List list(Object... params) throws BasicException {
        return list((Object) params);
    }

    /**
     *
     * @param params
     * @return
     * @throws BasicException
     */
    @Override
    public final List list(Object params) throws BasicException {
    // En caso de error o lanza un pepinazo en forma de DataException 
        DataResultSet SRS = openExec(params);
        List aSO = fetchAll(SRS);    
        SRS.close();
        closeExec();       
        return aSO;
    }
    
    /**
     *
     * @param offset
     * @param length
     * @return
     * @throws BasicException
     */
    @Override
    public final List listPage(int offset, int length) throws BasicException {
        return listPage(null, offset, length);
    }

    /**
     *
     * @param params
     * @param offset
     * @param length
     * @return
     * @throws BasicException
     */
    @Override
    public final List listPage(Object params, int offset, int length) throws BasicException {
    // En caso de error o lanza un pepinazo en forma de DataException         
        DataResultSet SRS = openExec(params);
        List aSO = fetchPage(SRS, offset, length);    
        SRS.close();
        closeExec();       
        return aSO;
    }
    
    /**
     *
     * @return
     * @throws BasicException
     */
    @Override
    public final Object find() throws BasicException {
        return find((Object) null);
    }

    /**
     *
     * @param params
     * @return
     * @throws BasicException
     */
    @Override
    public final Object find(Object... params) throws BasicException {
        return find((Object) params);
    }

    /**
     *
     * @param params
     * @return
     * @throws BasicException
     */
    @Override
    public final Object find(Object params) throws BasicException {
    // En caso de error o lanza un pepinazo en forma de SQLException          
        DataResultSet SRS = openExec(params);
        Object obj = fetchOne(SRS);  
        SRS.close();
        closeExec();       
        return obj;
    }

    // Utilidades

    /**
     *
     * @param SRS
     * @return
     * @throws BasicException
     */
        public final List fetchAll(DataResultSet SRS) throws BasicException {
        if (SRS == null) {
            throw new BasicException(LocalRes.getIntString("exception.nodataset"));
        }
        
        List aSO = new ArrayList();
        while (SRS.next()) {
            aSO.add(SRS.getCurrent());
        }     
        return aSO;
    }
    
    // Utilidades

    /**
     *
     * @param SRS
     * @param offset
     * @param length
     * @return
     * @throws BasicException
     */
        public final List fetchPage(DataResultSet SRS, int offset, int length) throws BasicException {
        
        if (SRS == null) {
            throw new BasicException(LocalRes.getIntString("exception.nodataset"));
        }  
        
        if (offset < 0 || length < 0) {
            throw new BasicException(LocalRes.getIntString("exception.nonegativelimits"));
        }
         
        // Skip los primeros que no me importan
        while (offset > 0 && SRS.next()) {
            offset--;
        }
        
        // me traigo tantos como me han dicho
        List aSO = new ArrayList();
        if (offset == 0) {
            while (length > 0 && SRS.next()) {
                length--;
                aSO.add(SRS.getCurrent());
            }     
        }
        return aSO;            
    }
    
    /**
     *
     * @param SRS
     * @return
     * @throws BasicException
     */
    public final Object fetchOne(DataResultSet SRS) throws BasicException {
                       
        if (SRS == null) {
            throw new BasicException(LocalRes.getIntString("exception.nodataset"));
        } 
        
        if (SRS.next()) {
            return SRS.getCurrent();  
        } else {
            return null;
        }
    }
}
