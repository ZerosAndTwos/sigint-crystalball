package data_for_entity.data_providers;

import model.TargetType;

public class TargetTypeProvider implements EntityDataProvider {
    
    @Override
    public Object generate(int length) {
        return TargetType.random();
    }
}
