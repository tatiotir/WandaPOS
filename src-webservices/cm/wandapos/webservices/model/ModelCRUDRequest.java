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
//@XmlType(name = "", propOrder = {"modelCRUD", "loginRequest"})
@XmlRootElement(name = "ModelCRUDRequest")
public class ModelCRUDRequest {
    
    @XmlElement(name = "modelCRUD", required = true)
    private ModelCRUD m_modelCRUD;
    
    @XmlElement(name = "loginRequest", required = true)
    private LoginRequest m_loginRequest;
    
    public ModelCRUDRequest() {
        this.m_modelCRUD = new ModelCRUD();
        this.m_loginRequest = new LoginRequest();
    }
    
    public ModelCRUDRequest(ModelCRUD modelCRUD, LoginRequest loginRequest) {
        this.m_modelCRUD = modelCRUD;
        this.m_loginRequest = loginRequest;
    }
    
    public ModelCRUD getModelCRUD() {
        return m_modelCRUD;
    }

    public void setModelCRUD(ModelCRUD modelCRUD) {
        this.m_modelCRUD = modelCRUD;
    }

    public LoginRequest getLoginRequest() {
        return m_loginRequest;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.m_loginRequest = loginRequest;
    }
}
