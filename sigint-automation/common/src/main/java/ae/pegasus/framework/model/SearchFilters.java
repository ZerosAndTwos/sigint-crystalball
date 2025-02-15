package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchFilters {

    @JsonProperty("workflow.teamIds")
    private List<String> teamIds;
    @JsonProperty("workflow.ownerId")
    private List<String> ownerId;
    @JsonProperty("workflow.tags")
    private List<String> tags;
    @JsonProperty("workflow.priority")
    private Integer priority;
    @JsonProperty("workflow.recordStatus")
    private String recordStatus; // 'UNPROCESSED"
    @JsonProperty("workflow.includeUnassignedRecords")
    private Boolean includeUnassignedRecords;
    @JsonProperty("workflow.designations")
    private List<String> designations;
    private AdvancedSearch advancedSearch;
    private List<String> publisherName;
    private List<DataSourceType> dataSource; // ["TWITTER"]
    private List<String> subSourceId;
    private List<String> subSource; // ["SMS"]
    private List<String> type; // ["TWEET"]
    private String eventFeed; // "OSINT"
    private SearchObjectType objectType; // "event"
    private String personName;
    private String personPassportNumber;
    private String travellerReference;
    private Boolean hasLocation;
    private EventTime eventTime;
    private String targetMatchType;
    private List<String> target;
    private List<TargetGroup> targetGroups;
    private List<String> targetGroup;
    private List<String> groupIds;
    private Boolean includeChildGroups;
    private List<String> profileCountry;
    private List<String> language;
    private FavoriteCount favoriteCount;
    private String youtubeChannelName;
    private List<String> youtubeCategory;
    private String youtubeChannelId;
    private List<String> tweetShowing;
    private String twitterAccountId;
    private String twitterUsername;
    private String twitterMentionHandle;
    private List<String> tweetCountry;
    private RetweetCount retweetCount;
    private String insUsername;
    private String insUserId;
    private List<String> insMediaType;
    private String passengerSeqNumber;
    private String flightBookingNumber;
    private Boolean includeSpam;
    private DurationRange duration;
    private SpeechDurationRange speechDuration;
    private String speechQualityForAudioKeywordMatching;
    private String speechQualityForVoiceIdMatching;
    private List<String> senderCountry;
    private Location location;
    private String tumblrUsername;

    public List<DataSourceType> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<DataSourceType> dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getEventFeed() {
        return eventFeed;
    }

    public void setEventFeed(String eventFeed) {
        this.eventFeed = eventFeed;
    }

    public SearchObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(SearchObjectType objectType) {
        this.objectType = objectType;
    }

    public List<String> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<String> teamIds) {
        this.teamIds = teamIds;
    }

    public List<String> getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(List<String> ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public AdvancedSearch getAdvancedSearch() {
        return advancedSearch;
    }

    public void setAdvancedSearch(AdvancedSearch advancedSearch) {
        this.advancedSearch = advancedSearch;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Boolean getIncludeUnassignedRecords() {
        return includeUnassignedRecords;
    }

    public void setIncludeUnassignedRecords(Boolean includeUnassignedRecords) {
        this.includeUnassignedRecords = includeUnassignedRecords;
    }

    public Boolean getHasLocation() {
        return hasLocation;
    }

    public void setHasLocation(Boolean hasLocation) {
        this.hasLocation = hasLocation;
    }


    public EventTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(EventTime eventTime) {
        this.eventTime = eventTime;
    }

    public List<String> getProfileCountry() {
        return profileCountry;
    }

    public void setProfileCountry(List<String> profileCountry) {
        this.profileCountry = profileCountry;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public List<String> getTweetShowing() {
        return tweetShowing;
    }

    public void setTweetShowing(List<String> tweetShowing) {
        this.tweetShowing = tweetShowing;
    }

    public FavoriteCount getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(FavoriteCount favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public List<String> getYoutubeCategory() {
        return youtubeCategory;
    }

    public void setYoutubeCategory(List<String> youtubeCategory) {
        this.youtubeCategory = youtubeCategory;
    }

    public String getTwitterAccountId() {
        return twitterAccountId;
    }

    public void setTwitterAccountId(String twitterAccountId) {
        this.twitterAccountId = twitterAccountId;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public String getYoutubeChannelName() {
        return youtubeChannelName;
    }

    public void setYoutubeChannelName(String youtubeChannelName) {
        this.youtubeChannelName = youtubeChannelName;
    }

    public String getYoutubeChannelId() {
        return youtubeChannelId;
    }

    public void setYoutubeChannelId(String youtubeChannelId) {
        this.youtubeChannelId = youtubeChannelId;
    }

    public String getInsUsername() {
        return insUsername;
    }

    public void setInsUsername(String insUsername) {
        this.insUsername = insUsername;
    }

    public String getInsUserId() {
        return insUserId;
    }

    public void setInsUserId(String insUserId) {
        this.insUserId = insUserId;
    }

    public Boolean getIncludeSpam() {
        return includeSpam;
    }

    public void setIncludeSpam(Boolean includeSpam) {
        this.includeSpam = includeSpam;
    }

    public List<String> getTarget() {
        return target;
    }

    public void setTarget(List<String> target) {
        this.target = target;
    }

    public String getTargetMatchType() {
        return targetMatchType;
    }

    public void setTargetMatchType(String targetMatchType) {
        this.targetMatchType = targetMatchType;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

    public Boolean getIncludeChildGroups() {
        return includeChildGroups;
    }

    public void setIncludeChildGroups(Boolean includeChildGroups) {
        this.includeChildGroups = includeChildGroups;
    }

    public List<String> getDesignations() {
        return designations;
    }

    public void setDesignations(List<String> designations) {
        this.designations = designations;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTravellerReference() {
        return travellerReference;
    }

    public void setTravellerReference(String travellerReference) {
        this.travellerReference = travellerReference;
    }

    public String getPersonPassportNumber() {
        return personPassportNumber;
    }

    public void setPersonPassportNumber(String personPassportNumber) {
        this.personPassportNumber = personPassportNumber;
    }

    public List<String> getTweetCountry() {
        return tweetCountry;
    }

    public void setTweetCountry(List<String> tweetCountry) {
        this.tweetCountry = tweetCountry;
    }

    public String getPassengerSeqNumber() {
        return passengerSeqNumber;
    }

    public void setPassengerSeqNumber(String passengerSeqNumber) {
        this.passengerSeqNumber = passengerSeqNumber;
    }

    public List<String> getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(List<String> publisherName) {
        this.publisherName = publisherName;
    }

    public String getFlightBookingNumber() {
        return flightBookingNumber;
    }

    public void setFlightBookingNumber(String flightBookingNumber) {
        this.flightBookingNumber = flightBookingNumber;
    }

    public List<String> getSubSourceId() {
        return subSourceId;
    }

    public void setSubSourceId(List<String> subSourceId) {
        this.subSourceId = subSourceId;
    }

    public DurationRange getDuration() {
        return duration;
    }

    public void setDuration(DurationRange duration) {
        this.duration = duration;
    }

    public SpeechDurationRange getSpeechDuration() {
        return speechDuration;
    }

    public void setSpeechDuration(SpeechDurationRange speechDuration) {
        this.speechDuration = speechDuration;
    }

    public String getSpeechQualityForAudioKeywordMatching() {
        return speechQualityForAudioKeywordMatching;
    }

    public void setSpeechQualityForAudioKeywordMatching(String speechQualityForAudioKeywordMatching) {
        this.speechQualityForAudioKeywordMatching = speechQualityForAudioKeywordMatching;
    }

    public String getSpeechQualityForVoiceIdMatching() {
        return speechQualityForVoiceIdMatching;
    }

    public void setSpeechQualityForVoiceIdMatching(String speechQualityForVoiceIdMatching) {
        this.speechQualityForVoiceIdMatching = speechQualityForVoiceIdMatching;
    }

    public List<TargetGroup> getTargetGroups() {
        return targetGroups;
    }

    public void setTargetGroups(List<TargetGroup> targetGroups) {
        this.targetGroups = targetGroups;
    }

    public List<String> getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(List<String> targetGroup) {
        this.targetGroup = targetGroup;
    }

    public List<String> getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(List<String> senderCountry) {
        this.senderCountry = senderCountry;
    }

    public List<String> getSubSource() {
        return subSource;
    }

    public void setSubSource(List<String> subSource) {
        this.subSource = subSource;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public RetweetCount getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(RetweetCount retweetCount) {
        this.retweetCount = retweetCount;
    }

    public String getTwitterMentionHandle() {
        return twitterMentionHandle;
    }

    public void setTwitterMentionHandle(String twitterMentionHandle) {
        this.twitterMentionHandle = twitterMentionHandle;
    }

    public String getTumblrUsername() {
        return tumblrUsername;
    }

    public void setTumblrUsername(String tumblrUsername) {
        this.tumblrUsername = tumblrUsername;
    }

    public List<String> getInsMediaType() {
        return insMediaType;
    }

    public void setInsMediaType(List<String> insMediaType) {
        this.insMediaType = insMediaType;
    }

    public static class TargetGroup extends AbstractEntity {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class FavoriteCount {
        private Integer from;

        public Integer getFrom() {
            return from;
        }

        public void setFrom(Integer from) {
            this.from = from;
        }
    }

    public class EventTime {
        private Date from;
        private Date to;

        public Date getFrom() {
            return from;
        }

        public void setFrom(Date from) {
            this.from = from;
        }

        public Date getTo() {
            return to;
        }

        public void setTo(Date to) {
            this.to = to;
        }
    }

    public class DurationRange {
        private Integer from;
        private Integer to;

        public Integer getFrom() {
            return from;
        }

        public void setFrom(Integer from) {
            this.from = from;
        }

        public Integer getTo() {
            return to;
        }

        public void setTo(Integer to) {
            this.to = to;
        }
    }

    public class SpeechDurationRange {
        private Integer from;
        private Integer to;

        public Integer getFrom() {
            return from;
        }

        public void setFrom(Integer from) {
            this.from = from;
        }

        public Integer getTo() {
            return to;
        }

        public void setTo(Integer to) {
            this.to = to;
        }
    }

    public class RetweetCount {
        private Integer from;

        public Integer getFrom() {
            return from;
        }

        public void setFrom(Integer from) {
            this.from = from;
        }
    }
}
