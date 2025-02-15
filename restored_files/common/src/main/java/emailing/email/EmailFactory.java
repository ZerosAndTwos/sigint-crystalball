package emailing.email;

import emailing.email.html_body_builder.FailingBodyBuilder;
import emailing.email.html_body_builder.StableBodyBuilder;
import jenkins.JenkinsService;
import jenkins.JobStatus;

public class EmailFactory {
    
    public static String buildHmtlEmail() {
        
        JenkinsService jenkinsService = new JenkinsService();
        JobStatus status = jenkinsService.getLatestJobStatus();
        HtmlEmail email;
        if (status.equals(JobStatus.FAILURE)) email =  new HtmlEmail(new FailingBodyBuilder());
        else if (status.equals(JobStatus.SUCCESS)) email =  new HtmlEmail(new StableBodyBuilder());
        else throw  new AssertionError("Build status wasn't recognized");
        return  email.getHtmlBody();
        
        }
    
    }

