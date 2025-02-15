package data_for_entity.data_providers;

import utils.RandomGenerator;

public class PhonebookCountryName extends DependencyDataProvider {
    @Override
    public Object generate(int length) {
        DependencyData dependencyData = getDependencyData();
        return RandomGenerator.getCountryName((String)dependencyData.getData("countryCode"));
    }
}
