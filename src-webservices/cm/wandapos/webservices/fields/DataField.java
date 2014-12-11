package cm.wandapos.webservices.fields;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
