package ae.pegasus.framework.steps;

import ae.pegasus.framework.data_for_entity.RandomEntities;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.Team;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.services.TeamService;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.List;
import java.util.regex.Pattern;

import static ae.pegasus.framework.json.JsonConverter.toJsonString;

@SuppressWarnings("unchecked")
public class APITeamManagementSteps extends APISteps {

    private static TeamService teamService = new TeamService();

    private Team generateRandomTeam() {
        return objectInitializer.randomEntity(Team.class);
    }

    @When("I send create a new team")
    public void createNewTeam() {
        Team team = generateRandomTeam();
        context.put("team", team);

        teamService.add(team);
    }

    @Then("Team is correct")
    public void createdTeamShouldBeCorrect() {
        Team team = context.get("team", Team.class);
        Team created = Entities.getTeams().getLatest();

        Assert.assertEquals(created.getDescription(), team.getDescription());
        Assert.assertEquals(toJsonString(created.getDefaultPermission()), toJsonString(team.getDefaultPermission()));
        Assert.assertEquals(created.getFullName(), team.getFullName());
        Assert.assertEquals(created.getOrganizationType(), team.getOrganizationType());
        Assert.assertEquals(created.getParentTeamId(), team.getParentTeamId());
    }

    @When("I send delete team request")
    public void deleteTeam() {
        Team team = Entities.getTeams().getLatest();
        teamService.remove(team);
    }

    @When("I send get list of teams")
    public void getListOfTeams() {
        OperationResult<List<Team>> operationResult = teamService.search(null);
        context.put("teamList", operationResult.getEntity());
    }

    @Then("Teams list size more than $size")
    public void teamListSizeShouldBeMoreThan(String size) {
        List<Team> teams = context.get("teamList", List.class);

        Assert.assertTrue(teams.size() > Integer.valueOf(size));
    }

    @Then("Team is $criteria the list")
    public void checkTeamInList(String criteria) {
        List<Team> teams = context.get("teamList", List.class);
        Team Team = context.get("team", Team.class);

        boolean matched = teams.stream()
                .anyMatch((r -> r.getId().equals(Team.getId())));

        if (criteria.equals("in")) {
            Assert.assertTrue(matched);
        } else {
            Assert.assertFalse(matched);
        }
    }

    @When("I send update team request")
    public void updateTeam() {
        Team team = Entities.getTeams().getLatest();

        Team updatedTeam = generateRandomTeam();
        updatedTeam.setId(team.getId());
        context.put("team", updatedTeam);

        teamService.update(updatedTeam);
    }

    @When("I send get team details request")
    public void getTeamDetails() {
        Team team = Entities.getTeams().getLatest();
        context.put("team", team);

        teamService.view(team.getId());
    }

    @When("I send create a new nested team")
    public void createNestedTeam() {
        Team parentTeam = Entities.getTeams().getLatest();
        Team nestedTeam = new RandomEntities().randomEntity(Team.class);
        nestedTeam.setParentTeamId(parentTeam.getId());

        context.put("team", nestedTeam);

        teamService.add(nestedTeam);
    }

    @When("I send move team to other parent team")
    public void moveTeamToOtherTeam() {
        Team team = Entities.getTeams().getLatest();
        List<Team> teams = Entities.getTeams().getEntities();

        Team newParentTeam = teams.stream()
                .filter(t -> !t.getParentTeamId().equals(team.getParentTeamId()) || !t.getId().equals(team.getId()))
                .findAny()
                .orElse(null);
        Assert.assertNotNull("New parent team for moving nested team not created", newParentTeam);

        team.setParentTeamId(newParentTeam.getId());
        context.put("team", team);

        teamService.update(team);
    }

    @When("I send delete parent team request")
    public void deleteParentTeam() {
        String parentTeamId = Entities.getTeams().getLatest().getParentTeamId();
        Team parentTeam = Entities.getTeams().getEntity(parentTeamId);

        teamService.remove(parentTeam);
    }


    @When("I delete all qe empty teams")
    public void deleteAllEmptyTeams() {
        List<Team> teams = context.get("teamList", List.class);
        teams.stream()
                .filter(team -> team.getDescription() != null && !team.getDescription().isEmpty()
                        && Pattern.compile("qe_").matcher(team.getFullName()).find())
                .forEach(team -> teamService.remove(team));
    }
}
