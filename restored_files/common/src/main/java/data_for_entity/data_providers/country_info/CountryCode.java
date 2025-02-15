package data_for_entity.data_providers.country_info;

import data_for_entity.data_providers.EntityDataProvider;
import utils.RandomGenerator;

public class CountryCode implements EntityDataProvider {
    
    @Override
    public String generate(int length) {
        return RandomGenerator.generateCountryCode();
    }
}
