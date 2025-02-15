package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailManager {
    
    private Connection connection;
    
    public EmailManager() {
    }
    
    public void setEmailasPassed(){}
    public void setEmailasFailed(){}
    
    
    private void initConnection() throws SQLException, IOException {
        if (connection==null) {
            connection = new DatabaseConnector().connect();
        }
        
    }
    
    public void updateEmailSendInfo(Boolean status) throws Exception {
        try {
            initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE info SET is_set= ? where send=?;");
            preparedStatement.setBoolean(1, status);
            preparedStatement.setString(2, "send");
            preparedStatement.executeUpdate();
        }catch (IOException|SQLException ex) {
            throw ex;
        }
            
    }
    
    
    public boolean getEmailSendInfo() throws Exception {
        try {
            initConnection();
            ResultSet result = connection.createStatement().executeQuery("SELECT is_set FROM info WHERE send='send';");
            if (result.next()) {
                return result.getBoolean("is_set");
            } else {
                throw new SQLException("Cannot get result from SQL Query");
        }
    } catch (IOException|SQLException ex) {
        throw ex;
        }
    }
    
}
