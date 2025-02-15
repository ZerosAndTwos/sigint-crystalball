package app_context.entities;

import abs.EntityList;
import model.TargetGroup;

class TargetGroupsList extends EntityList<TargetGroup> {

    @Override
    public TargetGroup getEntity(String param) {
        for(TargetGroup group: entities) {
            if (group.getId().equals(param)){
                return group;
            }
        }
       return null;
    }
}
