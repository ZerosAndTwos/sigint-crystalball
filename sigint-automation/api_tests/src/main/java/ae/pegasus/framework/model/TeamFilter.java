package ae.pegasus.framework.model;

public class TeamFilter extends SearchFilter<Team> {

    private String teamId;
    private String teamSearchType; //"teamSearchType": "ANCESTORS"

    @Override
    public boolean isAppliedToEntity(Team entity) {
        return activeFilter.isAppliedToEntity(entity);
    }

    public String getTeamSearchType() {
        return teamSearchType;
    }

    public void setTeamSearchType(String teamSearchType) {
        this.teamSearchType = teamSearchType;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
