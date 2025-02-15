package ae.pegasus.framework.data_for_entity.data_providers;

import ae.pegasus.framework.services.UserService;

public class OrgUnitProvider implements EntityDataProvider {

    @Override
    public String generate(int length) {
        return "00-" + UserService.getOrCreateDefaultTeamId();
    }
}
