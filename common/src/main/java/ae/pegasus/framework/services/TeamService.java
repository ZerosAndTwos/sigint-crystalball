package ae.pegasus.framework.services;

import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.TeamRequest;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.AuthResponseResult;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.Team;
import ae.pegasus.framework.model.entities.Entities;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class TeamService implements EntityService<Team> {

    private static final Logger log = Logger.getLogger(TeamService.class);
    private static final TeamRequest request = new TeamRequest();

    @Override
    public OperationResult<Team> view(String id) {
        log.info("Getting Team details by id: " + id);

        G4Response response = g4HttpClient.sendRequest(request.view(id));

        OperationResult<Team> operationResult = new OperationResult<>(response, Team.class);
        if (operationResult.isSuccess()) {
            Entities.getTeams().addOrUpdateEntity(operationResult.getEntity());
            Entities.getOrganizations().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult<Team> add(Team entity) {
        log.info("Team creating");

        G4Response response = g4HttpClient.sendRequest(request.create(entity));

        OperationResult<Team> operationResult = new OperationResult<>(response, Team.class);
        if (operationResult.isSuccess()) {
            Team team = operationResult.getEntity();
            log.info("New Team id: " + team.getId() + " and fullname: " + team.getFullName());
            Entities.getTeams().addOrUpdateEntity(team);
            Entities.getOrganizations().addOrUpdateEntity(team);
        }
        return operationResult;
    }

    @Override
    public OperationResult<Team> update(Team entity) {
        log.info("Update Team with id: " + entity.getId() + " name: " + entity.getFullName());

        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<Team> operationResult = new OperationResult<>(response, Team.class);
        if (operationResult.isSuccess()) {
            Entities.getTeams().addOrUpdateEntity(operationResult.getEntity());
            Entities.getOrganizations().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult<AuthResponseResult> remove(Team entity) {
        log.info("Deleting Team with id:" + entity.getId() + " and fullname: " + entity.getFullName());

        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult<AuthResponseResult> operationResult = new OperationResult<>(response, AuthResponseResult.class);
        if (operationResult.isSuccess()) {
            Entities.getTeams().removeEntity(entity);
            Entities.getOrganizations().removeEntity(entity);
        } else {
            log.warn(operationResult.getCode() + " " + operationResult.getMessage());
        }
        return operationResult;
    }

    @Override
    public OperationResult<List<Team>> search(SearchFilter filter) {
        log.info("Search teams by filter: " + JsonConverter.toJsonString(filter));

        G4Response response = g4HttpClient.sendRequest(request.search(filter));

        OperationResult<Team[]> operationResult = new OperationResult<>(response, Team[].class);
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            return new OperationResult<>(response);
        }
    }

    @Override
    public OperationResult<List<Team>> list() {
        throw new NotImplementedException("");
    }

}
