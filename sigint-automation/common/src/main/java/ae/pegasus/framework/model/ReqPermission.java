package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import ae.pegasus.framework.data_for_entity.annotations.DataProvider;
import ae.pegasus.framework.data_for_entity.data_providers.OrgUnitProvider;
import ae.pegasus.framework.data_for_entity.data_providers.profiler.ClassificationProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class ReqPermission {

    @DataIgnore
    private String actionPermission;
    @DataProvider(ClassificationProvider.class)
    private String classification;
    @DataIgnore
    private List<String> dataSources = new ArrayList<>();
    @DataProvider(OrgUnitProvider.class)
    private String orgUnit;

    public String getActionPermission() {
        return actionPermission;
    }

    public void setActionPermission(String actionPermission) {
        this.actionPermission = actionPermission;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }

    public List<String> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<String> dataSources) {
        this.dataSources = dataSources;
    }
}
