package services;

import model.SearchFilter;
import model.entities.Entities;
import errors.OperationResultError;
import file_generator.FileGenerator;
import http.G4Response;
import http.OperationResult;
import http.requests.phonebook.DuSubscriberRequest;
import model.DuSubscriberEntry;
import model.G4File;
import model.UploadResult;
import model.phonebook.DuSubscriberSearchResult;
import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DuSubscriberService implements EntityService<DuSubscriberEntry> {

    private static Logger log = Logger.getLogger(DuSubscriberService.class);
    private static DuSubscriberRequest request = new DuSubscriberRequest();

    /**
     * ADD new Du Subscriber entry.
     * Used UPLOAD API: POST /du-subscribers/upload uploadMultipart.
     *
     * @param entity Du Subscriber entry
     * @return HTTP status code
     */
    @Override
    public OperationResult<UploadResult> add(DuSubscriberEntry entity) {
        log.info("Add duSubscriber Entry..");
        List<DuSubscriberEntry> entries = new ArrayList<>();
        entries.add(entity);

        OperationResult<UploadResult> operationResult = upload(entries);
        if (operationResult.isSuccess()) {
            Entities.getDuSubscriberses().addOrUpdateEntity(entity);
        }
        return operationResult;
    }

    /**
     * UPLOAD list of Du Subscriber entries.
     * API: POST /du-subscribers/upload uploadMultipart
     *
     * @param entries list of Du Subscriber entries.
     * @return HTTP status code
     */
    private OperationResult<UploadResult> upload(List<DuSubscriberEntry> entries) {
        log.info("Writing DuSubscriberEntry to csv file..");
        G4File file = new FileGenerator(DuSubscriberEntry.class).write(entries);

        log.info("Upload file: " + file.getName() + " with " + entries.size() + " duSubscriber entries..");
        G4Response response = g4HttpClient.sendRequest(request.upload(file));

        return new OperationResult<>(response, UploadResult.class, "result");
    }

    @Override
    public OperationResult remove(DuSubscriberEntry entity) {
        throw new NotImplementedException();
    }

    /**
     * Search list Du Subscriber entries.
     * API: POST /du-subscribers/search search
     *
     * @param filter DuSubscriberFilter search filter
     * @return EntityList of Du Subscriber entries
     */
    @Override
    public OperationResult<List<DuSubscriberEntry>> search(SearchFilter filter) {
        log.info("Getting list of duSubscriber entries..");
        G4Response response = g4HttpClient.sendRequest(request.search(filter));

        OperationResult<DuSubscriberSearchResult> operationResult =
                new OperationResult<>(response, DuSubscriberSearchResult.class, "result");

        if (operationResult.isSuccess() && operationResult.getEntity() != null) {
            return new OperationResult<>(response, operationResult.getEntity().getResult());
        } else {
            throw new OperationResultError(operationResult);
        }
    }

    @Override
    public OperationResult<List<DuSubscriberEntry>> list() {
        throw new NotImplementedException();
    }

    @Override
    public OperationResult<DuSubscriberEntry> update(DuSubscriberEntry entity) {
        throw new NotImplementedException();
    }

    /**
     * GET Du Subscriber entry
     * API: GET /du-subscribers/entries/{id} getEntry
     *
     * @param id id of entry
     * @return Du Subscriber entry
     */
    @Override
    public OperationResult<DuSubscriberEntry> view(String id) {
        log.info("Getting derails of duSubscriber Entry by id: " + id);
        G4Response response = g4HttpClient.sendRequest(request.get(id));

        return new OperationResult<>(response, DuSubscriberEntry.class, "result");
    }

}
