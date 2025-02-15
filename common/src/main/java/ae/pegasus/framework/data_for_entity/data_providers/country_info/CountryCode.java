package ae.pegasus.framework.data_for_entity.data_providers.country_info;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;
import ae.pegasus.framework.utils.RandomGenerator;

public class CountryCode implements EntityDataProvider {
    
    @Override
    public String generate(int length) {
        return RandomGenerator.generateCountryCode();
    }
}
