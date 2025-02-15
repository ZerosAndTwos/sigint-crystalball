package data_for_entity.data_providers.profiler;

import data_for_entity.data_providers.EntityDataProvider;
import model.ProfileCategory;

public class ProfileCategoryProvider implements EntityDataProvider {

    @Override
    public String generate(int length) {
        return ProfileCategory.random().getDisplayName();
    }
}
