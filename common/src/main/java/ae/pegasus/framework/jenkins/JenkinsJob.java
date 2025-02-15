package ae.pegasus.framework.jenkins;

public enum JenkinsJob {
    
    DEVELOP("Develop Tests"), MASTER("Master Tests");
    
    
    private final String jobName;
    
    
    JenkinsJob(String jobName) {
        this.jobName = jobName;
    }
    
    public String getJobName(){
        return this.jobName;
    }
    
   
}
