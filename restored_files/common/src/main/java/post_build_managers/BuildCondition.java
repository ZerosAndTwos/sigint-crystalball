package post_build_managers;

import org.apache.log4j.Logger;

public class BuildCondition {
    
    private static BuildManager manager = new BuildManager();
    private static Logger logger = Logger.getLogger(BuildCondition.class);
    
    public static void resetBuildFlag() {
        manager.updateBuildFlag(Boolean.FALSE);
    }
    
    
    public static BuildStatus getPreviousStatus() {
        return manager.getPreviousBuildStatus();
    }
    
    public static void updateBuildStatus(BuildStatus status) {
        if (!manager.isBuildUpdated()) {
            BuildStatus currentStatus = manager.getCurrentBuildStatus();
            manager.updatePreviousBuildStatus(currentStatus);
            manager.updateBuildFlag(Boolean.TRUE);
        }
        manager.updateCurrentBuildStatus(status);
        
    }
    
    public static BuildStatus getCurrentStatus() {
        return manager.getCurrentBuildStatus();
    }
    
}
