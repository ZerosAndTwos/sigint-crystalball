package ae.pegasus.framework.services;

import ae.pegasus.framework.app_context.properties.G4Properties;
import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4HttpClient;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.SQMRequest;
import ae.pegasus.framework.model.*;
import org.apache.log4j.Logger;

import java.util.List;

import static ae.pegasus.framework.http.G4HttpClient.Protocol.HTTP;
import static ae.pegasus.framework.json.JsonConverter.jsonToObjectsList;
import static ae.pegasus.framework.json.JsonConverter.toJsonString;

public class SQMService {

    private static SQMRequest request = new SQMRequest();
    private static final Logger log = Logger.getLogger(SQMService.class);
    private static final G4HttpClient g4HttpClient = new G4HttpClient(G4Properties.getRunProperties().getApplicationURL(), HTTP);

    public OperationResult<String> search(SearchQueueRequest searchQueueRequest) {
        log.info("SQM search: " + toJsonString(searchQueueRequest));

        G4Response response = g4HttpClient.sendRequest(request.search(searchQueueRequest));

        OperationResult<String> operationResult = new OperationResult<>(response, String.class);
        if (operationResult.isSuccess()) {
            return operationResult;
        } else {
            throw new OperationResultError(operationResult);
        }
    }

    public OperationResult<SearchQueue> view(String id) {
        log.info("SQM view: " + id);

        G4Response response = g4HttpClient.sendRequest(request.view(id));

        OperationResult<SearchQueue> operationResult = new OperationResult<>(response, SearchQueue.class);
        if (operationResult.isSuccess()) {
            return operationResult;
        } else {
            throw new OperationResultError(operationResult);
        }
    }

    @SuppressWarnings("unchecked")
    public OperationResult<SearchResult<SearchEntity>> result(String id, DataSourceCategory sourceType, SearchObjectType objectType,
                                                      Integer pageNumber, Integer pageSize) {
        log.info("Get SQM result. SourceType:" + sourceType + " objectType:" + objectType + " page:" + pageNumber + " pageSize:" + pageSize);

        G4Response response = g4HttpClient.sendRequest(request.result(id, sourceType, objectType, pageNumber, pageSize));

        OperationResult<SearchResult[]> operationResult = new OperationResult<>(response, SearchResult[].class);
        if (operationResult.isSuccess()) {
            SearchResult result = operationResult.getEntity()[0];
            List<SearchEntity> entities = result.getData();
            if (!entities.isEmpty()) {
                switch (entities.get(0).getType()) {
                    case "TargetVO":
                        List<SearchResultTargets> searchResultTargets = jsonToObjectsList(response.getMessage(), SearchResultTargets[].class);
                        return new OperationResult(response, searchResultTargets.get(0));
                    case "EventVO":
                    case "EntityVO":
                        List<SearchResultRecords> searchResultRecords = jsonToObjectsList(response.getMessage(), SearchResultRecords[].class);
                        return new OperationResult(response, searchResultRecords.get(0));
                    default:
                        throw new AssertionError("Unknown entity type:" + entities.get(0).getType() + " in search response");
                }
            } else {
                List<SearchResultRecords> searchResultRecords = jsonToObjectsList(response.getMessage(), SearchResultRecords[].class);
                return new OperationResult(response, searchResultRecords.get(0));
            }
        } else {
            throw new OperationResultError(operationResult);
        }
    }
}
