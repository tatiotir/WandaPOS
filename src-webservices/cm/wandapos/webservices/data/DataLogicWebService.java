/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cm.wandapos.webservices.data;

import cm.wandapos.webservices.model.MWebServiceMethod;
import cm.wandapos.webservices.model.MWebService;
import cm.wandapos.webservices.model.MWebServicePara;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceFind;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.List;

/**
 *
 * @author Ing. Tatioti Mbogning Raoul
 */
public class DataLogicWebService extends BeanFactoryDataSingle {

    private SentenceList m_webServices;
    private SentenceFind m_webServiceByValue;
    private SentenceList m_webServiceMethods;
    private SentenceFind m_webServiceMethodByValue;
    private SentenceList m_webServiceParamaters;
    private SentenceFind m_webServiceParamaterByName;
    private SentenceFind m_webServiceInputColumnNames;
    private SentenceFind m_webServiceOutputColumnNames;
    
    // SerializerRead to Read Web Service Methods
    private SerializerRead webServiceMethodRead;
    
    // SerializerRead to Read Web Service
    private SerializerRead webServiceRead;
    
    // SerializerRead to Read Web Service Type Paramaters
    private SerializerRead webServiceParamaterRead;
    
    
    @Override
    public void init(Session s) {
        
        webServiceMethodRead = new SerializerRead() {

            @Override
            public Object readValues(DataRead dr) throws BasicException {
                return new MWebServiceMethod(
                dr.getString(0),
                dr.getString(1),
                dr.getString(2),
                dr.getString(3),
                dr.getString(4));
            }
        };
        
        webServiceRead = new SerializerRead() {

            @Override
            public Object readValues(DataRead dr) throws BasicException {
                return new MWebService(
                dr.getString(0),
                dr.getString(1),
                dr.getString(2),
                dr.getString(3));
            }
        }; 
        
        webServiceParamaterRead = new SerializerRead() {

            @Override
            public Object readValues(DataRead dr) throws BasicException {
                return new MWebServicePara(
                dr.getString(0),
                dr.getString(1),
                dr.getString(2),
                dr.getString(3));
            }
        };
        
        m_webServiceMethods = new PreparedSentence(s, "SELECT WEBSERVICEID, NAME, VALUE, DESCRIPTION, HELP FROM WEBSERVICEMETHOD WHERE WEBSERVICEID = ? ORDER BY VALUE", 
                SerializerWriteString.INSTANCE, webServiceMethodRead);
        
        m_webServiceMethodByValue = new PreparedSentence(s, "SELECT WEBSERVICEID, NAME, VALUE, DESCRIPTION, HELP FROM WEBSERVICEMETHOD WHERE WEBSERVICEID = ? AND VALUE = ?", 
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), webServiceMethodRead);
        
        m_webServices = new StaticSentence(s, "SELECT NAME, VALUE, DESCRIPTION, HELP FROM WEBSERVICE", 
                null, webServiceRead);
        
        m_webServiceByValue = new PreparedSentence(s, "SELECT NAME, VALUE, DESCRIPTION, HELP FROM WEBSERVICE WHERE VALUE = ?", 
                new SerializerWriteBasic(new Datas[]{Datas.STRING}), webServiceRead);
        
        m_webServiceParamaters = new PreparedSentence(s, "SELECT WEBSERVICETYPEID, NAME, TYPE, CONSTANT_VALUE FROM WEBSERVICEPARA WHERE WEBSERVICETYPEID = ? ORDER BY NAME", 
                SerializerWriteString.INSTANCE, webServiceParamaterRead); 
        
        m_webServiceParamaterByName = new PreparedSentence(s, "SELECT WEBSERVICETYPEID, NAME, TYPE, CONSTANT_VALUE FROM WEBSERVICEPARA WHERE WEBSERVICETYPEID = ? AND NAME = ?", 
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), webServiceParamaterRead); 
        
        m_webServiceInputColumnNames = new PreparedSentence(s, "SELECT C.NAME FROM WEBSERVICEFIELDINPUT F, COLUMN C WHERE F.WEBSERVICETYPEID = ? AND C.ID = F.ID ORDER BY C.NAME",
        SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING}));
        
        m_webServiceInputColumnNames = new PreparedSentence(s, "SELECT C.NAME FROM WEBSERVICEFIELDOUPUT F, COLUMN C WHERE F.WEBSERVICETYPEID = ? AND C.ID = F.ID ORDER BY C.NAME",
        SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING}));
    }
    
    public List getWebServices() throws BasicException {
        return m_webServices.list();
    }
    
    public MWebService getWebService(String webServiceValue) throws BasicException {
        return (MWebService)m_webServiceByValue.find(webServiceValue);
    }
    
    public List getMethods(String webServiceID) throws BasicException {
        return m_webServiceMethods.list(webServiceID);
    }
    
    public MWebServiceMethod getMethod(String webServiceID, String methodValue) throws BasicException {
        return (MWebServiceMethod)m_webServiceMethodByValue.find(webServiceID, methodValue);
    }
    
    public List getParamaters(String webServiceTypeID) throws BasicException {
        return m_webServiceParamaters.list(webServiceTypeID);
    }
    
    public MWebServicePara getParamater(String webServiceTypeID, String paramaterName) throws BasicException {
        return (MWebServicePara)m_webServiceParamaterByName.find(webServiceTypeID, paramaterName);
    }
}
