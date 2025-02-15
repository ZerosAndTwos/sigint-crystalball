package ae.pegasus.framework.services;

import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.SavedSearchRequest;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.SavedSearch;
import ae.pegasus.framework.model.SearchFilter;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class SavedSearchService implements EntityService<SavedSearch> {

    private static final SavedSearchRequest request = new SavedSearchRequest();
    private static final Logger log = Logger.getLogger(SavedSearchService.class);

    @Override
    public OperationResult<?> add(SavedSearch entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult remove(SavedSearch entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<List<SavedSearch>> search(SearchFilter filter) {
        log.info("Search savedSearches with filter: " + JsonConverter.toJsonString(filter));

        G4Response response = g4HttpClient.sendRequest(request.search(filter));
        log.info(response.getMessage());

        OperationResult<SavedSearch[]> operationResult = new OperationResult<>(response, SavedSearch[].class, "data");
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            return new OperationResult<>(response);
        }
    }

    @Override
    public OperationResult<List<SavedSearch>> list() {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<SavedSearch> update(SavedSearch entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<SavedSearch> view(String id) {
        throw new NotImplementedException("");
    }
}
