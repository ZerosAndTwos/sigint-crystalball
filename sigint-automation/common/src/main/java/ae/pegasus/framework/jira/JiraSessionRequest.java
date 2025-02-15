package ae.pegasus.framework.jira;

import ae.pegasus.framework.app_context.properties.G4Properties;
import ae.pegasus.framework.app_context.properties.JiraProperties;
import ae.pegasus.framework.http.requests.HttpRequest;
import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.jira.model.User;
import org.apache.log4j.Logger;


class JiraSessionRequest extends HttpRequest {

    private final static String URI = "/rest/auth/latest/session";
    private static Logger log = Logger.getLogger(JiraSessionRequest.class);
    private static JiraProperties properties = G4Properties.getJiraProperties();

    JiraSessionRequest() {
        super(URI);

        User user = new User();
        String username = properties.getUsername();
        log.debug("Using username:"+username);
        String password = properties.getPassword();
        user.setUsername(username);
        log.debug("Using password:"+password);
        user.setPassword(password);

        this
                .setHttpMethod(HttpMethod.POST)
                .setPayload(user);
    }
}
