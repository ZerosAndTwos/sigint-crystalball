package data_for_entity.data_providers;

import model.SourceType;

public class SourceTypeProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return SourceType.random();
    }
}
