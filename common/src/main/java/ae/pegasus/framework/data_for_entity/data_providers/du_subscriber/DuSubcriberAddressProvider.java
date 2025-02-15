package ae.pegasus.framework.data_for_entity.data_providers.du_subscriber;

import ae.pegasus.framework.data_for_entity.data_providers.DependencyData;
import ae.pegasus.framework.data_for_entity.data_providers.DependencyDataProvider;

public class DuSubcriberAddressProvider extends DependencyDataProvider {
    @Override
    public Object generate(int length) {
        DependencyData dependencyData = getDependencyData();
        String city = (String)dependencyData.getData("city");
        String poBox = (String)dependencyData.getData("poBox");
        return city + ", " + poBox;
                
    }
}
