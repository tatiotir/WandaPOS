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
import cm.wandapos.webservices.data.DataLogicWebService;
import cm.wandapos.webservices.data.DatabaseDocument;
import cm.wandapos.webservices.data.StandardResponseDocument;
import cm.wandapos.webservices.fields.DataField;
import cm.wandapos.webservices.fields.DataRow;
import cm.wandapos.webservices.fields.DataSet;
import cm.wandapos.webservices.interfaces.IModelWebService;
import cm.wandapos.webservices.model.LoginRequest;
import cm.wandapos.webservices.model.MWebServicePara;
import cm.wandapos.webservices.model.MWebServiceType;
import cm.wandapos.webservices.model.ModelCRUD;
import cm.wandapos.webservices.model.ModelCRUDRequest;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSystem;
import com.truemesh.squiggle.SelectQuery;
import com.truemesh.squiggle.Table;
import com.truemesh.squiggle.criteria.MatchCriteria;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;

/**
 *
 * @author Ing. Tatioti Mbogning Raoul
 */
@WebService(endpointInterface = "cm.wandapos.webservices.interfaces.IModelWebService")
public class ModelWebService extends AbstractWebService implements IModelWebService {

    private final String m_webServiceName = "ModelWebService";

    public ModelWebService(AppView app) {
        super(app);
    }

    @Override
    public DatabaseDocument readData(ModelCRUDRequest request) {

        DatabaseDocument responseDocument = new DatabaseDocument();
        try {
            /**
             * Login Return an error message if there is an error Return null if
             * there is no errors
             */
            String error = login(request.getLoginRequest(), m_webServiceName, "readData", request.getModelCRUD().getServiceType());
            if ((error != null) && error.length() > 0) {
                responseDocument.setError(error);
                return responseDocument;
            }
            
            // Validate web service type parameters
            error = validateParameters(request.getModelCRUD());
            if ((error != null) && error.length() > 0) {
                responseDocument.setError(error);
                return responseDocument;
            }
            
            MWebServiceType webServiceType = m_dlWebService.getWebServiceType(request.getModelCRUD().getServiceType());
            List outputColumnNames = m_dlWebService.getOutputColumnNames(webServiceType.getId());
            
            // Contruct SQL Query
            SelectQuery query = new SelectQuery();
            Table table = new Table(request.getModelCRUD().getTableName());
            
            // Add Column and construc SerializerRead
            SerializerReadBasic serializerRead = new SerializerReadBasic();
            for (Object column : outputColumnNames) {
                query.addColumn(table, (String) column);
                serializerRead.addClasse(Datas.OBJECT);
            }
            
            // Add where clause
            query.addCriteria(new MatchCriteria(table, "ID", MatchCriteria.EQUALS, "?"));
            
            List<Object[]> listDatas = m_dlWebService.getDatas(m_appView.getSession(), query.toString(), new SerializerWriteBasic(new Datas[]{Datas.STRING}), 
                    serializerRead, request.getModelCRUD().getRecordID());
            
            // Construct the DataSet
            DataSet dataSet = new DataSet();
            for (Object[] data : listDatas) {
                DataRow dataRow = new DataRow();
                for (int i = 0; i < data.length; ++i) {
                    DataField dataField = new DataField();
                    dataField.setColumnName((String)outputColumnNames.get(i));
                    dataField.setValue((String) data[i]);
                    dataRow.addDataField(dataField);
                }
                dataSet.addDataRow(dataRow);
            }
            
            responseDocument.setStartRow(1);
            responseDocument.setNumRows(listDatas.size());
            responseDocument.setTotalRows(listDatas.size());
            responseDocument.setDataSet(dataSet);
            
        } catch (BasicException ex) {
            DatabaseDocument errorDocument = new DatabaseDocument();
            errorDocument.setError(ex.getMessage());
            return errorDocument;
        }
        return responseDocument;
    }

    @Override
    public DatabaseDocument queryData(ModelCRUDRequest request) {

        DatabaseDocument responseDocument = new DatabaseDocument();
        /**
         * Login Return an error message if there is an error Return null if
         * there is no errors
         */
        String error = login(request.getLoginRequest(), m_webServiceName, "readData", request.getModelCRUD().getServiceType());
        if ((error != null) && error.length() > 0) {
            responseDocument.setError(error);
            return responseDocument;
        }

        // Query data
        return responseDocument;
    }

    @Override
    public StandardResponseDocument createData(ModelCRUDRequest request) {

        StandardResponseDocument responseDocument = new StandardResponseDocument();
        /**
         * Login Return an error message if there is an error Return null if
         * there is no errors
         */
        String error = login(request.getLoginRequest(), m_webServiceName, "readData", request.getModelCRUD().getServiceType());
        if ((error != null) && error.length() > 0) {
            responseDocument.setError(error);
            return responseDocument;
        }

        // Create data
        return responseDocument;
    }

    private String validateParameters(ModelCRUD modelCRUD) {
        try {
            MWebServiceType webServiceType = m_dlWebService.getWebServiceType(modelCRUD.getServiceType());

            List webServiceParameters = null;
            webServiceParameters = m_dlWebService.getParamaters(webServiceType.getId());

            if (webServiceParameters == null) {
                return "The service : " + modelCRUD.getServiceType() + " is not well configured.";
            } else {

                for (Object webServiceParameter : webServiceParameters) {
                    MWebServicePara parameter = (MWebServicePara) webServiceParameter;
                    if (parameter.getName().equals(MWebServicePara.PARA_ACTION)) {
                        if (!modelCRUD.getAction().equals(parameter.getConstantValue())) {
                            return "The action parameter" + modelCRUD.getAction() + " is invalid";
                        }
                    } else if (parameter.getName().equals(MWebServicePara.PARA_TABLENAME)) {
                        if (!modelCRUD.getTableName().equals(parameter.getConstantValue())) {
                            return "The tablename paramater : " + modelCRUD.getTableName() + " is invalid";
                        }
                    }
                }
            }

        } catch (BasicException ex) {

        }
        return null;
    }
}
