/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cm.wandapos.webservices.implementation;

import cm.wandapos.webservices.data.DatabaseDocument;
import cm.wandapos.webservices.interfaces.IResponseDocumentError;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ing. Tatioti Mbogning Raoul
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DatabaseDocument")
public class DatabaseDocumentError extends DatabaseDocument implements IResponseDocumentError {

    @XmlElement(name = "Error")
    private String m_error;
    
    public DatabaseDocumentError() {
        this.m_error = new String();
    }
    
    @Override
    public void setError(String error) {
        this.m_error = error;
    }

    @Override
    public String getError() {
        return m_error;
    }
}
