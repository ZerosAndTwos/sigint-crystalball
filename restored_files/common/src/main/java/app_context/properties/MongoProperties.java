package app_context.properties;

import java.io.InputStream;

class MongoProperties extends ApplicationProperty {

    MongoProperties() {
    }

    @Override
    InputStream loadPropertyFile() {
        return getClass().getClassLoader().getResourceAsStream("mongodb.properties");
    }

    String getHostDevelop() {
        return getProperty().getProperty("host_develop");
    }

    String getPort() {
        return getProperty().getProperty("port");
    }

    String getHostTest(){
        return getProperty().getProperty("host_test");
    }

    String getHostMaster() {
        return getProperty().getProperty("host_master");
    }

}
