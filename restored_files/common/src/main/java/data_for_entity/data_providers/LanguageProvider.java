package data_for_entity.data_providers;

import utils.RandomGenerator;

public class LanguageProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return RandomGenerator.getRandomLanguage();
    }
}
