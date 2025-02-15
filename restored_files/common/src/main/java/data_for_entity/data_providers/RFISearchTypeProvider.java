package data_for_entity.data_providers;

import model.InformationRequestSearchType;

public class RFISearchTypeProvider implements EntityDataProvider {
    
    @Override
    public Object generate(int length) {
        return InformationRequestSearchType.random();
    }
}
