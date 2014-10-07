/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.data.loader;

/**
 *
 * @author TATIOTI-IDEAPAD
 */
public class SessionDBMongoDB implements SessionDB {

    @Override
    public String TRUE() {
        return "TRUE";
    }

    @Override
    public String FALSE() {
        return "FALSE";
    }

    @Override
    public String INTEGER_NULL() {
        return "";
    }

    @Override
    public String CHAR_NULL() {
        return "";
    }

    @Override
    public String getName() {
        return "MongoDB";
    }

    @Override
    public SentenceFind getSequenceSentence(Session s, String sequence) {
        return new SequenceForMongoDB(s, sequence);
    }

    @Override
    public SentenceFind resetSequenceSentence(Session s, String sequence) {
        return new SequenceForMongoDB(s, sequence);
    }
    
}
