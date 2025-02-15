package data_for_entity.data_providers.profiler;

import data_for_entity.data_providers.EntityDataProvider;
import model.Classification;

public class ClassificationProvider implements EntityDataProvider {

    @Override
    public String generate(int length) {
        return Classification.random().getName();
    }
}
