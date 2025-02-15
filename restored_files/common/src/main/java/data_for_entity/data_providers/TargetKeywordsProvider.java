package data_for_entity.data_providers;

import utils.RandomGenerator;

public class TargetKeywordsProvider extends DependencyDataProvider {
    
    @Override
    public Object generate(int length) {
        return RandomGenerator.generateKeywords(3);
    }
}
