package users_management;

import app_context.properties.MongoConnectionProperties;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import error_reporter.ErrorReporter;
import org.apache.log4j.Logger;
import org.bson.Document;

class MongoConnector {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private static MongoConnector instance;
    private Logger log = Logger.getLogger(MongoConnector.class);

    private MongoConnector() {
        initConnection();
    }

    static MongoConnector getMongoConnector() {
        if (instance == null) {
            instance = new MongoConnector();
        }
        return instance;
    }


    private void initConnection() {
        log.debug("Initializing connection to mongo db.");
        String host = MongoConnectionProperties.getHost();
        Integer port = Integer.valueOf(MongoConnectionProperties.getPort());
        log.debug(String.format("Host: %s, Port: %s", host, port));
        mongoClient = new MongoClient(host, port);
    }

    void useDatabase(String database){
        mongoDatabase = mongoClient.getDatabase(database);
    }

    MongoCollection<Document> getCollection(String collectionName) {
        if (mongoDatabase == null) {
            ErrorReporter.raiseError("You must select database first!");
        }
        return mongoDatabase.getCollection(collectionName);

    }
}
