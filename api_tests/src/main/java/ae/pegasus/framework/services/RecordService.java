package ae.pegasus.framework.services;

import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.RecordRequest;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.Record;
import ae.pegasus.framework.model.RecordSearchResult;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.entities.Entities;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.List;

public class RecordService implements EntityService<Record> {

    private static Logger log = Logger.getLogger(RecordService.class);
    private static RecordRequest request = new RecordRequest();

    /**
     * ADD new manual record
     * API: POST /api/sigint/record/manual
     *
     * @param entity entity
     * @return HTTP status code
     */
    @Override
    public OperationResult<Record> add(Record entity) {
        log.info("Creating new record");
        G4Response response = g4HttpClient.sendRequest(request.manual(entity));

        OperationResult<Record> operationResult = new OperationResult<>(response, Record.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getRecords().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult remove(Record entity) {
        throw new NotImplementedException("");
    }

    /**
     * Search list of records
     * API: POST "/api/sigint/records/search?withTargets=true"
     *
     * @param filter search filter for payload
     * @return List of record
     */
    @Override
    public OperationResult<List<Record>> search(SearchFilter filter) {
        log.info("Search records by filter:" + JsonConverter.toJsonString(filter));
        G4Response response = g4HttpClient.sendRequest(request.search(filter));

        OperationResult<RecordSearchResult> operationResult = new OperationResult<>(response, RecordSearchResult.class);
        if (operationResult.isSuccess()) {
            List<Record> records = operationResult.getEntity().getResult();
            log.info("Founded " + records.size() + " records");
            return new OperationResult<>(response, records);
        } else {
            throw new OperationResultError(operationResult);
        }
    }

    @Override
    public OperationResult<List<Record>> list() {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<Record> update(Record entity) {
        throw new RuntimeException();
    }

    @Override
    public OperationResult<Record> view(String id) {
        throw new RuntimeException();
    }
}