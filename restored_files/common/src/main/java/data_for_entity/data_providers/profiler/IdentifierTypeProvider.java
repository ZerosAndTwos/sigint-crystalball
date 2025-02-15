package data_for_entity.data_providers.profiler;

import data_for_entity.data_providers.EntityDataProvider;
import model.IdentifierType;

public class IdentifierTypeProvider implements EntityDataProvider {

    @Override
    public IdentifierType generate(int length) {
        return IdentifierType.random();
    }
}
