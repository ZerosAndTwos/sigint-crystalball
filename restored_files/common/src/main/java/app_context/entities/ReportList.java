package app_context.entities;

import abs.EntityList;
import errors.NullReturnException;
import model.Report;

class ReportList extends EntityList<Report> {

    @Override
    public Report getEntity(String param) throws NullReturnException {
        for(Report report : entities) {
            if (report.getId().equals(param)){
                return report;
            }
        }
        throw new NullReturnException("There is not report!");
    }
}
