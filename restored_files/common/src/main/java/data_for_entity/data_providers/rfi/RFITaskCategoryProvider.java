package data_for_entity.data_providers.rfi;

import data_for_entity.data_providers.EntityDataProvider;
import model.InformationRequestTaskCategory;

public class RFITaskCategoryProvider implements EntityDataProvider {
    
    @Override
    public Object generate(int length) {
        return InformationRequestTaskCategory.random();
    }
}
