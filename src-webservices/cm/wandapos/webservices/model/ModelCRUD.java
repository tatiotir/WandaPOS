package cm.wandapos.webservices.model;

import cm.wandapos.webservices.enums.WebServiceAction;
import cm.wandapos.webservices.fields.DataRow;
import com.sun.xml.bind.XmlAccessorFactory;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
