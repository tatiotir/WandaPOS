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
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSystem;

/**
 *
 * @author ing. Tatioti Mbogning Raoul
 */
public abstract class AbstractWebService {

    private AppView m_appView;
    
    public AbstractWebService(AppView app) {
        this.m_appView = app;
    }
    
    protected String login(LoginRequest loginRequest, String webService, String method, String serviceType) {
        DataLogicSystem dataLogicSystem = (DataLogicSystem)m_appView.getBean("com.openbravo.pos.forms.DataLogicSystem");
        
        return "";
    }

    protected String authenticate(String webServiceValue, String methodValue, String serviceTypeValue) {
        return "";
    }

}
