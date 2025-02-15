package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import ae.pegasus.framework.data_for_entity.annotations.DataProvider;
import ae.pegasus.framework.data_for_entity.annotations.WithDataSize;
import ae.pegasus.framework.data_for_entity.data_providers.PhonesProvider;
import ae.pegasus.framework.data_for_entity.data_providers.custom.LanguageCodesProvider;
import ae.pegasus.framework.json.serialization.TargetDeserializer;
import ae.pegasus.framework.json.serialization.TargetJsonSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class Target extends G4Entity {

    private String description;
    @JsonSerialize(using = TargetJsonSerializer.class)
    @JsonDeserialize(using = TargetDeserializer.class)
    @DataIgnore
    private ArrayList<TargetGroup> groups = new ArrayList<>();
    private HashSet<String> keywords = new HashSet<>();
    @WithDataSize(5)
    @DataProvider(LanguageCodesProvider.class)
    private HashSet<String> languages = new HashSet<>();
    private String name;
    @DataProvider(PhonesProvider.class)
    private HashSet<String> phones = new HashSet<>();
    @DataIgnore
    private String originalName;
    @DataIgnore
    private Integer threatScore;
    @DataIgnore
    private Long lmt;
    @DataIgnore
    private Boolean deleted = false;
    @DataIgnore
    private Boolean active = true;
    private Integer version;

    @Override
    public String toString(){
        return String.format("groups:%s, keywords:%s, languages:%s, name:%s," +
                        "phones:%s, originalName:%s, threatScore:%s, lmt:%s, deleted:%s, active:%s, version:%s",
                Arrays.toString(groups.toArray()), Arrays.toString(keywords.toArray()),
                Arrays.toString(languages.toArray()), name, Arrays.toString(phones.toArray()), originalName, threatScore, lmt, deleted, active, version);
    }

    public Target(String id) {
        setId(id);
    }

    public Target(){}

    public Target addGroup(TargetGroup targetGroup) {
        List<TargetGroup> groups = getGroups();
        groups.add(targetGroup);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<TargetGroup> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<TargetGroup> groups) {
        this.groups = groups;
    }

    public HashSet<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(HashSet<String> keywords) {
        this.keywords = keywords;
    }

    public HashSet<String> getLanguages() {
        return languages;
    }

    public void setLanguages(HashSet<String> languages) {
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<String> getPhones() {
        return phones;
    }

    public void setPhones(HashSet<String> phones) {
        this.phones = phones;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Integer getThreatScore() {
        return threatScore;
    }

    public void setThreatScore(Integer threatScore) {
        this.threatScore = threatScore;
    }

    public Long getLmt() {
        return lmt;
    }

    public void setLmt(Long lmt) {
        this.lmt = lmt;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
