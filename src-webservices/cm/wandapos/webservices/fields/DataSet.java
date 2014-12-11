/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cm.wandapos.webservices.fields;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ing. Tatioti Mbogning Raoul
 */

@XmlRootElement(name = "DataSet")
public class DataSet {
    
    @XmlElement(name = "DataRow")
    private List<DataRow> m_dataRows;
    
    public DataSet() {
        this.m_dataRows = new ArrayList<>();
    }
    
    public DataSet(List<DataRow> dataRows) {
        this.m_dataRows = dataRows;
    }
    
    public void addDataRow(DataRow dataRow) {
        m_dataRows.add(dataRow);
    }
}
