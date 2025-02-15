package ae.pegasus.framework.data_for_entity.data_providers.profiler;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;
import ae.pegasus.framework.model.TargetStatus;

public class TargetStatusProvider implements EntityDataProvider {

    @Override
    public TargetStatus generate(int length) {
        return TargetStatus.random();
    }
}
