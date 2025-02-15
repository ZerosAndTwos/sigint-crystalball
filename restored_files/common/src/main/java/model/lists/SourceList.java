package model.lists;

import abs.EntityList;
import errors.NullReturnException;
import model.Source;

public class SourceList extends EntityList<Source> {

    @Override
    public Source getEntity(String param) throws NullReturnException {
        for(Source source: entities) {
            if (source.getId().equals(param)){
                return source;
            }
        }
        throw new NullReturnException("Unable to get Source by id: " + param);
    }
}
