package data_for_entity.data_providers.profiler;

import data_for_entity.data_providers.EntityDataProvider;
import model.DataSourceType;

public class DataSourceTypeProvider implements EntityDataProvider {

    @Override
    public DataSourceType generate(int length) {
        return DataSourceType.random();
    }
}
