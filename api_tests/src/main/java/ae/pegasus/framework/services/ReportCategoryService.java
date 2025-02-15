package ae.pegasus.framework.services;

import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.ReportCategoriesRequest;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.Result;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.model.information_managment.ReportCategory;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.List;

public class ReportCategoryService implements EntityService<ReportCategory> {

    private static final Logger log = Logger.getLogger(ReportCategoryService.class);
    private static final ReportCategoriesRequest request = new ReportCategoriesRequest();

    @Override
    public OperationResult<ReportCategory> add(ReportCategory entity) {
        log.info("Report category creating");

        G4Response response = g4HttpClient.sendRequest(request.create(entity));

        OperationResult<ReportCategory> operationResult = new OperationResult<>(response, ReportCategory.class, "result");
        if (operationResult.isSuccess()) {
            ReportCategory reportCategory = operationResult.getEntity();
            log.info("New report category id: " + reportCategory.getId() + " and name: " + reportCategory.getName());
            Entities.getReportCategories().addOrUpdateEntity(reportCategory);
        }
        return operationResult;
    }

    @Override
    public OperationResult<Result> remove(ReportCategory entity) {
        log.info("Deleting report category id:" + entity.getId() + " and name: " + entity.getName());

        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult<Result> operationResult = new OperationResult<>(response, Result.class);
        if (operationResult.isSuccess()) {
            Entities.getReportCategories().removeEntity(entity);
        }
        return operationResult;
    }

    @Override
    public OperationResult<List<ReportCategory>> search(SearchFilter filter) {
        throw new NotImplementedException("");
    }

    /**
     * GET list of Report Category
     *
     * @return list of ReportCategory
     */
    @Override
    public OperationResult<List<ReportCategory>> list() {
        log.info("Get list of categories...");

        G4Response response = g4HttpClient.sendRequest(request.list());

        List<ReportCategory> reportCategories =
                JsonConverter.jsonToObjectsList(response.getMessage(), ReportCategory[].class, "result");

        return new OperationResult<>(response, reportCategories);
    }

    @Override
    public OperationResult<ReportCategory> update(ReportCategory entity) {
        log.info("Update report category with id: " + entity.getId());

        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<ReportCategory> operationResult = new OperationResult<>(response, ReportCategory.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getReportCategories().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult<ReportCategory> view(String id) {
        log.info("Getting ReportCategory details id:" + id);

        G4Response response = g4HttpClient.sendRequest(request.view(id));

        OperationResult<ReportCategory> operationResult = new OperationResult<>(response, ReportCategory.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getReportCategories().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }
}
