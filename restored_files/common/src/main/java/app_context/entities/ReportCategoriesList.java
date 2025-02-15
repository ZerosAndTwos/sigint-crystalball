package app_context.entities;

import abs.EntityList;
import errors.NullReturnException;
import model.ReportCategory;

class ReportCategoriesList extends EntityList<ReportCategory> {

    @Override
    public ReportCategory getEntity(String param) throws NullReturnException {
        for(ReportCategory reportCategory : entities) {
            if (reportCategory.getId().equals(param)){
                return reportCategory;
            }
        }
        throw new NullReturnException("There is not reportCategory!");
    }
}
