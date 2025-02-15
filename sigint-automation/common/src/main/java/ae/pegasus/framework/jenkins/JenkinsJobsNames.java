package ae.pegasus.framework.jenkins;

import java.util.HashMap;

public class JenkinsJobsNames {
    
    private static HashMap<String, JenkinsJob> jobHashMap = new HashMap<>();
    
    static  {
        jobHashMap.put("develop", JenkinsJob.DEVELOP);
        jobHashMap.put("master", JenkinsJob.MASTER);
        
    }
    
    public static String getJobName(String standBranch) {
        JenkinsJob jenkinsJob = jobHashMap.get(standBranch);
        if (jenkinsJob == null) {
            return null;
        } else {
            return jenkinsJob.getJobName();
        }
        
    }
    
}
