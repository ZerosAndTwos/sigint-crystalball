package database;

import model.AppContext;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

 class DatabaseConnector {
    
    private String host;
    private String database;
    private String user;
    
    DatabaseConnector() throws IOException {
        initVariables();
    }
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public String getDatabase() {
        return database;
    }
    
    public void setDatabase(String database) {
        this.database = database;
    }
    
    public String getUser() {
        return user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    private String password;
    
    private void initVariables() throws IOException {
        Properties database = new Properties();
        database.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        this.host = AppContext.getContext().environment().getRemoteHub();
        this.user = database.getProperty("user");
        this.password = database.getProperty("password");
        this.database = database.getProperty("database");
        
    }
    
    Connection connect() throws SQLException {
        return DriverManager.getConnection(String.format("jdbc:mysql://%s:3306/%s",host, database), user, password);
    }
}
