/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.data.loader;

import com.openbravo.basic.BasicException;

/**
 *
 * @author TATIOTI-IDEAPAD
 */
public class SequenceForMongoDB extends BaseSentence {

    public SequenceForMongoDB(Session s, String sequence)
    {
        
    }
    
    @Override
    public DataResultSet openExec(Object params) throws BasicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DataResultSet moreResults() throws BasicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeExec() throws BasicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
