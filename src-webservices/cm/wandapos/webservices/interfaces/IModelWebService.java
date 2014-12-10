package cm.wandapos.webservices.interfaces;

import cm.wandapos.webservices.fields.DataSet;
import cm.wandapos.webservices.model.ModelCRUDRequest;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.RPC)
public interface IModelWebService {

    /* Model oriented web services */ 

    @WebMethod public DataSet readData(@WebParam(name = "ModelCRUDRequest") ModelCRUDRequest request);
    @WebMethod public DataSet[] queryData(@WebParam(name = "ModelCRUDRequest") ModelCRUDRequest request);

}
