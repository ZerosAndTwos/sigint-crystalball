package data_for_entity.data_providers;

import model.Permission;

public class UserPermissionProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return Permission.random();
    }
}
