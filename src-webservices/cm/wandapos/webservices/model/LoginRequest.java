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

package cm.wandapos.webservices.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Ing. Tatioti Mbogning Raoul
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "loginRequest")
public class LoginRequest {

    @XmlElement(name = "user", required = true)
    private String m_user;
    
    @XmlElement(name = "pass", required = true)
    private String m_pass;
    
    public LoginRequest() {
        m_user = new String();
        m_pass = new String();
    }
    
    public LoginRequest(String user, String pass) {
        this.m_user = user;
        this.m_pass = pass;
    }
    
    public String getUser() {
        return m_user;
    }

    public void setUser(String m_user) {
        this.m_user = m_user;
    }

    public String getPass() {
        return m_pass;
    }

    public void setPass(String m_pass) {
        this.m_pass = m_pass;
    }
}
