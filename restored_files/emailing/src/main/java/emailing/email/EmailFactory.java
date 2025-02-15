package emailing.email;

import ae.pegasus.framework.app_context.properties.G4Properties;
import emailing.email.html_body_builder.FailingContentBuilder;
import emailing.email.html_body_builder.StableContentBuilder;
import emailing.email.html_body_builder.StillFailingContentBuilder;
import ae.pegasus.framework.error_reporter.ErrorReporter;
import jenkins.JenkinsJobsNames;
import jenkins.JenkinsService;
import jenkins.JobStatus;
import org.apache.log4j.Logger;

public class EmailFactory {
    
    private static Logger logger = Logger.getLogger(EmailFactory.class);
    
    public static HtmlEmail buildHtmlEmail() {
    
        String standBranch = G4Properties.getRunProperties().getActiveStand();
        String jobName = JenkinsJobsNames.getJobName(standBranch);
        if (jobName == null) {
            ErrorReporter.raiseError("Name of jenkins job wasn't received. Email send error");
        }
        JenkinsService jenkinsService = new JenkinsService();
        JobStatus latestJobStatus = jenkinsService.getLatestJobStatus(jobName);
        JobStatus previousJobStatus = jenkinsService.getPreviousJobStatus(jobName);
        if ((latestJobStatus == null) || (previousJobStatus == null)) {
            ErrorReporter.raiseError("Either latest or previous job status wasn't received." +
                    "Email will not be sent due to error");
        }
        HtmlEmail email;
        if (previousJobStatus.equals(JobStatus.SUCCESS)) {
            email = (latestJobStatus.equals(JobStatus.FAILURE)) ? new HtmlEmail(new FailingContentBuilder(jobName)): null;
        }
        else if (previousJobStatus.equals(JobStatus.FAILURE)) {
            email = (latestJobStatus.equals(JobStatus.FAILURE)) ? new HtmlEmail(new StillFailingContentBuilder(jobName)) :
                    new HtmlEmail(new StableContentBuilder(jobName));
        }
        else {
            throw new AssertionError("Email status is not recognized");
        }
        return email;
        
        }
    }

