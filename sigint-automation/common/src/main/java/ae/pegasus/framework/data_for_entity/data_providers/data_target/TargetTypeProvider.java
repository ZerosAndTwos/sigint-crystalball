package ae.pegasus.framework.data_for_entity.data_providers.data_target;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;
import ae.pegasus.framework.model.TargetType;

public class TargetTypeProvider implements EntityDataProvider {
    
    @Override
    public Object generate(int length) {
        return TargetType.random();
    }
}
