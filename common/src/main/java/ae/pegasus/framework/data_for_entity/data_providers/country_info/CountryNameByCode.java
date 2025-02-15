package ae.pegasus.framework.data_for_entity.data_providers.country_info;

import ae.pegasus.framework.data_for_entity.data_providers.DependencyData;
import ae.pegasus.framework.data_for_entity.data_providers.DependencyDataProvider;
import ae.pegasus.framework.utils.RandomGenerator;

public class CountryNameByCode extends DependencyDataProvider {
    
    @Override
    public Object generate(int length) {
        DependencyData dependencyData = getDependencyData();
        return RandomGenerator.getCountryName((String)dependencyData.getData("countryCodeOriginal"));
        
    }
}
