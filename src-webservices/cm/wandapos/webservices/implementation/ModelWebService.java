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

package cm.wandapos.webservices.implementation;

import cm.wandapos.webservices.data.AbstractWebService;
import cm.wandapos.webservices.data.DatabaseDocument;
import cm.wandapos.webservices.data.StandardResponseDocument;
import cm.wandapos.webservices.fields.DataSet;
import cm.wandapos.webservices.interfaces.IModelWebService;
import cm.wandapos.webservices.model.LoginRequest;
import cm.wandapos.webservices.model.ModelCRUD;
import cm.wandapos.webservices.model.ModelCRUDRequest;
import com.openbravo.pos.forms.AppView;
import javax.jws.WebService;

/**
 *
 * @author Ing. Tatioti Mbogning Raoul
 */

@WebService(endpointInterface = "cm.wandapos.webservices.interfaces.IModelWebService")
public class ModelWebService extends AbstractWebService implements IModelWebService {

    private final String m_webServiceName = new String("ModelWebService");
    
    public ModelWebService(AppView app) {
        super(app);
    }

    @Override
    public DatabaseDocument readData(ModelCRUDRequest request) {
        
        /** 
         * Login
         * Return an error message if there is an error
         * Return null if there is no errors 
         */
        String error = login(request.getLoginRequest(), m_webServiceName, "readData", request.getModelCRUD().getServiceType());
        if (error != null)
            return new DatabaseDocumentError(error);
        
        // Read data
        return new DatabaseDocument();
    }

    @Override
    public DatabaseDocument queryData(ModelCRUDRequest request) {
        
        /** 
         * Login
         * Return an error message if there is an error
         * Return null if there is no errors 
         */
        String error = login(request.getLoginRequest(), m_webServiceName, "queryData", request.getModelCRUD().getServiceType());
        if (error != null)
            return new DatabaseDocumentError(error);
        
        // Query data
        return new DatabaseDocument();
    }

    @Override
    public StandardResponseDocument createData(ModelCRUDRequest request) {
        
        /** 
         * Login
         * Return an error message if there is an error
         * Return null if there is no errors 
         */
        String error = login(request.getLoginRequest(), m_webServiceName, "createData", request.getModelCRUD().getServiceType());
        if (error != null)
            return new StandardResponseError(error);
        
        // Create data
        return new StandardResponseDocument();
    }   
}
