package ae.pegasus.framework.data_for_entity.data_providers.coordinates;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;

public class CoordinateProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return Math.random() * 360 - 180;
    }
}
