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
//    Wanda POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Wanda POS.  If not, see <http://www.gnu.org/licenses/>.

package cm.wandapos.webservices.fields;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author Ing. Tatioti Mbogning Raoul
 */

@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "", propOrder = {"val"})
@XmlRootElement(name = "field")
public class DataField {

    @XmlElement(name = "val", required = true)
    private String m_value;
    
    @XmlAttribute(name = "columnName", required = true)
    private String m_columnName;

    public DataField() {
        this.m_value = new String();
        this.m_columnName = new String();
    }

    public DataField(String columnName, String value) {
        this.m_columnName = columnName;
        this.m_value = value;
    }

    public void setColumnName(String columnName) {
        this.m_columnName = columnName;
    }

    public void setValue(String value) {
        this.m_value = value;
    }

    public String getColumnName() {
        return m_columnName;
    }

    public String getValue() {
        return m_value;
    }

    @Override
    public String toString() {
        return m_columnName + " = " + m_value;
    }

}
