package post_build_managers;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class BuildManager extends Manager {
    
    private Logger logger = Logger.getLogger(BuildManager.class);
    
    public void updateCurrentBuildStatus(BuildStatus status) {
        updateBuild(status.toString(), "buildResultCurrent");
    }
    
    public void updatePreviousBuildStatus(BuildStatus status) {
        
        updateBuild(status.toString(), "buildResultPrevious");
    }
    
    public BuildStatus getCurrentBuildStatus()  {
            return getBuildStatus("buildResultCurrent");
        
    }
    
    public BuildStatus getPreviousBuildStatus() {
        return getBuildStatus("buildResultPrevious");}
    
    private BuildStatus getBuildStatus(String param) {
        try {
            initConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT status FROM info WHERE param=?;");
            statement.setString(1, param);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return BuildStatus.valueOf(result.getString("status"));
            } else {
                throw new SQLException("Cannot get result from SQL Query");
            }
        } catch (IOException| SQLException ex) {
            logger.error("Error occurred getting build status. UNKNOWN will be used");
            logger.error(ex.getMessage());
            return BuildStatus.UNKNOWN;
        }
    
    }
    
    public void updateBuildFlag(Boolean param) {
        updateBuild(param.toString(), "buildUpdated");
    }
    
    public boolean isBuildUpdated() {
        try {
            initConnection();
            ResultSet set = connection.createStatement().executeQuery("SELECT status from info WHERE param='buildUpdated'");
            if (set.next()) {
                return Boolean.parseBoolean(set.getString("status"));
            } else {
                logger.error("Database doesn't hold required status column");
                return false;
            }
        } catch (IOException | SQLException ex) {
            logger.error("Error occurred getting build status. UNKNOWN will be used");
            logger.error(ex.getMessage());
            return false;
        }
    }
    
    private void updateBuild(String status, String param) {
        try{
        initConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE info SET status=? where param=?;");
        preparedStatement.setString(1, status);
        preparedStatement.setString(2, param);
        preparedStatement.executeUpdate();
        } catch (IOException|SQLException ex) {
            logger.error("Cannot update build status due to DB error");
            logger.error(ex.getMessage());
        }
    }
    
}
