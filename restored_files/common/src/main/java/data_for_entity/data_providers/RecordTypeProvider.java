package data_for_entity.data_providers;

import model.RecordType;

public class RecordTypeProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return RecordType.random();
    }
}
