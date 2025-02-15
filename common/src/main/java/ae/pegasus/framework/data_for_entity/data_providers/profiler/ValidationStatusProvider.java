package ae.pegasus.framework.data_for_entity.data_providers.profiler;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;
import ae.pegasus.framework.model.ValidationStatus;

public class ValidationStatusProvider implements EntityDataProvider {

    @Override
    public ValidationStatus generate(int length) {
        return ValidationStatus.random();
    }
}
