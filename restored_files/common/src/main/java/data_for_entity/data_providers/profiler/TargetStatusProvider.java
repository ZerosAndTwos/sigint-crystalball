package data_for_entity.data_providers.profiler;

import data_for_entity.data_providers.EntityDataProvider;
import model.TargetStatus;

public class TargetStatusProvider implements EntityDataProvider {

    @Override
    public TargetStatus generate(int length) {
        return TargetStatus.random();
    }
}
