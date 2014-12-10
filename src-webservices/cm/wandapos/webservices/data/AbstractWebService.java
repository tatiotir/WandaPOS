/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.wandapos.webservices.data;

import cm.wandapos.webservices.model.LoginRequest;
import com.openbravo.pos.forms.AppView;

/**
 *
 * @author TATIOTI-IDEAPAD
 */
public abstract class AbstractWebService {

    private AppView m_appView;
    
    public AbstractWebService(AppView app) {
        this.m_appView = app;
    }
    
    protected String login(LoginRequest loginRequest, String webService, String method, String serviceType) {
        return "";
    }

    protected String authenticate(String webServiceValue, String methodValue, String serviceTypeValue) {
        return "";
    }

}
