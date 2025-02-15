package model.lists;

import abs.EntityList;
import errors.NullReturnException;
import model.Group;

public class GroupList extends EntityList<Group> {


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
