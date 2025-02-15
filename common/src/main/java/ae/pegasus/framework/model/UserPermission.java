package ae.pegasus.framework.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserPermission {

    private List<String> titles = new ArrayList<>();
    private List<TeamTitle> teamTitles = new ArrayList<>();
    private List<String> actions = new ArrayList<>();
    private PermissionRecord record = new PermissionRecord();

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public PermissionRecord getRecord() {
        return record;
    }

    public void setRecord(PermissionRecord record) {
        this.record = record;
    }

    public List<TeamTitle> getTeamTitles() {
        return teamTitles;
    }

    public void setTeamTitles(List<TeamTitle> teamTitles) {
        this.teamTitles = teamTitles;
    }

    public class PermissionRecord {

        private List<String> clearances = new ArrayList<>();
        private List<String> dataSources = new ArrayList<>();
        private List<String> organizations = new ArrayList<>();

        public List<String> getClearances() {
            return clearances;
        }

        public void setClearances(List<String> clearances) {
            this.clearances = clearances;
        }

        public List<String> getDataSources() {
            return dataSources;
        }

        public void setDataSources(List<String> dataSources) {
            this.dataSources = dataSources;
        }

        public List<String> getOrganizations() {
            return organizations;
        }

        public void setOrganizations(List<String> organizations) {
            this.organizations = organizations;
        }
    }
}
