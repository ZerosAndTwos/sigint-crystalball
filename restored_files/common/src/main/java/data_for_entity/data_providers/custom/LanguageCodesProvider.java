package data_for_entity.data_providers.custom;

import data_for_entity.data_providers.EntityDataProvider;
import utils.RandomGenerator;

public class LanguageCodesProvider implements EntityDataProvider {
    
    @Override
    public Object generate(int length) {
        return RandomGenerator.getRandomLanguageCode();
    }
}
