package post_build_managers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class Manager {
    
    protected Connection connection;
    
    protected void initConnection() throws SQLException, IOException {
        if (connection==null) {
            connection = new DatabaseConnector().connect();
        }
        
    }
}
