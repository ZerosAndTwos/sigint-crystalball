package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import ae.pegasus.framework.data_for_entity.annotations.DataProvider;
import ae.pegasus.framework.data_for_entity.annotations.WithCollectionSize;
import ae.pegasus.framework.data_for_entity.data_providers.profiler.ProfileAndTargetGroupNameProvider;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProfileAndTargetGroup extends AbstractEntity {

    @DataIgnore
    private ProfilerJsonType jsonType;
    @DataProvider(ProfileAndTargetGroupNameProvider.class)
    private String name;
    @WithCollectionSize(1)
    private ArrayList<ReqPermission> reqPermissions = new ArrayList<>();

    public ProfilerJsonType getJsonType() {
        return jsonType;
    }

    public void setJsonType(ProfilerJsonType jsonType) {
        this.jsonType = jsonType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ReqPermission> getReqPermissions() {
        return reqPermissions;
    }

    public void setReqPermissions(ArrayList<ReqPermission> reqPermissions) {
        this.reqPermissions = reqPermissions;
    }
}
