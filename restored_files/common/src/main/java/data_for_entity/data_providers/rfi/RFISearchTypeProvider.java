package data_for_entity.data_providers.rfi;

import data_for_entity.data_providers.EntityDataProvider;
import model.InformationRequestSearchType;

public class RFISearchTypeProvider implements EntityDataProvider {
    
    @Override
    public Object generate(int length) {
        return InformationRequestSearchType.random();
    }
}
