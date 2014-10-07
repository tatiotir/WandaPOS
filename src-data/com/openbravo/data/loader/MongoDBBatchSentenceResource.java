/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.data.loader;

import com.openbravo.basic.BasicException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author TATIOTI-IDEAPAD
 */
public class MongoDBBatchSentenceResource extends MongoDBBatchSentence {
    private String m_sResScript;
    
    /** Creates a new instance of BatchSentenceResource
     * @param s
     * @param resscript */
    public MongoDBBatchSentenceResource(Session s, String resscript) {
        super(s);
        m_sResScript = resscript;
    }
    
    /**
     *
     * @return
     * @throws BasicException
     */
    @Override
    protected Reader getReader() throws BasicException {
        
        InputStream in = BatchSentenceResource.class.getResourceAsStream(m_sResScript);
        
        if (in == null) {
            throw new BasicException(LocalRes.getIntString("exception.nosentencesfile"));
        } else {  
            try {
                return new InputStreamReader(in, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new BasicException(LocalRes.getIntString("exception.nosentencesfile"), ex);
            }
        }
    }
}
