package ae.pegasus.framework.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ae.pegasus.framework.json.JsonConverter.toJsonString;
import static ae.pegasus.framework.utils.StringUtils.splitToList;

public class OrganizationFilter extends SearchFilter<Organization> {

    private String name;
    private String parentOrgId;
    private List<String> orgIds = new ArrayList<>();
    private List<String> orgTypes = Arrays.asList("TEAM", "USER");
    private List<String> dataSources = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<String> clearances = new ArrayList<>();

    @Override
    public boolean isAppliedToEntity(Organization entity) {
        return activeFilter.isAppliedToEntity(entity);
    }

    public List<String> getOrgTypes() {
        return orgTypes;
    }

    public OrganizationFilter setOrgTypes(List<String> orgTypes) {
        this.orgTypes = orgTypes;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<String> dataSources) {
        this.dataSources = dataSources;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getClearances() {
        return clearances;
    }

    public void setClearances(List<String> clearances) {
        this.clearances = clearances;
    }

    public List<String> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<String> orgIds) {
        this.orgIds = orgIds;
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    class OrgTypesFilter extends SearchFilter<Organization> {

        OrgTypesFilter(List<String> values) {
            orgTypes = values;
        }

        @Override
        public boolean isAppliedToEntity(Organization entity) {
            return orgTypes.contains(entity.getOrganizationType().name());
        }
    }

    class NameFilter extends SearchFilter<Organization> {

        NameFilter(String value) {
            name = value;
        }

        @Override
        public boolean isAppliedToEntity(Organization entity) {
            return entity.getFullName().contains(name);
        }
    }

    class DataSourcesFilter extends SearchFilter<Organization> {

        DataSourcesFilter(List<String> values) {
            dataSources = values;
        }

        @Override
        public boolean isAppliedToEntity(Organization entity) {
            List<String> entityDataSources;
            try {
                entityDataSources = entity.getDefaultPermission().getRecord().getDataSources();
            } catch (NullPointerException npe) {
                entityDataSources = null;
            }

            return isAppliedToFilterList(entityDataSources, dataSources);
        }
    }

    class TitleFilter extends SearchFilter<Organization> {

        TitleFilter(List<String> values) {
            titles = values;
        }

        @Override
        public boolean isAppliedToEntity(Organization entity) {
            List<String> entityTitles;
            try {
                entityTitles = entity.getDefaultPermission().getTitles();
            } catch (NullPointerException npe) {
                entityTitles = null;
            }

            return isAppliedToFilterList(entityTitles, titles);
        }
    }

    class ClearanceFilter extends SearchFilter<Organization> {

        ClearanceFilter(List<String> values) {
            clearances = values;
        }

        @Override
        public boolean isAppliedToEntity(Organization entity) {
            List<String> entityClearances;
            try {
                entityClearances = entity.getDefaultPermission().getRecord().getClearances();
            } catch (NullPointerException npe) {
                entityClearances = null;
            }

            return isAppliedToFilterList(entityClearances, clearances);
        }
    }

    private class ParentOrgIdFilter extends SearchFilter<Organization> {

        ParentOrgIdFilter(String value) {
            parentOrgId = value;
        }

        @Override
        public boolean isAppliedToEntity(Organization entity) {
            switch (entity.getOrganizationType()) {
                case TEAM:
                    return entity.getParentTeamId().contains(parentOrgId);
                case USER:
                    return entity.getParentTeamIds().contains(parentOrgId);
                default:
                    throw new AssertionError("Unknown organization type:" + entity.getOrganizationType()
                            + "\nEntity:" + toJsonString(entity));
            }
        }
    }

    private class OrgIdsFilter extends SearchFilter<Organization> {

        OrgIdsFilter(List<String> values) {
            orgIds = values;
        }

        @Override
        public boolean isAppliedToEntity(Organization entity) {
            return orgIds.stream().anyMatch(orgId -> orgId.equals(entity.getId()));
        }
    }

    /**
     * Init or update Organizations filter.
     * Filter is used in OrganizationService for receive list of Organizations.
     *
     * @param criteria filter field
     * @param value    value of filter field
     * @return filter entity for Organizations
     */
    public OrganizationFilter filterBy(String criteria, String value) {
        switch (criteria.toLowerCase()) {
            case "name":
                this.setActiveFilter(this.new NameFilter(value));
                break;
            case "orgtypes":
                this.setActiveFilter(this.new OrgTypesFilter(splitToList(value)));
                break;
            case "datasources":
                this.setActiveFilter(this.new DataSourcesFilter(splitToList(value)));
                break;
            case "titles":
                this.setActiveFilter(this.new TitleFilter(splitToList(value)));
                break;
            case "clearances":
                this.setActiveFilter(this.new ClearanceFilter(splitToList(value)));
                break;
            case "orgids":
                this.setActiveFilter(this.new OrgIdsFilter(splitToList(value)));
                break;
            case "parentorgid":
                this.setActiveFilter(this.new ParentOrgIdFilter(value));
                break;
            default:
                throw new AssertionError("Unknown filter field for organization search: " + criteria);
        }
        return this;
    }
}
