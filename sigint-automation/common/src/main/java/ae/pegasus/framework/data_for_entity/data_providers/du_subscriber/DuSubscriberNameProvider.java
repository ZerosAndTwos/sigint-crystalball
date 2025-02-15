package ae.pegasus.framework.data_for_entity.data_providers.du_subscriber;

import ae.pegasus.framework.data_for_entity.data_providers.DependencyData;
import ae.pegasus.framework.data_for_entity.data_providers.DependencyDataProvider;

public class DuSubscriberNameProvider extends DependencyDataProvider {
    
    @Override
    public Object generate(int length) {
        DependencyData dependencyData = getDependencyData();
        String firstName = (String)dependencyData.getData("firstName");
        String middleName = (String)dependencyData.getData("middleName");
        String lastName = (String)dependencyData.getData("lastName");
        return firstName + " " + middleName + " " + lastName;
    }
}
