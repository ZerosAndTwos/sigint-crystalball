package ae.pegasus.framework.data_for_entity.data_providers.profiler;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;
import ae.pegasus.framework.model.Classification;

public class ClassificationProvider implements EntityDataProvider {

    @Override
    public String generate(int length) {
        return Classification.random().getName();
    }
}
