package ae.pegasus.framework.services;

import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.SearchRequest;
import ae.pegasus.framework.model.CBSearchFilter;
import ae.pegasus.framework.model.SearchEntity;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.SearchResult;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ae.pegasus.framework.json.JsonConverter.toJsonString;

public class ExcelSearchService implements EntityService<SearchEntity> {

    private static final SearchRequest request = new SearchRequest();
    private static final Logger log = Logger.getLogger(ExcelSearchService.class);

    @Override
    public OperationResult<?> add(SearchEntity entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult remove(SearchEntity entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<List<SearchEntity>> search(SearchFilter filter) {
        throw new NotImplementedException("");
    }

    @SuppressWarnings("unchecked")
    public G4Response search(CBSearchFilter filter) {
        log.info("CB_Search with filter: " + toJsonString(filter));
        G4Response response = g4HttpClient.sendRequest(request.search(filter));
        return response;
    }

    @Override
    public OperationResult<List<SearchEntity>> list() {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<SearchEntity> update(SearchEntity entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<SearchEntity> view(String id) {
        throw new NotImplementedException("");
    }

    public OperationResult<SearchResult[]> count(CBSearchFilter filter) {
        log.info("CB_Search_count with filter: " + toJsonString(filter));
        G4Response response = g4HttpClient.sendRequest(request.count(filter));
        log.info(response.getMessage());

        OperationResult<SearchResult[]> result = new OperationResult<>(response, SearchResult[].class);
        if (result.isSuccess() &&
                Arrays.stream(result.getEntity())
                        .allMatch(r -> Objects.equals(r.getStatus().getCode(), SearchResult.CodeStatus.SUCCESS))) {
            return result;
        } else {
            throw new OperationResultError(result);
        }
    }
}
