package data_for_entity.data_providers.data_target;

import data_for_entity.data_providers.DependencyDataProvider;
import utils.RandomGenerator;

public class TargetKeywordsProvider extends DependencyDataProvider {
    
    @Override
    public Object generate(int length) {
        return RandomGenerator.generateKeywords(3);
    }
}
