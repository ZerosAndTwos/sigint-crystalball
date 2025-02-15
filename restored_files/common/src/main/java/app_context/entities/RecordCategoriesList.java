package app_context.entities;

import abs.EntityList;
import errors.NullReturnException;
import model.RecordCategory;

class RecordCategoriesList extends EntityList<RecordCategory> {

    @Override
    public RecordCategory getEntity(String param) throws NullReturnException {
        for(RecordCategory recordCategory : entities) {
            if (recordCategory.getId().equals(param)){
                return recordCategory;
            }
        }
        throw new NullReturnException("There is not record-category!");
    }
}
