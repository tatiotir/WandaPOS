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

package cm.wandapos.webservices.interfaces;

import cm.wandapos.webservices.data.DatabaseDocument;
import cm.wandapos.webservices.data.StandardResponseDocument;
import cm.wandapos.webservices.fields.DataSet;
import cm.wandapos.webservices.model.ModelCRUDRequest;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * 
 * @author Ing. Tatioti Mbogning Raoul
 */

@WebService(name = "WandaPOS Model Oriented Web Services", targetNamespace = "http://wandapos.com")
@SOAPBinding(style=Style.RPC, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface IModelWebService {

    /* Model oriented web services */ 

    @WebResult(name = "DatabaseDocument")
    @WebMethod(operationName = "readData")
    public DatabaseDocument readData(ModelCRUDRequest request);
    
    @WebResult(name = "DatabaseDocument")
    @WebMethod(operationName = "queryData")
    public DatabaseDocument queryData(ModelCRUDRequest request);
    
    @WebResult(name = "standardResponse")
    @WebMethod(operationName = "createData")
    public StandardResponseDocument createData(ModelCRUDRequest request);
}
