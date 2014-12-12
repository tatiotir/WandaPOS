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

package cm.wandapos.webservices.data;

import cm.wandapos.webservices.fields.DataSet;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ing. Tatioti Mbogning Raoul
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DatabaseDocument")
public class DatabaseDocument {
    
    @XmlElement(name = "DataSet")
    private DataSet m_dataSet;
    
    @XmlAttribute(name = "numRows")
    private int m_numRows;
    
    @XmlAttribute(name = "totalRows")
    private int m_totalRows;
    
    @XmlAttribute(name = "startRow") 
    private int m_startRow;
    
    @XmlElement(name = "Error")
    private String m_error;
    
    public int getNumRows() {
        return m_numRows;
    }

    public void setNumRows(int numRows) {
        this.m_numRows = numRows;
    }

    public int getTotalRows() {
        return m_totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.m_totalRows = totalRows;
    }

    public int getStartRow() {
        return m_startRow;
    }

    public void setStartRow(int startRow) {
        this.m_startRow = startRow;
    }
    
    public DataSet getDataSet() {
        return m_dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.m_dataSet = dataSet;
    }
    
    public void setError(String error) {
        this.m_error = error;
    }

    public String getError() {
        return m_error;
    }
}
