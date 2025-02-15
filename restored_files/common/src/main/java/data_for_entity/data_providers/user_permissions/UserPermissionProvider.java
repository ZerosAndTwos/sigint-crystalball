package data_for_entity.data_providers.user_permissions;

import data_for_entity.data_providers.EntityDataProvider;

import static services.PermissionService.getPermissions;
import static utils.RandomGenerator.getRandomItemFromList;


public class UserPermissionProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return getRandomItemFromList(getPermissions());
    }
}
