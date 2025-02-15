package users_management;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.apache.log4j.Logger;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

class StoragePermissionsManager {

    private static final String STORAGE = "auth";
    private static final String COLLECTION = "permission";
    private MongoConnector mongoConnector = MongoConnector.getMongoConnector();
    private static Logger log = Logger.getLogger(StoragePermissionsManager.class);

    List<String> getPermissions() {
        log.debug(String.format("Getting document from storage: %s, collection: %s", STORAGE, COLLECTION));
        mongoConnector.useDatabase(STORAGE);
        MongoCollection<Document> collection = mongoConnector.getCollection(COLLECTION);
        FindIterable<Document> documents = collection.find();
        List<String> permissions = new ArrayList<>();
        for (Document doc: documents) {
            permissions.add(doc.getString("_id"));
        }
        return permissions;

    }



}
