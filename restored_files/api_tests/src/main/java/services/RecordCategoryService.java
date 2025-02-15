package services;

import errors.OperationResultError;
import http.G4Response;
import http.OperationResult;
import http.requests.RecordCategoriesRequest;
import model.RecordCategory;
import model.RecordCategoryListResult;
import model.SearchFilter;
import model.entities.Entities;
import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.List;

public class RecordCategoryService implements EntityService<RecordCategory> {

    private static Logger logger = Logger.getLogger(RecordCategoryService.class);
    private static RecordCategoriesRequest request = new RecordCategoriesRequest();

    /**
     * Add a new one record-category.
     *
     * @param entity record-category for payload
     * @return HTTP status code
     */
    @Override
    public OperationResult<RecordCategory> add(RecordCategory entity) {
        logger.info("Add new one record category..");
        G4Response response = g4HttpClient.sendRequest(request.add(entity));

        OperationResult<RecordCategory> operationResult = new OperationResult<>(response, RecordCategory.class);
        if (operationResult.isSuccess()) {
            Entities.getRecordCategories().addOrUpdateEntity(entity);
        }
        return operationResult;
    }

    @Override
    public OperationResult remove(RecordCategory entity) {
        throw new NotImplementedException();
    }

    @Override
    public OperationResult<List<RecordCategory>> search(SearchFilter filter) {
        throw new NotImplementedException();
    }

    /**
     * Get list of record-categories
     *
     * @return list of record-categories
     */
    @Override
    public OperationResult<List<RecordCategory>> list() {
        G4Response response = g4HttpClient.sendRequest(request.list());

        OperationResult<RecordCategoryListResult> operationResult =
                new OperationResult<>(response, RecordCategoryListResult.class);

        if (operationResult.isSuccess()) {
            List<RecordCategory> recordCategories = operationResult.getEntity().getResult();
            return new OperationResult<>(response, recordCategories);
        } else {
            throw new OperationResultError(operationResult);
        }
    }

    @Override
    public OperationResult<RecordCategory> update(RecordCategory entity) {
        logger.info("Updating record-category id:" + entity.getId());
        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<RecordCategory> operationResult = new OperationResult<>(response);
        if (operationResult.isSuccess()) {
            Entities.getRecordCategories().addOrUpdateEntity(entity);
        }
        return operationResult;
    }

    /**
     * Get record-category by id
     *
     * @param id id of entity
     * @return RecordCategory
     */
    @Override
    public OperationResult<RecordCategory> view(String id) {
        G4Response response = g4HttpClient.sendRequest(request.view(id));

        OperationResult<RecordCategory> operationResult = new OperationResult<>(response, RecordCategory.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getRecordCategories().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }
}
