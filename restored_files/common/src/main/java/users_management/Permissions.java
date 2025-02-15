package users_management;

import org.apache.log4j.Logger;

import java.util.List;

public class Permissions {


    private static List<String> permissions;
    private static Logger log = Logger.getLogger(Permissions.class);

    public static List<String> getPermissions() {
        if (permissions == null) {
            log.debug("Reading permissions from storage");
            StoragePermissionsManager manager = new StoragePermissionsManager();
            permissions = manager.getPermissions();
            log.debug("Get permissions: " + permissions.toString());
        }

        return permissions;

    }
}
