package ae.pegasus.framework.services;

import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.FinderFileRequest;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.model.entities.Entities;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static ae.pegasus.framework.json.JsonConverter.*;

public class FinderFileService implements EntityService<FinderFile> {

    private static final Logger log = Logger.getLogger(FinderFileService.class);
    private static final FinderFileRequest request = new FinderFileRequest();

    @Override
    public OperationResult<FinderFile> add(FinderFile entity) {
        log.info("Creating finder file:" + toJsonString(entity));
        G4Response response = g4HttpClient.sendRequest(request.add(entity));

        OperationResult<FinderFile> operationResult = new OperationResult<>(response, FinderFile.class, "data");
        if (operationResult.isSuccess()) {
            Entities.getFinderFiles().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult remove(FinderFile entity) {
        log.info("Deleting finder file id:" + entity.getId() + ", name:" + entity.getName());
        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult<String> deleteResult = new OperationResult<>(response);
        if (deleteResult.isSuccess()) {
            Entities.getFinderFiles().removeEntity(entity);
        }
        return deleteResult;
    }

    @Override
    public OperationResult<List<FinderFile>> search(SearchFilter filter) {
        throw new NotImplementedException("");
    }

    public OperationResult<List<FinderFile>> getFilesRoot(SearchFilter filter) {
        log.info("Get root of files, page:" + filter.getPage() + ", pageSize:" + filter.getPageSize());
        G4Response response = g4HttpClient.sendRequest(request.root(filter));

        OperationResult<FinderFile[]> operationResult = new OperationResult<>(response, FinderFile[].class, "data");
        return operationResult.isSuccess() ?
                new OperationResult<>(response, Arrays.asList(operationResult.getEntity())) :
                new OperationResult<>(response);
    }

    @Override
    public OperationResult<List<FinderFile>> list() {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<FinderFile> update(FinderFile entity) {
        log.info("Updating finder file: " + toJsonString(entity));
        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<FinderFile> operationResult = new OperationResult<>(response, FinderFile.class, "data");
        if (operationResult.isSuccess()) {
            Entities.getFinderFiles().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult<FinderFile> view(String id) {
        log.info("View finder file id:" + id);
        G4Response response = g4HttpClient.sendRequest(request.get(id));

        OperationResult<FinderFile> operationResult = new OperationResult<>(response, FinderFile.class, "data");
        if (operationResult.isSuccess()) {
            Entities.getFinderFiles().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    public OperationResult<FinderFile[]> getContent(String fileID) {
        log.info("Get content of finder file id: " + fileID);

        G4Response response = g4HttpClient.sendRequest(request.getContent(fileID));

        return new OperationResult<>(response, FinderFile[].class, "data");
    }

    public OperationResult<List<ProfileAndTargetGroup>> searchFileMembers(SearchFilter filter) {
        log.info("Get list of targets and finder files");
        G4Response response = g4HttpClient.sendRequest(request.search(filter));

        OperationResult<ProfileAndTargetGroup[]> operationResult =
                new OperationResult<>(response, ProfileAndTargetGroup[].class, "data");

        return operationResult.isSuccess() ?
                new OperationResult<>(response, Arrays.asList(operationResult.getEntity())) :
                new OperationResult<>(response);
    }

    public List extractEntitiesByTypeFromResponse(OperationResult<List<ProfileAndTargetGroup>> result, ProfilerJsonType type) {
        List<Object> list = jsonToObjectsList(result.getMessage(), Object[].class, "data");
        switch (type) {
            case Profile:
                return list.stream()
                        .filter(o -> ((LinkedHashMap) o).get("type").equals("Profile"))
                        .map(o -> jsonToObject(toJsonString(o), Profile.class))
                        .collect(Collectors.toList());
            case EnrichedFile:
            case File:
                return list.stream()
                        .filter(o -> ((LinkedHashMap) o).get("type").equals("File"))
                        .map(o -> jsonToObject(toJsonString(o), FinderFile.class))
                        .collect(Collectors.toList());
            default:
                throw new AssertionError("Not implemented extractEntitiesByTypeFromResponse() by type:" + type);
        }
    }

    public OperationResult<List<FinderFile>> cbFinderSearch(SearchFilter filter) {
        log.info("CB Finder search with filter:" + toJsonString(filter));
        G4Response response = g4HttpClient.sendRequest(request.cbFinderSearch(filter));

        OperationResult<FinderFile[]> operationResult =
                new OperationResult<>(response, FinderFile[].class, "data");

        return operationResult.isSuccess() ?
                new OperationResult<>(response, Arrays.asList(operationResult.getEntity())) :
                new OperationResult<>(response);
    }
}
