package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Process extends G4Entity {

    private Integer version;
    private String ownerId;
    private State state;
    private String sourceId;
    private String md5;
    private List<String> indexes;
    private Date lmt;
    private MatchingContext matchingContext;
    private Integer recordsCount;
    private Integer voiceCount;
    private Integer smsCount;
    private Long workload;
    private Date earlistRecordTime;
    private Date latestRecordTime;
    private Integer targetHitCount;
    private Integer targetMentionCount;
    private Boolean ingestMatchingComplete;

    public Integer getVoiceCount() {
        return voiceCount;
    }

    public void setVoiceCount(Integer voiceCount) {
        this.voiceCount = voiceCount;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public List<String> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<String> indexes) {
        this.indexes = indexes;
    }

    public Date getLmt() {
        return lmt;
    }

    public void setLmt(Date lmt) {
        this.lmt = lmt;
    }

    public MatchingContext getMatchingContext() {
        return matchingContext;
    }

    public void setMatchingContext(MatchingContext matchingContext) {
        this.matchingContext = matchingContext;
    }

    public Integer getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(Integer recordsCount) {
        this.recordsCount = recordsCount;
    }

    public Integer getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(Integer smsCount) {
        this.smsCount = smsCount;
    }

    public Long getWorkload() {
        return workload;
    }

    public void setWorkload(Long workload) {
        this.workload = workload;
    }

    public Date getEarlistRecordTime() {
        return earlistRecordTime;
    }

    public void setEarlistRecordTime(Date earlistRecordTime) {
        this.earlistRecordTime = earlistRecordTime;
    }

    public Date getLatestRecordTime() {
        return latestRecordTime;
    }

    public void setLatestRecordTime(Date latestRecordTime) {
        this.latestRecordTime = latestRecordTime;
    }

    public Integer getTargetHitCount() {
        return targetHitCount;
    }

    public void setTargetHitCount(Integer targetHitCount) {
        this.targetHitCount = targetHitCount;
    }

    public Integer getTargetMentionCount() {
        return targetMentionCount;
    }

    public void setTargetMentionCount(Integer targetMentionCount) {
        this.targetMentionCount = targetMentionCount;
    }

    public Boolean isIngestMatchingComplete() {
        return ingestMatchingComplete;
    }

    public void setIngestMatchingComplete(Boolean ingestMatchingComplete) {
        this.ingestMatchingComplete = ingestMatchingComplete;
    }
}
