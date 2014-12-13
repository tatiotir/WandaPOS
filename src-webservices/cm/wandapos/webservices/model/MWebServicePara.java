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
public class MWebServicePara {
    
    public static final String PARA_TABLENAME = "TableName";
    public static final String PARA_RECORDID = "RecordID";
    public static final String PARA_ACTION = "Action";
    
    private String name;
    private String type;
    private String constantValue;
    private String webServiceTypeID;

    public MWebServicePara(String webServiceTypeID, String name, String type, String constantValue) {
        this.name = name;
        this.type = type;
        this.constantValue = constantValue;
        this.webServiceTypeID = webServiceTypeID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConstantValue() {
        return constantValue;
    }

    public void setConstantValue(String constantValue) {
        this.constantValue = constantValue;
    }

    public String getWebServiceTypeID() {
        return webServiceTypeID;
    }

    public void setWebServiceTypeID(String webServiceTypeID) {
        this.webServiceTypeID = webServiceTypeID;
    }
    
}
