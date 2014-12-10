package cm.wandapos.webservices.fields;

import java.util.ArrayList;
import java.util.List;

public class DataRow {

    private List<DataField> m_dataFields;

    public DataRow() {
        m_dataFields = new ArrayList<>();
    }
    
    public DataRow(List<DataField> dataFields) {
        this.m_dataFields = dataFields;
    }

    public String getValueOfField(String columnName) {
        for (DataField field : m_dataFields) {
            if (field.getColumnName().equalsIgnoreCase(columnName)) {
                return field.getValue();
            }
        }
        return "";
    }

    public List<DataField> getDataFields() {
        return m_dataFields;
    }

    public void addDataField(String columnName, String columnValue) {
        DataField field = new DataField();
        field.setColumnName(columnName);
        field.setValue(columnValue);
        m_dataFields.add(field);
    }
    
    public void addDataField(DataField field) {
        m_dataFields.add(field);
    }

    @Override
    public String toString() {
        String retVal = "";
        for (DataField field : m_dataFields) {
            retVal = retVal + field.toString() + ", ";
        }
        if (retVal.length() > 0) {
            retVal = retVal.substring(0, retVal.length() - 2);
        }
        return retVal;
    }
}
