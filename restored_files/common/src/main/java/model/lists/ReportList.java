package model.lists;

import abs.EntityList;
import errors.NullReturnException;
import model.Report;

public class ReportList extends EntityList<Report> {

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
