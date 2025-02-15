package data_for_entity.data_providers.profiler;

import data_for_entity.data_providers.EntityDataProvider;
import model.ValidationStatus;

public class ValidationStatusProvider implements EntityDataProvider {

    @Override
    public ValidationStatus generate(int length) {
        return ValidationStatus.random();
    }
}
