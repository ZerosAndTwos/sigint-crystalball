package ae.pegasus.framework.data_for_entity.data_providers.whitelist;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;
import ae.pegasus.framework.model.WhiteListType;


public class WhiteListTypeProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return WhiteListType.random();
    }
}
