package ae.pegasus.framework.data_for_entity.data_providers.profiler;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;
import ae.pegasus.framework.model.DataSourceType;

public class DataSourceTypeProvider implements EntityDataProvider {

    @Override
    public DataSourceType generate(int length) {
        return DataSourceType.random();
    }
}
