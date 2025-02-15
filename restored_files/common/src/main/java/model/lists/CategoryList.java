package model.lists;

import abs.EntityList;
import errors.NullReturnException;
import model.ReportCategory;

public class CategoryList extends EntityList<ReportCategory> {

    @Override
    public ReportCategory getEntity(String param) throws NullReturnException {
        for(ReportCategory reportCategory : entities) {
            if (reportCategory.getId().equals(param)){
                return reportCategory;
            }
        }
        throw new NullReturnException("There is not category!");
    }
}
