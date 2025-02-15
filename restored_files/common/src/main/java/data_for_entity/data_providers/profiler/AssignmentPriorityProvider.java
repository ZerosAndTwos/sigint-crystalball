package data_for_entity.data_providers.profiler;

import data_for_entity.data_providers.EntityDataProvider;
import utils.RandomGenerator;

public class AssignmentPriorityProvider implements EntityDataProvider {

    @Override
    public Integer generate(int length) {
        return RandomGenerator.generateRandomInteger(0, 2);
    }
}
