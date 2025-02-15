package ae.pegasus.framework.data_for_entity.data_providers.user;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;

import static ae.pegasus.framework.services.PermissionService.getPermissions;
import static ae.pegasus.framework.utils.RandomGenerator.getRandomItemFromList;


public class UserPermissionProvider implements EntityDataProvider {

    @Override
    public Object generate(int length) {
        return getRandomItemFromList(getPermissions());
    }
}
