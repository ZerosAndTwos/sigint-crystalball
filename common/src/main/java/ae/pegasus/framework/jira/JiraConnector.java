package ae.pegasus.framework.jira;

import ae.pegasus.framework.app_context.properties.G4Properties;
import ae.pegasus.framework.errors.NullReturnException;
import ae.pegasus.framework.http.G4HttpClient;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.HttpRequest;
import ae.pegasus.framework.jira.model.*;
import ae.pegasus.framework.json.JsonConverter;
import org.apache.log4j.Logger;

import java.util.List;

import static ae.pegasus.framework.http.G4HttpClient.Protocol.HTTP;

/**
 * JiraConnector
 */
public class JiraConnector {

    private static final Logger log = Logger.getLogger(JiraConnector.class);
    private static final G4HttpClient client = new G4HttpClient(G4Properties.getJiraProperties().getJiraServer(), HTTP);

    /**
     * JiraConnector instance. Try to connect and initialize cookie.
     */
    public JiraConnector() {
        initSession();
    }

    /**
     * Connect to Jira and get current session info.
     * Need for initialize Jira cookie.
     *
     * @return Session
     */
    private Session initSession() {
        log.debug("Connecting to jira host...");
        JiraSessionRequest request = new JiraSessionRequest();
        G4Response response = client.sendRequest(request);

        OperationResult<SessionInfo> result = new OperationResult<>(response, SessionInfo.class);
        if (!result.isSuccess()) {
            log.error(String.format("Jira session init failed with %d code. Response message:\n%s",
                result.getCode(), result.getMessage()));
            return null;
        } else {
            log.debug("Jira session created");
        }

        Session session = null;
        if (result.getEntity() != null) {
            session = result.getEntity().getSession();
            G4HttpClient.setCookie(session.getName(), session.getValue());
        } else {
            log.error("Jira session wasn't received. Cookie will not be generated");
            log.error(result.getEntity().getLoginInfo().toString());
        }
        return session;
    }

    /**
     * Return projectId of project in Jira.
     *
     * @param name Project name
     * @return project id
     * @throws AssertionError if project id is not received.
     */
    public String getProjectId(String name) throws NullReturnException {
        log.debug("Getting project id based on name:" + name);

        String URL = "/rest/api/latest/project";
        HttpRequest request = new HttpRequest(URL);
        G4Response response = client.sendRequest(request);

        List<JiraProject> projects = JsonConverter.jsonToObjectsList(response.getMessage(), JiraProject[].class);
        for (JiraProject project : projects) {
            if (project.getName().equals(name)) {
                return project.getId();
            }
        }
        throw new AssertionError("Project id is not received");
    }

    /**
     * Return versionId of project version in Jira.
     *
     * @param projectId   projectId of current Jira project
     * @param versionName version name of current JIra project
     * @return versionId of current Jira project
     * @throws AssertionError There is version with name:versionName for project id:projectId)
     */
    public String getVersionId(String projectId, String versionName) throws NullReturnException {
        log.debug("Getting version id based on project id:" + projectId + " and version name:" + versionName);

        String URL = "/rest/api/latest/project/" + projectId + "/versions";
        HttpRequest request = new HttpRequest(URL);
        G4Response response = client.sendRequest(request);

        List<ProjectVersion> versions = JsonConverter.jsonToObjectsList(response.getMessage(), ProjectVersion[].class);

        for (ProjectVersion version : versions) {
            if (version.getName().equals(versionName)) {
                return version.getId();
            }
        }
        throw new AssertionError("There is version with name:" + versionName + " for project id:" + projectId);
    }

    /**
     * Return Jira Issue.
     *
     * @param issueKey issue key, ex: GQ-123, TEEL-1234
     * @return Issue
     */
    public Issue getIssue(String issueKey) {
        log.debug("Getting issue by it's key:" + issueKey);
        String url = "/rest/api/latest/issue/" + issueKey;

        HttpRequest request = new HttpRequest(url);
        G4Response response = client.sendRequest(request);
        return JsonConverter.jsonToObject(response.getMessage(), Issue.class);
    }

}
