package app_context.entities;


import abs.EntityList;
import errors.NullReturnException;
import model.Whitelist;

class WhitelistsList extends EntityList<Whitelist>{

    @Override
    public Whitelist getEntity(String param) throws NullReturnException{
        for(Whitelist whitelist: entities){

            if (whitelist.getId().equals(param)){
                return whitelist;
            }
        }
        throw new NullReturnException("There is not whitelist entry!");
    }
}
