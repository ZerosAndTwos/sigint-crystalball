package ae.pegasus.framework.data_for_entity.data_providers.profiler;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;
import ae.pegasus.framework.utils.RandomGenerator;

import static ae.pegasus.framework.services.EntityService.appContext;

public class TeamsIdProvider implements EntityDataProvider {

    @Override
    public String generate(int i) {
        return RandomGenerator.getRandomItemFromList(appContext.getLoggedUser().getUser().getParentTeamIds());
    }
}
