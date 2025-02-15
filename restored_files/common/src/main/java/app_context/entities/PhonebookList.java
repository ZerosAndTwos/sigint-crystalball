package app_context.entities;

import abs.EntityList;
import errors.NullReturnException;
import model.Phonebook;

class PhonebookList extends EntityList<Phonebook> {

    @Override
    public Phonebook getEntity(String param) throws NullReturnException {
        for(Phonebook phonebook : entities) {
            if (phonebook.getId().equals(param)){
                return phonebook;
            }
        }
        throw new NullReturnException("There is not Phonebook!");
    }
}
