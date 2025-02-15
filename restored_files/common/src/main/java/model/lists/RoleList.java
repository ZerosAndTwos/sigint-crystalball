package model.lists;

import abs.EntityList;
import errors.NullReturnException;
import model.Role;

public class RoleList extends EntityList<Role> {


    @Override
    public Role getEntity(String param) throws NullReturnException {
        for(Role role: entities) {
            if (role.getId().equals(param)){
                return role;
            }
        }
        throw new NullReturnException("There is not Role!");
    }
}
