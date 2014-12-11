/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cm.wandapos.webservices.data;

import cm.wandapos.webservices.fields.DataSet;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ing. Tatioti Mbogning Raoul
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DatabaseDocument")
public class DatabaseDocument {
    
    @XmlElement(name = "DataSet", required = true)
    private DataSet m_dataSet;
    
    @XmlAttribute(name = "numRows", required = true)
    private int m_numRows;
    
    @XmlAttribute(name = "totalRows", required = true)
    private int m_totalRows;
    
    @XmlAttribute(name = "startRow", required = true) 
    private int m_startRow;
    
    public DatabaseDocument() {
        this.m_numRows = -1;
        this.m_totalRows = -1;
        this.m_startRow = -1;
    }
    
    public int getNumRows() {
        return m_numRows;
    }

    public void setNumRows(int m_numRows) {
        this.m_numRows = m_numRows;
    }

    public int getTotalRows() {
        return m_totalRows;
    }

    public void setTotalRows(int m_totalRows) {
        this.m_totalRows = m_totalRows;
    }

    public int getM_startRow() {
        return m_startRow;
    }

    public void setStartRow(int m_startRow) {
        this.m_startRow = m_startRow;
    }
}
