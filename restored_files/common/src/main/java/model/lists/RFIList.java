package model.lists;

import abs.EntityList;
import errors.NullReturnException;
import model.InformationRequest;

public class RFIList extends EntityList<InformationRequest> {

    
    @Override
    public InformationRequest getEntity(String param) throws NullReturnException {
        for(InformationRequest rfi : entities) {
            if (rfi.getId().equals(param)){
                return rfi;
            }
        }
        throw new NullReturnException("There is not RFI!");
    }
}
