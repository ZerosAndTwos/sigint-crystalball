package data_for_entity.data_providers.data_target;

import data_for_entity.data_providers.EntityDataProvider;
import model.TargetType;

public class TargetTypeProvider implements EntityDataProvider {
    
    @Override
    public Object generate(int length) {
        return TargetType.random();
    }
}
