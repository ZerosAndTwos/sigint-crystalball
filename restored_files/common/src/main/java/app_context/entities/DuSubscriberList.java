package app_context.entities;

import abs.EntityList;
import model.DuSubscriberEntry;

class DuSubscriberList extends EntityList<DuSubscriberEntry> {


    @Override
    public DuSubscriberEntry getEntity(String param) {
        for(DuSubscriberEntry entry : entities) {
            if (entry.getId().equals(param)){
                return entry;
            }
        }
       return null;
    }
}
