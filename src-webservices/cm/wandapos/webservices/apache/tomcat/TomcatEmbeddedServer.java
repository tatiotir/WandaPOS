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
//    along with Wanda POS.  If not, see <http://www.gnu.org/licenses/>

package cm.wandapos.webservices.apache.tomcat;

import cm.wandapos.webservices.implementation.ModelWebService;
import com.openbravo.pos.forms.AppView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Endpoint;

/**
 * This class start a tomcat server and publish WandaPOS's Web Services
 *
 * @author Ing. Tatioti Mbogning Raoul
 */
public class TomcatEmbeddedServer {

    private static final Logger logger = Logger.getLogger("cm.wandapos.webservices.apache.tomcat.TomcatEmbeddedServer");
    
    private Endpoint m_endPoint;
    private final AppView m_app;
    
    public TomcatEmbeddedServer(AppView app) {
        logger.log(Level.INFO, "Creating Tomcat Server.");
        this.m_app = app;
    }
    
    public void start() {
        logger.log(Level.INFO, "Starting Tomcat Server");
        logger.log(Level.INFO, "Tomcat Server Host : {0}", m_app.getProperties().getProperty("tomcat.host"));
        logger.log(Level.INFO, "Tomcat Server Port : {0}", m_app.getProperties().getProperty("tomcat.port"));
        
        // P
        m_endPoint = Endpoint.publish("http://" + m_app.getProperties().getProperty("tomcat.host") + ":" + 
                m_app.getProperties().getProperty("tomcat.port") + "/WandaPOS/WebService", new ModelWebService(m_app));
    }
    
    public void stop() {
        logger.log(Level.INFO, "Stopping Tomcat Server");
        m_endPoint.stop();
    }
}
