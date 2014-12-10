package cm.wandapos.webservices.model;

public class LoginRequest {

    private String m_user;
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
