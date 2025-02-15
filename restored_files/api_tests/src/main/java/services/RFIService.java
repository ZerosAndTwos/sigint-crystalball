package services;

import abs.EntityList;
import abs.SearchFilter;
import app_context.entities.Entities;
import errors.NullReturnException;
import http.G4HttpClient;
import http.G4Response;
import http.requests.rfi.*;
import json.JsonConverter;
import model.InformationRequest;
import model.rfi.RFISearchResults;
import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;

public class RFIService implements EntityService<InformationRequest> {

    private Logger log = Logger.getLogger(RFIService.class);
    private static G4HttpClient g4HttpClient = new G4HttpClient();


    private void readRFIFromResponse(G4Response response) {
        InformationRequest createdRFI = JsonConverter.readEntityFromResponse(response, InformationRequest.class, "result");
        if (createdRFI != null) {
            Entities.getRFIs().addOrUpdateEntity(createdRFI);
        }
    }

    /**
     * API: POST "/api/rfi/upload" multipartUpload
     *
     * @param entity RFI
     * @return HTTP status code
     */
    @Override
    public int add(InformationRequest entity) {
        RFIUploadRequest request = new RFIUploadRequest(entity);
        G4Response response = g4HttpClient.sendRequest(request);

        readRFIFromResponse(response);
        return response.getStatus();
    }

    /**
     * API: DELETE /api/rfi/
     *
     * @param entity RFI
     * @return HTTP status code
     */
    @Override
    public int remove(InformationRequest entity) {
        RFIDeleteRequest deleteRequest = new RFIDeleteRequest(entity.getId());
        G4Response response = g4HttpClient.sendRequest(deleteRequest);

        if (response.getStatus() == 200) {
            try {
                Entities.getRFIs().removeEntity(entity);
            } catch (NullReturnException e) {
                log.warn("Was unable to remove entity with id " + entity.getId() + " as it" +
                        "doesn't in the list");
            }
        }
        return response.getStatus();
    }

    /**
     * API: POST /api/rfi/search
     *
     * @param filter search filter for payload
     * @return EntityList of RFI
     */
    @Override
    public EntityList<InformationRequest> list(SearchFilter filter) {
        RFISearchRequest request = new RFISearchRequest(filter);
        G4Response response = g4HttpClient.sendRequest(request);

        RFISearchResults searchResults = JsonConverter.readEntityFromResponse(response, RFISearchResults.class, "result");
        if (searchResults == null) {
            throw new AssertionError("Unable to read search results from RFI search");
        } else {
            return new EntityList<InformationRequest>(searchResults.getContent()) {
                public InformationRequest getEntity(String param) throws NullReturnException {
                    throw new NotImplementedException();
                }
            };
        }
    }

    /**
     * Used ADD method
     * API: POST "/api/rfi/upload" multipartUpload
     *
     * @param entity entity
     * @return HTTP status code
     */
    @Override
    public int update(InformationRequest entity) {
        return add(entity);
    }

    /**
     * GET /api/rfi/{id}
     *
     * @param id id of entity
     * @return InformationRequest (RFI)
     */
    @Override
    public InformationRequest view(String id) {
        log.info("Getting details of RFI by id:" + id);
        RFIDetailsRequest request = new RFIDetailsRequest(id);
        G4Response response = g4HttpClient.sendRequest(request);
        return JsonConverter.fromJsonToObject(response.getMessage(), RFIDetailsResponse.class).getResult();
    }

    /**
     * POST /api/rfi/{id}/CANCELLED
     *
     * @param entity RFI
     * @return HTTP status code
     */
    public int cancel(InformationRequest entity) {
        RFICancelRequest request = new RFICancelRequest(entity.getId());
        G4Response response = g4HttpClient.sendRequest(request);
        readRFIFromResponse(response);
        return response.getStatus();
    }

    /**
     * POST /api/rfi/{id}/assign
     *
     * @param entity RFI
     * @return HTTP status code
     */
    public int assign(InformationRequest entity) {
        RFIAssignRequest request = new RFIAssignRequest(entity.getId());
        G4Response response = g4HttpClient.sendRequest(request);
        readRFIFromResponse(response);
        return response.getStatus();
    }

}
