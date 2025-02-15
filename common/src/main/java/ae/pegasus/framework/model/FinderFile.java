package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import ae.pegasus.framework.data_for_entity.annotations.DataProvider;
import ae.pegasus.framework.data_for_entity.annotations.WithCollectionSize;
import ae.pegasus.framework.data_for_entity.data_providers.profiler.ProfileAndTargetGroupNameProvider;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class FinderFile extends AbstractEntity {

    @DataIgnore
    private ProfilerJsonType type = ProfilerJsonType.File;
    @DataIgnore
    private ProfilerJsonType baseType = ProfilerJsonType.File;
    @WithCollectionSize(1)
    private ArrayList<ReqPermission> reqPermissions = new ArrayList<>();
    @DataProvider(ProfileAndTargetGroupNameProvider.class)
    private String name;
    private String description;
    @DataIgnore
    private Boolean empty;
    @DataIgnore
    private Object aggregatedTypeCounts;
    @DataIgnore
    private Date creationTime;
    @DataIgnore
    private String creator;
    @DataIgnore
    private ArrayList<ParentChain> parentChain;
    @DataIgnore
    private String parentFileId;
    @DataIgnore
    private Boolean hasContents;
    @DataIgnore
    private Boolean deleted = false;
    @DataIgnore
    private FinderFile payload;
    @DataIgnore
    private List<String> files = new ArrayList<>();
    @DataIgnore
    private Integer version;
    @DataIgnore
    private List<String> fileIds = new ArrayList<>();
    @DataIgnore
    private String error;

    public ProfilerJsonType getType() {
        return type;
    }

    public void setType(ProfilerJsonType type) {
        this.type = type;
    }

    public ProfilerJsonType getBaseType() {
        return baseType;
    }

    public void setBaseType(ProfilerJsonType baseType) {
        this.baseType = baseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    public Object getAggregatedTypeCounts() {
        return aggregatedTypeCounts;
    }

    public void setAggregatedTypeCounts(Object aggregatedTypeCounts) {
        this.aggregatedTypeCounts = aggregatedTypeCounts;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public ArrayList<ParentChain> getParentChain() {
        return parentChain;
    }

    public void setParentChain(ArrayList<ParentChain> parentChain) {
        this.parentChain = parentChain;
    }

    public String getParentFileId() {
        return parentFileId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getHasContents() {
        return hasContents;
    }

    public void setHasContents(Boolean hasContents) {
        this.hasContents = hasContents;
    }

    public ArrayList<ReqPermission> getReqPermissions() {
        return reqPermissions;
    }

    public void setReqPermissions(ArrayList<ReqPermission> reqPermissions) {
        this.reqPermissions = reqPermissions;
    }

    public FinderFile getPayload() {
        return payload;
    }

    public void setPayload(FinderFile payload) {
        this.payload = payload;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public void setParentFileId(String parentFileId) {
        this.parentFileId = parentFileId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
    }

    public static class ParentChain extends AbstractEntity {

        private String name;

        public ParentChain(){
        }

        public ParentChain(String id, String name) {
            this.setId(id);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
