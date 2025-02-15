package ae.pegasus.framework.app_context.properties;

import java.io.InputStream;

public class JenkinsProperties extends ApplicationProperty{
    
    JenkinsProperties(){}
    
    @Override
    InputStream loadPropertyFile() {
        return getClass().getClassLoader().getResourceAsStream("jenkins.properties");
    }
    
    
    public String getHost() {
        return getProperty().getProperty("jenkinsHost");
    }
    
    public String getUsername() {
        return getProperty().getProperty("username");
    }
    
    public String getPassword() {
        return getProperty().getProperty("password");
    }
}
