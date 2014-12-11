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

package cm.wandapos.webservices.model;

import cm.wandapos.webservices.enums.WebServiceAction;
import cm.wandapos.webservices.fields.DataRow;
import com.sun.xml.bind.XmlAccessorFactory;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author Ing. Tatioti Mbogning Raoul
 */

@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(propOrder = {"serviceType", "tableName", "action", "recordID", "dataRow"})
@XmlRootElement(name = "ModelCRUD")
public class ModelCRUD {

    @XmlElement(name = "serviceType", required = true)
    private String m_serviceType;
    
    @XmlElement(name = "tableName")
    private String m_tableName;
    
    @XmlElement(name = "recordID")
    private int m_recordID;
    
    @XmlElement(name = "dataRow")
    private DataRow m_dataRow;
    
    
    private WebServiceAction m_action;

    public ModelCRUD() {
        this.m_action = WebServiceAction.Read;
        this.m_tableName = new String();
        this.m_recordID = -1;
        this.m_dataRow = new DataRow();
        this.m_serviceType = new String();
    }
    
    public ModelCRUD(String serviceType) {
        this.m_serviceType = serviceType;
    }

    public ModelCRUD(String serviceType, int recordID) {
        this(serviceType);
        this.m_recordID = recordID;
    }

    public ModelCRUD(String serviceType, String tableName, int recordID, WebServiceAction action, DataRow dataRow) {
        this(serviceType, recordID);
        this.m_tableName = tableName;
        this.m_action = action;
        this.m_dataRow = dataRow;
    }
    
    public String getServiceType() {
        return m_serviceType;
    }

    public void setServiceType(String serviceType) {
        this.m_serviceType = serviceType;
    }

    public String getTableName() {
        return m_tableName;
    }

    public void setTableName(String tableName) {
        this.m_tableName = tableName;
    }

    public int getRecordID() {
        return m_recordID;
    }

    public void setRecordID(int recordID) {
        this.m_recordID = recordID;
    }

    public DataRow getDataRow() {
        return m_dataRow;
    }

    public void setDataRow(DataRow dataRow) {
        this.m_dataRow = dataRow;
    }

    public WebServiceAction getAction() {
        return m_action;
    }

    public void setAction(WebServiceAction action) {
        this.m_action = action;
    }
}
