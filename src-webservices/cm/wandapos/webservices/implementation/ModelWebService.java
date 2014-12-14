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
import cm.wandapos.webservices.fields.DataField;
import cm.wandapos.webservices.fields.DataRow;
import cm.wandapos.webservices.fields.DataSet;
import cm.wandapos.webservices.interfaces.IModelWebService;
import cm.wandapos.webservices.model.MWebServicePara;
import cm.wandapos.webservices.model.MWebServiceType;
import cm.wandapos.webservices.model.ModelCRUD;
import cm.wandapos.webservices.model.ModelCRUDRequest;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.forms.AppView;
import com.truemesh.squiggle.SelectQuery;
import com.truemesh.squiggle.Table;
import com.truemesh.squiggle.criteria.MatchCriteria;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
            
            MWebServiceType webServiceType = m_dlWebService.getWebServiceType(request.getModelCRUD().getServiceType());

            // Validate web service type parameters
            error = validateParameters(request.getModelCRUD(), webServiceType);
            if ((error != null) && error.length() > 0) {
                responseDocument.setError(error);
                return responseDocument;
            }

            List<String> outputFields = m_dlWebService.getOutputFields(webServiceType.getId());

            // Contruct SQL Query
            SelectQuery query = new SelectQuery();
            Table table = new Table(request.getModelCRUD().getTableName());

            // Add output fields and construct SerializerRead
            SerializerReadBasic serializerRead = new SerializerReadBasic();
            for (String column : outputFields) {
                query.addColumn(table, column);
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
                    dataField.setColumnName(outputFields.get(i));
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

            MWebServiceType webServiceType = m_dlWebService.getWebServiceType(request.getModelCRUD().getServiceType());

            // Validate web service type parameters
            error = validateParameters(request.getModelCRUD(), webServiceType);
            if ((error != null) && error.length() > 0) {
                responseDocument.setError(error);
                return responseDocument;
            }

            List<String> outputFields = m_dlWebService.getOutputFields(webServiceType.getId());
            List<String[]> inputFields = m_dlWebService.getInputFields(webServiceType.getId());

            // Validate input columns
            if (inputFields != null) {
                List<String> inputFieldColumns = new ArrayList<>();
                for (String[] field : inputFields)
                    inputFieldColumns.add(field[1]);
                
                error = validateInputColumns(request.getModelCRUD(), inputFieldColumns);
                if ((error != null) && error.length() > 0) {
                    responseDocument.setError(error);
                    return responseDocument;
                }
            }
            
            // Contruct SQL Query
            SelectQuery query = new SelectQuery();
            Table table = new Table(request.getModelCRUD().getTableName());

            // Add output columns and construct SerializerRead
            SerializerReadBasic serializerRead = new SerializerReadBasic();
            for (String column : outputFields) {
                query.addColumn(table, column);
                serializerRead.addClasse(Datas.OBJECT);
            }

            // Add where clause using input fields
            for (DataField inputField : request.getModelCRUD().getDataRow().getDataFields()) {
                // Get input column reference
                String referenceName = m_dlWebService.getReferenceByID(inputField.getColumnName());
                switch (referenceName) {
                    case "ID":
                        query.addCriteria(new MatchCriteria(table, inputField.getColumnName(), MatchCriteria.EQUALS, inputField.getValue()));
                        break;
                    case "String":
                        query.addCriteria(new MatchCriteria(table, inputField.getColumnName(), MatchCriteria.LIKE, inputField.getValue()));
                        break;
                }
            }
            
            List<Object[]> listDatas = m_dlWebService.getDatas(m_appView.getSession(), query.toString(), serializerRead);

            // Construct the DataSet
            DataSet dataSet = new DataSet();
            for (Object[] data : listDatas) {
                DataRow dataRow = new DataRow();
                for (int i = 0; i < data.length; ++i) {
                    DataField dataField = new DataField();
                    dataField.setColumnName(outputFields.get(i));
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

    private String validateParameters(ModelCRUD modelCRUD, MWebServiceType webServiceType) {
        try {

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

    private String validateInputColumns(ModelCRUD modelCRUD, List<String> inputColumns) {
        
        if (modelCRUD.getDataRow().getDataFields().size() != inputColumns.size())
            return "You request has more input fields than the configured web service";
        
        List<String> requestInputColumn = new ArrayList<>();
        for (DataField dataField : modelCRUD.getDataRow().getDataFields()) {
            requestInputColumn.add(dataField.getColumnName());
        }
        
        // Sort request input column
        Collections.sort(requestInputColumn);
        
        if (!requestInputColumn.equals(inputColumns))
            return "The input fields in your request does not correspond to the input field configured in the web service";
        
        return null;
    }
}
