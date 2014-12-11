package cm.wandapos.webservices.interfaces;

import cm.wandapos.webservices.fields.DataSet;
import cm.wandapos.webservices.model.ModelCRUDRequest;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(name = "WandaPOS Model Oriented Web Services", targetNamespace = "")
@SOAPBinding(style=Style.RPC, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface IModelWebService {

    /* Model oriented web services */ 

    @WebResult(name = "readDataResponse", targetNamespace="http://example.org/complex")
    @WebMethod(operationName = "readData")
    public DataSet readData(@WebParam(name = "ModelCRUDRequest") ModelCRUDRequest request);
    
    @WebResult(name = "queryDataResponse", targetNamespace="http://example.org/complex")
    @WebMethod(operationName = "queryData")
    public DataSet[] queryData(@WebParam(name = "ModelCRUDRequest") ModelCRUDRequest request);

}
