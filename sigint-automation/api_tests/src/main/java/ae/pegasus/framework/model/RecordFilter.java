package ae.pegasus.framework.model;

import java.util.List;

public class RecordFilter extends SearchFilter<Record> {

    private int minDuration;
    private List<String> processIds;
    private String processedStatus;
    private List<String> languages;
    private List<String> languageDialect;
    private List<String> tags;
    private boolean highConfidenceOnly;
    private String geoLocation;
    private String radius;
    private int minSpeechLength;
    private String mentionsAndHits;
    private String fromPhoneNumber;
    private String toPhoneNumber;

    public String getFromPhoneNumber() {
        return fromPhoneNumber;
    }

    public void setFromPhoneNumber(String fromPhoneNumber) {
        this.fromPhoneNumber = fromPhoneNumber;
    }

    public String getToPhoneNumber() {
        return toPhoneNumber;
    }

    public void setToPhoneNumber(String toPhoneNumber) {
        this.toPhoneNumber = toPhoneNumber;
    }

    public int getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }

    public List<String> getProcessIds() {
        return processIds;
    }

    public void setProcessIds(List<String> processIds) {
        this.processIds = processIds;
    }

    public String getProcessedStatus() {
        return processedStatus;
    }

    public void setProcessedStatus(String processedStatus) {
        this.processedStatus = processedStatus;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getLanguageDialect() {
        return languageDialect;
    }

    public void setLanguageDialect(List<String> languageDialect) {
        this.languageDialect = languageDialect;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isHighConfidenceOnly() {
        return highConfidenceOnly;
    }

    public void setHighConfidenceOnly(boolean highConfidenceOnly) {
        this.highConfidenceOnly = highConfidenceOnly;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public int getMinSpeechLength() {
        return minSpeechLength;
    }

    public void setMinSpeechLength(int minSpeechLength) {
        this.minSpeechLength = minSpeechLength;
    }

    public String getMentionsAndHits() {
        return mentionsAndHits;
    }

    public void setMentionsAndHits(String mentionsAndHits) {
        this.mentionsAndHits = mentionsAndHits;
    }

    @Override
    public boolean isAppliedToEntity(Record entity) {
        return false;
    }
}
