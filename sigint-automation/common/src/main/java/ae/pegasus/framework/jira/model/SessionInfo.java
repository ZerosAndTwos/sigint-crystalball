package ae.pegasus.framework.jira.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dm on 4/5/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionInfo {

    private Session session;
    private LoginInfo loginInfo;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }
}
