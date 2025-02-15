package ae.pegasus.framework.steps;

import ae.pegasus.framework.model.Team;
import ae.pegasus.framework.services.TeamService;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.When;

@SuppressWarnings("unchecked")
public class APITeamSteps extends APISteps {

    private static final Logger log = Logger.getLogger(APITeamSteps.class);
    private TeamService teamService = new TeamService();

    @When("I create new team")
    public void createTeam() {
        Team team = objectInitializer.randomEntity(Team.class);
        teamService.add(team);
        context.put("team", team);
    }

    @When("I create new team with name:$name")
    public void createTeam(String name) {
        Team team = objectInitializer.randomEntity(Team.class);
        team.setFullName(name);

        teamService.add(team);
        context.put("team", team);
    }
}
