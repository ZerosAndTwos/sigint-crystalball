package data_for_entity.data_providers.rfi;

import data_for_entity.data_providers.EntityDataProvider;
import model.InformationRequestPriority;

public class RFIPrioritiesProvider implements EntityDataProvider {
    
    @Override
    public Object generate(int length) {
        InformationRequestPriority priority = InformationRequestPriority.random();
        return priority.getNumber();
    }
}
