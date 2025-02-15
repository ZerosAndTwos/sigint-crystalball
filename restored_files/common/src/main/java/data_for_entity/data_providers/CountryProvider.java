package data_for_entity.data_providers;

import utils.RandomGenerator;

public class CountryProvider implements EntityDataProvider {
    
    @Override
    public Object generate(int length) {
        return RandomGenerator.getRandomCountry();
    }
}
