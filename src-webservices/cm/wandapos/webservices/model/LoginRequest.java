package cm.wandapos.webservices.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
