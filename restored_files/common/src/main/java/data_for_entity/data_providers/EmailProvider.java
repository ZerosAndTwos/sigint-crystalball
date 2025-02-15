package data_for_entity.data_providers;

import utils.RandomGenerator;

public class EmailProvider implements EntityDataProvider {

    @Override
    public String generate(int length) {
        return RandomGenerator.generateEmail();
    }
}
