/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
