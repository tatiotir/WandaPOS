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

package cm.wandapos.webservices.data;

import cm.wandapos.webservices.model.LoginRequest;
import cm.wandapos.webservices.model.MWebService;
import cm.wandapos.webservices.model.MWebServiceMethod;
import cm.wandapos.webservices.model.MWebServiceType;
import com.openbravo.basic.BasicException;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSystem;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ing. Tatioti Mbogning Raoul
 */
public abstract class AbstractWebService {

    protected final AppView m_appView;
    
    public AbstractWebService(AppView app) {
        this.m_appView = app;
    }
    
    protected String login(LoginRequest loginRequest, String webService, String method, String serviceType) {
        DataLogicSystem dataLogicSystem = (DataLogicSystem)m_appView.getBean("com.openbravo.pos.forms.DataLogicSystem");
        
        // Try to login the user
        AppUser appUser = null;
        try {
            appUser = dataLogicSystem.getAppUser(loginRequest.getUser(), loginRequest.getPass());
            
            if ((appUser == null) || !appUser.authenticate())
                return "Error during the login of the user : " + loginRequest.getUser();
        } catch (BasicException ex) {
            return "Error during the login of this user : " + loginRequest.getUser();
        }
        return authenticate(webService, method, serviceType);
    }

    protected String authenticate(String webServiceValue, String methodValue, String serviceTypeValue) {
        DataLogicWebService dataLogicWebService = (DataLogicWebService)m_appView.getBean("com.openbravo.pos.forms.DataLogicWebService");
        
        // Get the web service (ModelWebService)
        MWebService webService = null;
        try {
            webService = dataLogicWebService.getWebService(webServiceValue);
            
            if ((webService == null))
                return "Web service " + webServiceValue + " not registered";
            else if (!webService.getName().equals(webServiceValue))
                return "Web service " + webServiceValue + " not registered";
        } catch (BasicException ex) {
            return "Web service " + webServiceValue + " not registered";
        }
        
        // Get the web service method
        MWebServiceMethod webServiceMethod = null;
        try {
            webServiceMethod = dataLogicWebService.getMethod(webService.getId(), methodValue);
            
            if (webServiceMethod == null) 
                return "Method " + methodValue + " not registered";
            else if (webServiceMethod.getValue().equals(methodValue)) 
                return "Method " + methodValue + " not registered";
            
        } catch (BasicException ex) {
            return "Method " + methodValue + " not registered";
        }
        
        // Get the web service type
        MWebServiceType webServiceType = null;
        try {
            webServiceType = dataLogicWebService.getWebServiceType(serviceTypeValue);
            
            if (webServiceType == null) 
                return "Service type " + serviceTypeValue + " not configured";
            else if (webServiceType.getValue().equals(serviceTypeValue))
                return "Service type " + serviceTypeValue + " not configured";
            
        } catch (BasicException ex) {
            return "Service type " + serviceTypeValue + " not configured";
        }
        
        return null;
    }

}
