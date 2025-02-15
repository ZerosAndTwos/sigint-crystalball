package ae.pegasus.framework.data_for_entity.data_providers.phonebook;

import ae.pegasus.framework.data_for_entity.data_providers.DependencyData;
import ae.pegasus.framework.data_for_entity.data_providers.DependencyDataProvider;
import ae.pegasus.framework.utils.RandomGenerator;

public class PhonebookCountryName extends DependencyDataProvider {
    @Override
    public Object generate(int length) {
        DependencyData dependencyData = getDependencyData();
        return RandomGenerator.getCountryName((String)dependencyData.getData("countryCode"));
    }
}
