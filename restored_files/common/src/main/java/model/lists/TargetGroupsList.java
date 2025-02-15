package model.lists;

import abs.EntityList;
import errors.NullReturnException;
import model.TargetGroup;

public class TargetGroupsList extends EntityList<TargetGroup> {

    @Override
    public TargetGroup getEntity(String param) throws NullReturnException {
        for(TargetGroup group: entities) {
            if (group.getId().equals(param)){
                return group;
            }
        }
        throw new NullReturnException("There is not Target Group!");
    }
}
