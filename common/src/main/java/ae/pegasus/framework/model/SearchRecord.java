package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SearchRecord extends SearchEntity {

    private Date modifiedOn;
    private Date eventTime;
    private Date endTime;
    private Map<String, Object> identifiers = new LinkedHashMap<>();
    private Map<String, Object> attributes = new LinkedHashMap<>();
    private Object events;
    private CBEntities entities;
    private CBEntityAssignments assignments;
    private List<Geo> geo;
    private SearchObjectType objectType;
    private Reports reports = new Reports();

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Object getEvents() {
        return events;
    }

    public void setEvents(Object events) {
        this.events = events;
    }

    public CBEntities getEntities() {
        return entities;
    }

    public void setEntities(CBEntities entities) {
        this.entities = entities;
    }

    public CBEntityAssignments getAssignments() {
        return assignments;
    }

    public void setAssignments(CBEntityAssignments assignments) {
        this.assignments = assignments;
    }

    public List<Geo> getGeo() {
        return geo;
    }

    public void setGeo(List<Geo> geo) {
        this.geo = geo;
    }

    public SearchObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(SearchObjectType objectType) {
        this.objectType = objectType;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Reports getReports() {
        return reports;
    }

    public void setReports(Reports reports) {
        this.reports = reports;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Map<String, Object> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(Map<String, Object> identifiers) {
        this.identifiers = identifiers;
    }

    public class Reports {
        private List<String> reportIds = new ArrayList<>();

        public List<String> getReportIds() {
            return reportIds;
        }

        public void setReportIds(List<String> reportIds) {
            this.reportIds = reportIds;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public class Attributes {
        @JsonProperty("CHANNEL_ID")
        private List<String> channelId; // ["1", "1", "0", "1", "0", "0"]
        private List<String> CREATE_TIME; // ["1516459620719#1516459620719"]
        private List<String> DIALECT_NAME; // ["american"]
        private List<String> HAS_VPRINT; // ["true", "true"]
        private List<String> IS_MANUAL; // ["true"]
        private List<String> LANGUAGE_CODE2; //["en"]
        private List<String> PERCENT_SCORE; // ["0.625013530254364"]
        private List<String> PHONEME_TEXT;
        private List<String> UPLOAD_WAV_FILE_ID;
        private String VOICE_CONTENT_URL;
        private List<String> VOICE_SPEECH_LENGTH;

        public List<String> getChannelId() {
            return channelId;
        }

        public void setChannelId(List<String> channelId) {
            this.channelId = channelId;
        }

        public List<String> getCREATE_TIME() {
            return CREATE_TIME;
        }

        public void setCREATE_TIME(List<String> CREATE_TIME) {
            this.CREATE_TIME = CREATE_TIME;
        }

        public List<String> getDIALECT_NAME() {
            return DIALECT_NAME;
        }

        public void setDIALECT_NAME(List<String> DIALECT_NAME) {
            this.DIALECT_NAME = DIALECT_NAME;
        }

        public List<String> getHAS_VPRINT() {
            return HAS_VPRINT;
        }

        public void setHAS_VPRINT(List<String> HAS_VPRINT) {
            this.HAS_VPRINT = HAS_VPRINT;
        }

        public List<String> getIS_MANUAL() {
            return IS_MANUAL;
        }

        public void setIS_MANUAL(List<String> IS_MANUAL) {
            this.IS_MANUAL = IS_MANUAL;
        }

        public List<String> getLANGUAGE_CODE2() {
            return LANGUAGE_CODE2;
        }

        public void setLANGUAGE_CODE2(List<String> LANGUAGE_CODE2) {
            this.LANGUAGE_CODE2 = LANGUAGE_CODE2;
        }

        public List<String> getPERCENT_SCORE() {
            return PERCENT_SCORE;
        }

        public void setPERCENT_SCORE(List<String> PERCENT_SCORE) {
            this.PERCENT_SCORE = PERCENT_SCORE;
        }

        public List<String> getPHONEME_TEXT() {
            return PHONEME_TEXT;
        }

        public void setPHONEME_TEXT(List<String> PHONEME_TEXT) {
            this.PHONEME_TEXT = PHONEME_TEXT;
        }

        public List<String> getUPLOAD_WAV_FILE_ID() {
            return UPLOAD_WAV_FILE_ID;
        }

        public void setUPLOAD_WAV_FILE_ID(List<String> UPLOAD_WAV_FILE_ID) {
            this.UPLOAD_WAV_FILE_ID = UPLOAD_WAV_FILE_ID;
        }

        public String getVOICE_CONTENT_URL() {
            return VOICE_CONTENT_URL;
        }

        public void setVOICE_CONTENT_URL(String VOICE_CONTENT_URL) {
            this.VOICE_CONTENT_URL = VOICE_CONTENT_URL;
        }

        public List<String> getVOICE_SPEECH_LENGTH() {
            return VOICE_SPEECH_LENGTH;
        }

        public void setVOICE_SPEECH_LENGTH(List<String> VOICE_SPEECH_LENGTH) {
            this.VOICE_SPEECH_LENGTH = VOICE_SPEECH_LENGTH;
        }
    }
}