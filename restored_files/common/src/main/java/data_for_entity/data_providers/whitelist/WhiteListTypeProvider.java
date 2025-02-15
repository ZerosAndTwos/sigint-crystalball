package data_for_entity.data_providers.whitelist;

import data_for_entity.data_providers.EntityDataProvider;
import model.WhiteListType;


public class WhiteListTypeProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return WhiteListType.random();
    }
}
