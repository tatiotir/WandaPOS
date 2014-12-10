package cm.wandapos.webservices.fields;


public class DataField {

    private String m_value;
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
