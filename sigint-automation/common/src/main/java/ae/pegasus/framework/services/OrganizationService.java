package ae.pegasus.framework.services;

import ae.pegasus.framework.data_for_entity.RandomEntities;
import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.OrganizationRequest;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.*;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class OrganizationService implements EntityService<Organization> {

    private static Logger log = Logger.getLogger(OrganizationService.class);
    private static OrganizationRequest request = new OrganizationRequest();

    @Override
    public OperationResult<?> add(Organization entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult remove(Organization entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<List<Organization>> search(SearchFilter filter) {
        log.info("Search organization by filter:" + JsonConverter.toJsonString(filter));

        G4Response response = g4HttpClient.sendRequest(request.search(filter));

        OperationResult<Organization[]> operationResult = new OperationResult<>(response, Organization[].class);
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, asList(operationResult.getEntity()));
        } else {
            return new OperationResult<>(response);
        }
    }

    @Override
    public OperationResult<List<Organization>> list() {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<Organization> update(Organization entity) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<Organization> view(String id) {
        throw new NotImplementedException("");
    }

    public Team getOrCreateTeamByName(String orgName) {
        OrganizationFilter filter = new OrganizationFilter()
                .setOrgTypes(Collections.singletonList("TEAM"))
                .filterBy("name", orgName);

        // search orgUnits by name
        OperationResult<List<Organization>> orgSearchResult = new OrganizationService().search(filter);

        if (orgSearchResult.isSuccess()) {
            // find team in response
            Organization organization = orgSearchResult.getEntity().stream()
                    .filter(org -> org.getOrganizationType().equals(OrganizationType.TEAM)
                            && org.getFullName().equals(orgName))
                    .findAny().orElse(null);

            TeamService teamService = new TeamService();
            // if not found, create new team (orgUnit)
            if (organization == null) {
                Team team = new RandomEntities().randomEntity(Team.class);
                team.setFullName(orgName);

                OperationResult<Team> teamResult = teamService.add(team);
                if (teamResult.isSuccess()) {
                    return teamResult.getEntity();
                } else {
                    throw new OperationResultError(teamResult);
                }
            } else {
                return teamService
                        .view(organization.getId())
                        .getEntity();
            }
        } else {
            throw new OperationResultError(orgSearchResult);
        }
    }
}
