/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cm.wandapos.webservices.implementation;

import cm.wandapos.webservices.data.AbstractWebService;
import cm.wandapos.webservices.fields.DataSet;
import cm.wandapos.webservices.interfaces.IModelWebService;
import cm.wandapos.webservices.model.LoginRequest;
import cm.wandapos.webservices.model.ModelCRUD;
import cm.wandapos.webservices.model.ModelCRUDRequest;
import com.openbravo.pos.forms.AppView;
import javax.jws.WebService;

/**
 *
 * @author TATIOTI-IDEAPAD
 */

@WebService(endpointInterface = "cm.wandapos.webservices.interfaces.IModelWebService")
public class ModelWebService extends AbstractWebService implements IModelWebService {

    public ModelWebService(AppView app) {
        super(app);
    }
    
    @Override
    public DataSet readData(ModelCRUDRequest request) {
        
        LoginRequest loginRequest = request.getLoginRequest();
        ModelCRUD modelCRUD = request.getModelCRUD();
        
        return null;
    }

    @Override
    public DataSet[] queryData(ModelCRUDRequest request) {
        return null;
        
    }
    
}
