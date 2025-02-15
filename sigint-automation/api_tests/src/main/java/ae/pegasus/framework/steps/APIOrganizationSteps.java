package ae.pegasus.framework.steps;

import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.Organization;
import ae.pegasus.framework.model.OrganizationFilter;
import ae.pegasus.framework.model.Team;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.services.OrganizationService;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.List;

import static ae.pegasus.framework.json.JsonConverter.jsonToObject;
import static ae.pegasus.framework.json.JsonConverter.toJsonString;
import static ae.pegasus.framework.utils.RandomGenerator.getRandomItemFromList;

@SuppressWarnings("unchecked")
public class APIOrganizationSteps extends APISteps {

    private static OrganizationService organizationService = new OrganizationService();
    private static APITeamSteps teamSteps = new APITeamSteps();


    @When("I send search users and teams by $criteria with $value request")
    public void searchUsersAndTeamsByCriteria(String criteria, String value) {
        Organization organization = Entities.getOrganizations().getLatest();

        switch (criteria.toLowerCase()) {
            case "name":
                value = value.equals("random") ? organization.getFullName() : value;
                break;
            case "orgtypes":
                value = value.equals("random") ? organization.getOrganizationType().name() : value;
                break;
            case "datasources":
                value = value.equals("random") ? organization.getDefaultPermission().getRecord().getDataSources().toString() : value;
                break;
            case "clearances":
                value = value.equals("random") ? organization.getDefaultPermission().getRecord().getClearances().toString() : value;
                break;
            case "titles":
                value = value.equals("random") ? organization.getDefaultPermission().getTitles().toString() : value;
                break;
            case "orgids":
                value = value.equals("random") ? organization.getId() : value;
                break;
            case "parentorgid":
                String parentTemId = organization.getParentTeamId() != null ?
                        organization.getParentTeamId() :
                        getRandomItemFromList(organization.getParentTeamIds());
                value = value.equals("random") ? parentTemId : value;
                break;
            default:
                throw new AssertionError("Unknown filter field for organization search: " + criteria);
        }

        OrganizationFilter filter = new OrganizationFilter().filterBy(criteria, value);
        OperationResult<List<Organization>> operationResult = organizationService.search(filter);

        context.put("organizationList", operationResult.getEntity());
        context.put("organizationFilter", filter);
    }

    @Given("OrgUnit with name:\"$name\" exists")
    public void getOrCreateOrgUnitByName(String name) {
        searchUsersAndTeamsByCriteria("name", name);
        List<Organization> organizations = context.get("organizationList", List.class);

        Organization organization = organizations.stream()
                .filter(org -> org.getFullName().equals(name))
                .findAny().orElse(null);

        if (organization == null) {
            teamSteps.createTeam(name);
        } else {
            Team team = jsonToObject(toJsonString(organization), Team.class);
            context.put("team", team);
        }
    }

    @Then("Users and Teams search result are correct")
    public void searchResultAreCorrect() {
        List<Organization> organizations = context.get("organizationList", List.class);
        OrganizationFilter organizationFilter = context.get("organizationFilter", OrganizationFilter.class);

        organizations.forEach(organization -> Assert.assertTrue(
                        "Entity does not applied to filter!" +
                                "\n Entity:" + toJsonString(organization) +
                                "\n Filter:" + toJsonString(organizationFilter),
                        organizationFilter.isAppliedToEntity(organization)));
    }

    @Then("Users and Teams list size more than $size")
    public void teamListSizeShouldBeMoreThan(String size) {
        List<Organization> organizations = context.get("organizationList", List.class);

        Assert.assertTrue(organizations.size() > Integer.valueOf(size));
    }

    @Then("Users and Teams list contains created user or team")
    public void usersAndTeamsListContainCreatedTeam() {
        List<Organization> organizations = context.get("organizationList", List.class);
        Organization organization = Entities.getOrganizations().getLatest();

        Assert.assertTrue(organizations.stream().anyMatch(org -> org.getId().equals(organization.getId())));
    }
}
