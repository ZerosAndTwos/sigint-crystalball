package ae.pegasus.framework.app_context.properties;

public class G4Properties {
    private static RunProperties runProperties;
    private static JiraProperties jiraProperties;
    private static MailProperties mailProperties;
    private static JenkinsProperties jenkinsProperties;

    
    public static RunProperties getRunProperties() {
        if(runProperties==null) {
            runProperties = new RunProperties();
        }
        return runProperties;
    }
    
    public static JiraProperties getJiraProperties() {
        if (jiraProperties==null) {
            jiraProperties = new JiraProperties();
        }
        return jiraProperties;
    }
    
    public static MailProperties getMailProperties() {
        if (mailProperties == null) {
            mailProperties = new MailProperties();
        }
        return mailProperties;
    }
    
    public static JenkinsProperties  getJenkinsProperties() {
        if (jenkinsProperties == null) {
            jenkinsProperties = new JenkinsProperties();
        }
        return  jenkinsProperties;
    }

}
