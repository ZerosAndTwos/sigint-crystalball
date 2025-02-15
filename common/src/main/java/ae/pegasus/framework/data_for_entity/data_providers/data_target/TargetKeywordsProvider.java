package ae.pegasus.framework.data_for_entity.data_providers.data_target;

import ae.pegasus.framework.data_for_entity.data_providers.DependencyDataProvider;
import ae.pegasus.framework.utils.RandomGenerator;

public class TargetKeywordsProvider extends DependencyDataProvider {
    
    @Override
    public Object generate(int length) {
        return RandomGenerator.generateKeywords(3);
    }
}
