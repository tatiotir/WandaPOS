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

package cm.wandapos.webservices.model;

/**
 * 
 * @author Ing. Tatioti Mbogning Raoul
 */
public class MWebServiceType {
    
    private String m_id;
    private String name;
    private String value;
    private String help;
    private String tableID;

    public MWebServiceType(String id, String name, String value, String tableID) {
        this.m_id = id;
        this.name = name;
        this.value = value;
        this.tableID = tableID;
    }
    
    public MWebServiceType(String id, String name, String value, String help, String tableID) {
        this.m_id = id;
        this.name = name;
        this.value = value;
        this.help = help;
        this.tableID = tableID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }
    
    public String getId() {
        return this.m_id;
    }
    
    public void setId(String id) {
        this.m_id = id;
    }
}
