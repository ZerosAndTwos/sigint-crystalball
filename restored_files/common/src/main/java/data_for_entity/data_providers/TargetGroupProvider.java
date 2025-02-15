package data_for_entity.data_providers;

import model.TargetGroupType;

public class TargetGroupProvider implements EntityDataProvider {

    @Override
    public Object generate(int length) {
        return TargetGroupType.random();
    }
}
