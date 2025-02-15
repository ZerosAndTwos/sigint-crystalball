package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import ae.pegasus.framework.data_for_entity.annotations.DataProvider;
import ae.pegasus.framework.data_for_entity.annotations.WithCollectionSize;
import ae.pegasus.framework.data_for_entity.data_providers.profiler.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Profile extends ProfileAndTargetGroup {

    @DataIgnore
    private String type = "Individual";
    private ProfileProperties properties;
    @DataIgnore
    private ArrayList<SearchRecord> entities = new ArrayList<>();
    @DataIgnore
    private ArrayList<TargetGroup> groups = new ArrayList<>();
    @DataIgnore
    private ArrayList<FinderFile> files = new ArrayList<>();
    @WithCollectionSize(1)
    @DataProvider(TeamsIdProvider.class)
    private HashSet<String> assignedTeams = new HashSet<>();
    @DataIgnore
    private Boolean target;
    @DataProvider(ProfileCategoryProvider.class)
    private String category;
    @DataIgnore
    private ProfileConsolidatedAttributes consolidatedAttributes;
    @DataProvider(TargetStatusProvider.class)
    @DataIgnore
    private TargetStatus active = TargetStatus.ACTIVE;
    @DataIgnore
    private Date activeUntil;
    @DataIgnore
    private Integer identifierCount = 0;
    @DataIgnore
    private Profile parent;
    @DataIgnore
    private ArrayList<String> mergingProfilesIDs = new ArrayList<>();
    @DataProvider(CriminalRecordProvider.class)
    private CriminalRecord criminalRecord;
    @DataProvider(ClassificationProvider.class)
    private String classification; // "C";
    @DataIgnore
    @WithCollectionSize(4)
    private ArrayList<Identifier> identifiers = new ArrayList<>();
    @DataIgnore
    private ArrayList<IdentifierSummary> identifiersSummary = new ArrayList<>();
    @DataIgnore
    private String uploadedImage;
    @DataIgnore
    private ArrayList<VoiceFile> voiceFiles = new ArrayList<>();
    @DataIgnore
    private String voiceModelId;
    @DataIgnore
    private List<Object> attachments = new ArrayList<>();
    @DataIgnore
    private Date historicalQueryTime;
    @DataIgnore
    private Object lastLocation;
    @DataIgnore
    private List<String> rfis = new ArrayList<>();
    @DataIgnore
    private Integer threatImpact = 20;
    @DataIgnore
    private Integer threatLikelihood = 20;

    public Profile() {
        setJsonType(ProfilerJsonType.Profile);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ProfileProperties getProperties() {
        return properties;
    }

    public void setProperties(ProfileProperties properties) {
        this.properties = properties;
    }

    public ArrayList<SearchRecord> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<SearchRecord> entities) {
        this.entities = entities;
    }

    public ArrayList<TargetGroup> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<TargetGroup> groups) {
        this.groups = groups;
    }

    public Boolean getTarget() {
        return target;
    }

    public void setTarget(Boolean target) {
        this.target = target;
    }

    public ProfileConsolidatedAttributes getConsolidatedAttributes() {
        return consolidatedAttributes;
    }

    public void setConsolidatedAttributes(ProfileConsolidatedAttributes consolidatedAttributes) {
        this.consolidatedAttributes = consolidatedAttributes;
    }

    public TargetStatus getActive() {
        return active;
    }

    public void setActive(TargetStatus active) {
        this.active = active;
    }

    public Date getActiveUntil() {
        return activeUntil;
    }

    public void setActiveUntil(Date activeUntil) {
        this.activeUntil = activeUntil;
    }

    public Integer getIdentifierCount() {
        return identifierCount;
    }

    public void setIdentifierCount(Integer identifierCount) {
        this.identifierCount = identifierCount;
    }

    public Profile getParent() {
        return parent;
    }

    public void setParent(Profile parent) {
        this.parent = parent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getMergingProfilesIDs() {
        return mergingProfilesIDs;
    }

    public void setMergingProfilesIDs(ArrayList<String> mergingProfilesIDs) {
        this.mergingProfilesIDs = mergingProfilesIDs;
    }

    public CriminalRecord getCriminalRecord() {
        return criminalRecord;
    }

    public void setCriminalRecord(CriminalRecord criminalRecord) {
        this.criminalRecord = criminalRecord;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public ArrayList<Identifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(ArrayList<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    public ArrayList<IdentifierSummary> getIdentifiersSummary() {
        return identifiersSummary;
    }

    public void setIdentifiersSummary(ArrayList<IdentifierSummary> identifiersSummary) {
        this.identifiersSummary = identifiersSummary;
    }

    public String getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(String uploadedImage) {
        this.uploadedImage = uploadedImage;
    }

    public ArrayList<VoiceFile> getVoiceFiles() {
        return voiceFiles;
    }

    public void setVoiceFiles(ArrayList<VoiceFile> voiceFiles) {
        this.voiceFiles = voiceFiles;
    }

    public String getVoiceModelId() {
        return voiceModelId;
    }

    public void setVoiceModelId(String voiceModelId) {
        this.voiceModelId = voiceModelId;
    }

    public List<Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }

    public Date getHistoricalQueryTime() {
        return historicalQueryTime;
    }

    public void setHistoricalQueryTime(Date historicalQueryTime) {
        this.historicalQueryTime = historicalQueryTime;
    }

    public Object getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Object lastLocation) {
        this.lastLocation = lastLocation;
    }

    public List<String> getRfis() {
        return rfis;
    }

    public void setRfis(List<String> rfis) {
        this.rfis = rfis;
    }

    public ArrayList<FinderFile> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<FinderFile> files) {
        this.files = files;
    }

    public Integer getThreatImpact() {
        return threatImpact;
    }

    public void setThreatImpact(Integer threatImpact) {
        this.threatImpact = threatImpact;
    }

    public Integer getThreatLikelihood() {
        return threatLikelihood;
    }

    public void setThreatLikelihood(Integer threatLikelihood) {
        this.threatLikelihood = threatLikelihood;
    }

    public HashSet<String> getAssignedTeams() {
        return assignedTeams;
    }

    public void setAssignedTeams(HashSet<String> assignedTeams) {
        this.assignedTeams = assignedTeams;
    }

    public static class FinderFile extends AbstractEntity {

        public FinderFile() {}

        public FinderFile(String id, String name) {
            setId(id);
            setName(name);
        }

        private String name;

        public String getName() {
            return name;
        }

        public FinderFile setName(String name) {
            this.name = name;
            return this;
        }
    }
}
