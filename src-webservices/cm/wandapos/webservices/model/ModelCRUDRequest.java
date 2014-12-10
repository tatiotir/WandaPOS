/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cm.wandapos.webservices.model;

/**
 *
 * @author Ing. Tatioti Mbogning Raoul
 */
public class ModelCRUDRequest {
    
    private ModelCRUD m_modelCRUD;
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
