package ae.pegasus.framework.services;

import ae.pegasus.framework.csv.CSVWhitelistWriter;
import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.WhiteListRequest;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.model.entities.Entities;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ae.pegasus.framework.ingestion.IngestionService.INJECTIONS_FILE;
import static ae.pegasus.framework.json.JsonConverter.toJsonString;
import static ae.pegasus.framework.utils.StringUtils.saveStringToFile;
import static org.junit.Assert.assertNotNull;


public class WhitelistService implements EntityService<Whitelist> {

    private static final Logger log = Logger.getLogger(WhitelistService.class);
    private static WhiteListRequest request = new WhiteListRequest();

    @Override
    public OperationResult<Whitelist> add(Whitelist entity) {
        log.info("Creating new Whitelist..");
        G4Response response = g4HttpClient.sendRequest(request.add(entity));

        OperationResult<Whitelist> operationResult = new OperationResult<>(response, Whitelist.class);
        if (operationResult.isSuccess()) {
            Entities.getWhitelists().addOrUpdateEntity(operationResult.getEntity());
        }

        return operationResult;
    }

    @Override
    public OperationResult<Result> remove(Whitelist entity) {
        log.info("Deleting Whitelist with id: " + entity.getId());
        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult<Result> operationResult = new OperationResult<>(response, Result.class);
        if (operationResult.isSuccess()) {
            Entities.getWhitelists().removeEntity(entity);
        }
        return operationResult;
    }

    @Override
    public OperationResult<List<Whitelist>> search(SearchFilter filter) {
        log.info("Search whitelists, filter:\n" + toJsonString(filter));
        G4Response response = g4HttpClient.sendRequest(request.search(filter));

        OperationResult<WhitelistSearchResult> operationResult = new OperationResult<>(response, WhitelistSearchResult.class);
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, operationResult.getEntity().getResult());
        } else {
            return new OperationResult<>(response);
        }
    }

    @Override
    public OperationResult<List<Whitelist>> list() {
        log.info("Getting list of Whitelists");
        G4Response response = g4HttpClient.sendRequest(request.list());

        OperationResult<Whitelist[]> operationResult = new OperationResult<>(response, Whitelist[].class);
        if (operationResult.isSuccess() && operationResult.getEntity() != null) {
            List<Whitelist> whitelists = Arrays.asList(operationResult.getEntity());
            return new OperationResult<>(response, whitelists);
        } else {
            throw new OperationResultError(operationResult);
        }
    }

    @Override
    public OperationResult<Whitelist> update(Whitelist entity) {
        log.info("Updating Whitelist with id: " + entity.getId());

        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<Whitelist> operationResult = new OperationResult<>(response, entity);
        if (operationResult.isSuccess()) {
            Entities.getWhitelists().addOrUpdateEntity(entity);
        }
        return operationResult;
    }

    @Override
    public OperationResult<Whitelist> view(String id) {
        log.info("Getting Whitelist with id: " + id);
        G4Response response = g4HttpClient.sendRequest(request.get(id));

        OperationResult<Whitelist> operationResult = new OperationResult<>(response, Whitelist.class);

        if (operationResult.isSuccess()) {
            Entities.getWhitelists().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    public G4File createCSVFile(List<Whitelist> whitelists, boolean withHeader) {
        log.info("Writing " + whitelists.size() + " Whitelist entries to file..");

        CSVWhitelistWriter writer = new CSVWhitelistWriter("whitelists.csv", withHeader);
        G4File file = null;
        try {
            file = writer.writeEntitiesToCsv(whitelists);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return file;
    }

    public List<String> injectWhitelists(List<Whitelist> whitelists) {
        DataInjection injections = new DataInjection();
        List<String> phones = new ArrayList<>();

        for (Whitelist whitelist: whitelists) {
            // TODO: add email and twitter support
            if (whitelist.getType() == WhiteListType.PHONE_NUMBER) {
                phones.add(whitelist.getIdentifier());
            }
        }
        injections.setPhones(phones);
        String json = toJsonString(injections);
        log.info(json);
        saveStringToFile(json, INJECTIONS_FILE.toString());

        return phones;
    }

    public OperationResult<ImportResult> upload(G4File file) {
        assertNotNull("null object for whitelist upload", file);

        log.info("Import Whitelists from file..");
        G4Response response = g4HttpClient.sendRequest(request.upload(file));

        return new OperationResult<>(response, ImportResult.class);
    }
}
