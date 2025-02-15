package services;

import model.SearchFilter;
import http.G4Response;
import json.JsonConverter;
import http.OperationResult;
import http.requests.RecordsSearchRequest;
import model.ProfileEntity;
import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.List;

public class RecordsSearchService implements EntityService<ProfileEntity> {

    private static final RecordsSearchRequest request = new RecordsSearchRequest();
    private static final Logger log = Logger.getLogger(RecordsSearchService.class);

    @Override
    public OperationResult<?> add(ProfileEntity entity) {
        throw new NotImplementedException();
    }

    @Override
    public OperationResult remove(ProfileEntity entity) {
        throw new NotImplementedException();
    }

    @Override
    public OperationResult<List<ProfileEntity>> search(SearchFilter filter) {
        log.info("Records search with filter: " + JsonConverter.toJsonString(filter));
        G4Response response = g4HttpClient.sendRequest(request.search(filter));

        List<ProfileEntity> profileEntities =
                JsonConverter.jsonToObjectsList(response.getMessage(), ProfileEntity[].class, "data");

        return new OperationResult<>(response, profileEntities);
    }

    @Override
    public OperationResult<List<ProfileEntity>> list() {
        throw new NotImplementedException();
    }

    @Override
    public OperationResult<ProfileEntity> update(ProfileEntity entity) {
        throw new NotImplementedException();
    }

    @Override
    public OperationResult<ProfileEntity> view(String id) {
        throw new NotImplementedException();
    }
}
