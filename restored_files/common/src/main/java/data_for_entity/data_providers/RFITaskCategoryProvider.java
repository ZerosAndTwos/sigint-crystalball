package data_for_entity.data_providers;

import model.InformationRequestTaskCategory;

public class RFITaskCategoryProvider implements EntityDataProvider {
    
    @Override
    public Object generate(int length) {
        return InformationRequestTaskCategory.random();
    }
}
