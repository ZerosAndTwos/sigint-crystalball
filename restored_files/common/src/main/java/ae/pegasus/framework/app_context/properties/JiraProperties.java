package ae.pegasus.framework.app_context.properties;

import java.io.InputStream;

public class JiraProperties extends ApplicationProperty{
    
    JiraProperties(){}
    
    @Override
    InputStream loadPropertyFile() {
        return getClass().getClassLoader().getResourceAsStream("jiraConnection.properties");
    }
    
    public String getJiraServer() {
        return getProperty().getProperty("server");
    }
    
    public String getUsername() {
        return getProperty().getProperty("username");
    }
    
    public String getPassword() {
        return getProperty().getProperty("password");
    }
    
    public String getCycleName() {
        return getProperty().getProperty("cycle");
    }
    
    public String getVersion() {
        return getProperty().getProperty("version");
    }
    
    public String getProject() {
        return getProperty().getProperty("project");
    }
    
}
