package ae.pegasus.framework.data_for_entity.data_providers.profiler;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;
import ae.pegasus.framework.model.ProfileCategory;

public class ProfileCategoryProvider implements EntityDataProvider {

    @Override
    public String generate(int length) {
        return ProfileCategory.random().getDisplayName();
    }
}
