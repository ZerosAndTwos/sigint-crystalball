package ae.pegasus.framework.services;

import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.DesignationsRequest;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.Designation;
import ae.pegasus.framework.model.DesignationSearchResult;
import ae.pegasus.framework.model.Result;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.entities.Entities;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class DesignationService implements EntityService<Designation> {

  private static final Logger log = Logger.getLogger(DesignationService.class);
  private static DesignationsRequest request = new DesignationsRequest();

  /**
   * API: PUT /api/admin/designations
   *
   * @param entity Designation entity
   * @return HTTP status code
   */
  @Override
  public OperationResult<Designation> add(Designation entity) {
    log.info("Creating new Designation..");

    G4Response response = g4HttpClient.sendRequest(request.add(entity));

    OperationResult<Designation> operationResult = new OperationResult<>(response, Designation.class);
    if (operationResult.isSuccess()) {
      Entities.getDesignations().addOrUpdateEntity(operationResult.getEntity());
    }
    return operationResult;
  }

  /**
   * API: DELETE /api/admin/designations/{id}
   *
   * @param entity Designation entity
   * @return HTTP status code
   */
  @Override
  public OperationResult<Result> remove(Designation entity) {
    log.info("Deleting Designation id: " + entity.getId());

    G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

    OperationResult<Result> operationResult = new OperationResult<>(response, Result.class);
    if (operationResult.isSuccess()) {
      Entities.getDesignations().removeEntity(entity);
    }
    return operationResult;
  }

  @Override
  public OperationResult<List<Designation>> search(SearchFilter filter) {
    log.info("Search list of designations, filter: " + JsonConverter.toJsonString(filter));
    G4Response response = g4HttpClient.sendRequest(request.search(filter));

    OperationResult<DesignationSearchResult> operationResult = new OperationResult<>(response, DesignationSearchResult.class);
    if (operationResult.isSuccess()) {
      return new OperationResult<>(response, operationResult.getEntity().getResult());
    } else {
      return new OperationResult<>(response);
    }
  }

  /**
   * GET list of Designations.
   * API: GET /api/admin/designations getDesignations
   *
   * @return HTTP status code
   */
  @Override
  public OperationResult<List<Designation>> list() {
    log.info("Getting list of Designations");
    G4Response response = g4HttpClient.sendRequest(request.list());

    OperationResult<Designation[]> operationResult = new OperationResult<>(response, Designation[].class);

    if (operationResult.isSuccess() && operationResult.getEntity() != null) {
      List<Designation> designations = Arrays.asList(operationResult.getEntity());

      return new OperationResult<>(response, designations);
    } else {
      throw new OperationResultError(operationResult);
    }
  }

  /**
   * API: POST /api/admin/designations
   *
   * @param entity Designation entity
   * @return HTTP status code
   */
  @Override
  public OperationResult<Designation> update(Designation entity) {
    log.info("Updating Designation id: " + entity.getId());

    G4Response response = g4HttpClient.sendRequest(request.update(entity));

    OperationResult<Designation> operationResult = new OperationResult<>(response);
    if (operationResult.isSuccess()) {
      Entities.getDesignations().addOrUpdateEntity(entity);
      return new OperationResult<>(response, entity);
    } else {
      log.error("Error! Update designation was failed");
      return operationResult;
    }
  }

  /**
   * API: GET /api/admin/designations/{id}
   *
   * @param id id of entity
   * @return Designation entity
   */
  @Override
  public OperationResult<Designation> view(String id) {
    log.info("View Designation details, id: " + id);

    G4Response response = g4HttpClient.sendRequest(request.view(id));

    return new OperationResult<>(response, Designation.class);
  }
}
