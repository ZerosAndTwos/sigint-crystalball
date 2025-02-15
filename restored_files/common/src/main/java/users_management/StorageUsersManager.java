package users_management;

import error_reporter.ErrorReporter;
import model.User;
import org.apache.log4j.Logger;

import java.util.*;

public class StorageUsersManager {

    private static StorageUsersManager instance;
    private static Map<String, List<User>> userMap = new HashMap<>();
    private Logger log = Logger.getLogger(StorageUsersManager.class);

    private StorageUsersManager(){initializePermissions();}


    public static StorageUsersManager getManager(){
        if (instance == null) {
            instance = new StorageUsersManager();
        }
        return instance;
    }

    private static void initializePermissions() {
        List<String> existingPermissions = Permissions.getPermissions();
        if (existingPermissions.size() == 0) {
            ErrorReporter.raiseError("List of permissions wasn't loaded from mongo DB!");
        }

        existingPermissions.forEach(permission -> userMap.put(permission, new ArrayList<>()));
    }

    public List<User> getUsersWithPermissions(String... permissions) {
        List<User> users = new ArrayList<>();
        for (String permission: permissions) {
            if (users.size() == 0) {
                users.addAll(userMap.get(permission));
            } else {
                users.retainAll(userMap.get(permission));
                if (users.size() == 0) {
                    log.debug("User with permissions: " + Arrays.toString(permissions) + "not found!");
                    return users;
                }
            }
        }
       log.trace("With permissions: " + Arrays.toString(permissions) + " users found: " + users.size());
       return users;
    }

    public void addUser(User user, String... permissions) {
        for (String permission: permissions) {
            userMap.get(permission).add(user);
        }
    }
}
