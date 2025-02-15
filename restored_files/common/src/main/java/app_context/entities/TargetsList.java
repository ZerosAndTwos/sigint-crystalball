package app_context.entities;

import abs.EntityList;
import errors.NullReturnException;
import model.Target;

import java.util.List;

class TargetsList extends EntityList<Target> {
    
    public TargetsList(){
      
                }
 	
    public TargetsList(List<Target> targets) {
        super(targets);
    }

    @Override
    public Target getEntity(String param) throws NullReturnException {
        for (Target target:entities) {
            if (target.getId().equals(param)) {
                return target;
            }
        }
        throw new NullReturnException("Target is not found!");
    }
}
