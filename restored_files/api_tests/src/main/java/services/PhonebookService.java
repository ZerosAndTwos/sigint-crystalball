package services;

import errors.OperationResultError;
import file_generator.FileGenerator;
import http.G4Response;
import http.OperationResult;
import http.requests.phonebook.PhonebookRequest;
import http.requests.phonebook.UnifiedPhonebookSearchRequest;
import json.JsonConverter;
import model.G4File;
import model.Phonebook;
import model.SearchFilter;
import model.UploadResult;
import model.entities.Entities;
import model.phonebook.PhonebookSearchResults;
import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.List;

public class PhonebookService implements EntityService<Phonebook> {

    private static Logger log = Logger.getLogger(PhonebookService.class);
    private static PhonebookRequest request = new PhonebookRequest();

    /*
        TODO
        POST /phonebook/search search

        GET /phonebook/sources getAll
        POST /phonebook/sources create
        POST /phonebook/sources/search search
        DELETE /phonebook/sources/{id} delete
        GET /phonebook/sources/{id} get
        POST /phonebook/sources/{id} update
        POST /phonebook/sources/{id}/undelete undelete

        PUT /phonebook/uploadBinary uploadBinary
    */

    /**
     * POST /phonebook/entries addEntry
     *
     * @param entity Phonebook entry
     * @return * @return HTTP status code
     */
    @Override
    public OperationResult<Phonebook> add(Phonebook entity) {
        log.info("Creating new Phonebook entry");
        log.debug(JsonConverter.toJsonString(entity));
        G4Response response = g4HttpClient.sendRequest(request.add(entity));

        OperationResult<Phonebook> operationResult = new OperationResult<>(response, Phonebook.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getPhonebooks().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    /**
     * DELETE /phonebook/entries/{id} deleteEntry
     *
     * @param entity Phonebook entity
     * @return HTTP status code
     */
    @Override
    public OperationResult remove(Phonebook entity) {
        log.info("Delete Phonebook entry id:" + entity.getId());
        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult operationResult = new OperationResult(response);
        if (operationResult.isSuccess()) {
            Entities.getPhonebooks().removeEntity(entity);
        }
        return operationResult;
    }

    /**
     * GET list of Unified Phonebook subscriber entries: Phonebook, Du and Etisalat subscriber.
     * API: POST /unified-phonebook/search search
     *
     * @param filter search filter for payload
     * @return List of Unified Phonebook subscriber entries
     */
    @Override
    public OperationResult<List<Phonebook>> search(SearchFilter filter) {
        UnifiedPhonebookSearchRequest request = new UnifiedPhonebookSearchRequest(filter);
        G4Response response = g4HttpClient.sendRequest(request);

        OperationResult<PhonebookSearchResults> operationResult =
                new OperationResult<>(response, PhonebookSearchResults.class, "result");

        if (operationResult.isSuccess() && operationResult.getEntity() != null) {
            return new OperationResult<>(response, operationResult.getEntity().getResult());
        } else {
            throw new OperationResultError(operationResult);
        }
    }

    @Override
    public OperationResult<List<Phonebook>> list() {
        throw new NotImplementedException();
    }

    /**
     * POST /phonebook/entries/{id} updateEntry
     *
     * @param entity entity
     * @return HTTP status code
     */
    @Override
    public OperationResult<Phonebook> update(Phonebook entity) {
        log.info("Updating Phonebook entry id:" + entity.getId());
        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<Phonebook> operationResult = new OperationResult<>(response, Phonebook.class, "result");
        if (operationResult.isSuccess()) {
            Entities.getPhonebooks().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    /**
     * GET /phonebook/entries/{id} getEntry
     *
     * @param id id of entity
     * @return Phonebook entry
     */
    @Override
    public OperationResult<Phonebook> view(String id) {
        log.info("View Phonebook entry id:" + id);
        G4Response response = g4HttpClient.sendRequest(request.get(id));

        return new OperationResult<>(response, Phonebook.class, "result");
    }

    /**
     * POST /phonebook/upload uploadMultipart
     *
     * @param phonebooks list of Phonebook entries
     * @return HTTP status code
     */
    public OperationResult<UploadResult> upload(List<Phonebook> phonebooks) {
        log.info("Writing Phonebook entries to file..");
        G4File file = new FileGenerator(Phonebook.class).write(phonebooks);

        log.info("Upload file with " + phonebooks.size() + " Phonebook entries..");
        G4Response response = g4HttpClient.sendRequest(request.upload(file));

        return new OperationResult<>(response, UploadResult.class, "result");
    }

}
