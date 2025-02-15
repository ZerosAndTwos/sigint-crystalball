package app_context.entities;

import abs.EntityList;
import errors.NullReturnException;
import model.Record;

class RecordList extends EntityList<Record> {


    @Override
    public Record getEntity(String param) throws NullReturnException {
        for(Record record : entities) {
            if (record.getId().equals(param)){
                return record;
            }
        }
        throw new NullReturnException("There is not report!");
    }
}
