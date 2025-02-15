package ae.pegasus.framework.services;

import ae.pegasus.framework.app_context.properties.G4Properties;
import ae.pegasus.framework.http.G4HttpClient;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.DictionaryRequest;
import ae.pegasus.framework.model.Dictionary;
import ae.pegasus.framework.model.SourceType;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

import static ae.pegasus.framework.http.G4HttpClient.Protocol.HTTP;

public class DictionaryService {

    private static final Logger log = Logger.getLogger(DictionaryService.class);
    private static G4HttpClient g4HttpClient = new G4HttpClient(G4Properties.getRunProperties().getApplicationURL(), HTTP);
    private static DictionaryRequest request = new DictionaryRequest();

    private static OperationResult<List<SourceType>> getSources(DictionaryRequest httpRequest) {

        G4Response response = g4HttpClient.sendRequest(httpRequest);

        OperationResult<SourceType[]> operationResult = new OperationResult<>(response, SourceType[].class);
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            return new OperationResult<>(response);
        }
    }

    public static OperationResult<List<SourceType>> getAllSources() {
        log.info("Dictionaries. Get sources.");

        return getSources(request.listAvailableSources());
    }

    public static OperationResult<List<SourceType>> getManualSources() {
        log.info("Dictionaries. Get manual sources.");

        return getSources(request.listAvailableManualSources());
    }

    public static Dictionary loadDictionary() {
        log.info("Load workflow dictionary.");
        Dictionary dictionary = new Dictionary();

        OperationResult<List<SourceType>> resultSources = getAllSources();
        if (resultSources.isSuccess()) {
            dictionary.setSourceTypes(resultSources.getEntity());
        } else {
            log.error("Can't get the sources dict: " + resultSources.getMessage());
        }

        return dictionary;
    }
}
