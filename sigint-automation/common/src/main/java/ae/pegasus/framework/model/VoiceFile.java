package ae.pegasus.framework.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class VoiceFile extends AbstractEntity {

    private ProfilerJsonType jsonType = ProfilerJsonType.VoiceFile;
    private String voiceEventId;

    public ProfilerJsonType getJsonType() {
        return jsonType;
    }

    public void setJsonType(ProfilerJsonType jsonType) {
        this.jsonType = jsonType;
    }

    public String getVoiceEventId() {
        return voiceEventId;
    }

    public void setVoiceEventId(String voiceEventId) {
        this.voiceEventId = voiceEventId;
    }
}
