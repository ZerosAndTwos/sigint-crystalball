package ae.pegasus.framework.jenkins;

import ae.pegasus.framework.app_context.properties.G4Properties;
import ae.pegasus.framework.app_context.properties.JenkinsProperties;
import ae.pegasus.framework.http.G4HttpClient;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.HttpRequest;
import org.apache.log4j.Logger;

import static ae.pegasus.framework.http.G4HttpClient.Protocol.HTTP;

class JenkinsClient {
    
    private G4HttpClient g4HttpClient = new G4HttpClient(G4Properties.getRunProperties().getApplicationURL(), HTTP);
    private String jenkinsURL;
    private String jenkinsUsername;
    private String jenkinsPassword;
    private String jobName;
    private Logger logger = Logger.getLogger(JenkinsClient.class);
    
    JenkinsClient() {
        JenkinsProperties jenkinsConfig  = G4Properties.getJenkinsProperties();
        this.jenkinsURL = jenkinsConfig.getHost();
        this.jenkinsUsername = jenkinsConfig.getUsername();
        this.jenkinsPassword = jenkinsConfig.getPassword();
    }

    JobInfo getJenkinsJobInfo(String jobName, String jobNumber) {
        logger.debug("Getting jenkins job info by job number:"+jobNumber);

        String url = String.format("%s/job/%s/%s/api/json", jenkinsURL, jobName, jobNumber);
        HttpRequest request = new HttpRequest(url);
        G4Response response = g4HttpClient.sendRequest(request, jenkinsUsername, jenkinsPassword);
        OperationResult<JobInfo> operationResult = new OperationResult<>(response, JobInfo.class);
        if (!operationResult.isSuccess()) {
            logger.error("Got error from jenkins when trying to get job status");
        }

        JobInfo jobInfo = operationResult.getEntity();
        logger.debug("Received job: " + jobInfo);
        return jobInfo;
    }
    
}
