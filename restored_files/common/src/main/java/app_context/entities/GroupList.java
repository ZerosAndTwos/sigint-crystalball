package app_context.entities;

import abs.EntityList;
import errors.NullReturnException;
import model.Group;

class GroupList extends EntityList<Group> {


    @Override
    public Group getEntity(String param) throws NullReturnException {
        for(Group group : entities) {
            if (group.getId().equals(param)){
                return group;
            }
        }
        throw new NullReturnException("There is not Group!");
    }
}
