package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.*;
import ae.pegasus.framework.data_for_entity.data_providers.country_info.CountryProvider;
import ae.pegasus.framework.data_for_entity.data_providers.custom.LanguageProvider;
import ae.pegasus.framework.data_for_entity.data_providers.record.RecordBodySMSProvider;
import ae.pegasus.framework.data_for_entity.data_providers.record.RecordBodyVoiceProvider;
import ae.pegasus.framework.data_for_entity.data_providers.record.RecordTypeProvider;
import ae.pegasus.framework.data_for_entity.data_types.FieldDataType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class Record extends G4Entity {
    
    @DataIgnore
    private String sourceId;
    @DataIgnore
    private String sourceName;
    @DataIgnore
    private String sourceType;
    @DataIgnore
    private String sourceLetterCode;
    @DataIgnore
    private String sourceLocation;
    @DataIgnore
    private String typeEnglishName;
    @DataIgnore
    private String typeArabicName;
    @WithDataSize(12)
    @WithFieldDataType(FieldDataType.NUMERIC)
    private String fromNumber;
    @DataIgnore
    private List<String> fromNumberNames;
    @WithDataSize(12)
    @WithFieldDataType(FieldDataType.NUMERIC)
    private String toNumber;
    @DataIgnore
    private List<String> toNumberNames;
    @DataProvider(CountryProvider.class)
    private String fromCountry;
    @DataProvider(CountryProvider.class)
    private String toCountry;
    @DataProvider(LanguageProvider.class)
    private String language;
    @WithDataSize(15)
    private String tmsi;
    @WithDataSize(15)
    private String imsi;
    private String originalId;
    @DataIgnore
    private Date dateAndTime;
    @WithFieldDataType(FieldDataType.DATE)
    private Date time;
    
    @DataProvider(RecordTypeProvider.class)
    private String type;
    @WithDataDependencies(provider = RecordBodySMSProvider.class, fields = {"type"})
    private String text;
    @WithDataDependencies(provider = RecordBodyVoiceProvider.class, fields = {"type"})
    private Integer duration;
    private boolean manualEntry = true;
    private int priority=0;
    private String state = "READY";
    @DataIgnore
    private String processedStatus;
    @DataIgnore
    private int optimizedDuration;
    @DataIgnore
    private String fileId;
    @DataIgnore
    private String optimizedFileId;

    private ArrayList<String> targetMentionIds;
    private ArrayList<String> targetHitIds;
    

    public String getProcessedStatus() {
        return processedStatus;
    }

    public void setProcessedStatus(String processedStatus) {
        this.processedStatus = processedStatus;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTmsi() {
        return tmsi;
    }

    public void setTmsi(String tmsi) {
        this.tmsi = tmsi;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getToNumber() {
        return toNumber;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    public String getToCountry() {
        return toCountry;
    }

    public void setToCountry(String toCountry) {
        this.toCountry = toCountry;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isManualEntry() {
        return manualEntry;
    }

    public void setManualEntry(boolean manualEntry) {
        this.manualEntry = manualEntry;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceLetterCode() {
        return sourceLetterCode;
    }

    public void setSourceLetterCode(String sourceLetterCode) {
        this.sourceLetterCode = sourceLetterCode;
    }

    public String getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public String getTypeEnglishName() {
        return typeEnglishName;
    }

    public void setTypeEnglishName(String typeEnglishName) {
        this.typeEnglishName = typeEnglishName;
    }

    public String getTypeArabicName() {
        return typeArabicName;
    }

    public void setTypeArabicName(String typeArabicName) {
        this.typeArabicName = typeArabicName;
    }

    public List<String> getFromNumberNames() {
        return fromNumberNames;
    }

    public void setFromNumberNames(List<String> fromNumberNames) {
        this.fromNumberNames = fromNumberNames;
    }

    public List<String> getToNumberNames() {
        return toNumberNames;
    }

    public void setToNumberNames(List<String> toNumberNames) {
        this.toNumberNames = toNumberNames;
        
    }

    public int getOptimizedDuration() {
        return optimizedDuration;
    }

    public void setOptimizedDuration(int optimizedDuration) {
        this.optimizedDuration = optimizedDuration;
    }

    public String getFileId() {
        return fileId;
    }

    public void  setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getOptimizedFileId() {
        return optimizedFileId;
    }

    public void setOptimizedFileId(String optimizedFileId) {
        this.optimizedFileId = optimizedFileId;
    }

    public ArrayList<String> getTargetMentionIds() {
        return targetMentionIds;
    }

    public void setTargetMentionIds(ArrayList<String> targetMentionIds) {
        this.targetMentionIds = targetMentionIds;
    }

    public ArrayList<String> getTargetHitIds() {
        return targetHitIds;
    }

    public void setTargetHitIds(ArrayList<String> targetHitIds) {
        this.targetHitIds = targetHitIds;
    }
}
