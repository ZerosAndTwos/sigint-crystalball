package ae.pegasus.framework.data_for_entity.data_providers.profiler;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;
import ae.pegasus.framework.model.IdentifierType;

public class IdentifierTypeProvider implements EntityDataProvider {

    @Override
    public IdentifierType generate(int length) {
        return IdentifierType.random();
    }
}
