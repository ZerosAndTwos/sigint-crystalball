package ae.pegasus.framework.data_for_entity.data_providers.provision;

import ae.pegasus.framework.data_for_entity.data_providers.DependencyData;
import ae.pegasus.framework.data_for_entity.data_providers.DependencyDataProvider;
import ae.pegasus.framework.utils.RandomGenerator;

public class ProvisionRegionCode extends DependencyDataProvider {
    @Override
    public Object generate(int length) {
        DependencyData dependencyData = getDependencyData();
        return RandomGenerator.getCountryName((String)dependencyData.getData("provisionedRegionCode"));
    }
}
