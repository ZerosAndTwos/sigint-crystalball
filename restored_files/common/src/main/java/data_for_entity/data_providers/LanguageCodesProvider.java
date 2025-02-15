package data_for_entity.data_providers;

import utils.RandomGenerator;

public class LanguageCodesProvider implements EntityDataProvider {
    
    @Override
    public Object generate(int length) {
        return RandomGenerator.getRandomLanguageCode();
    }
}
