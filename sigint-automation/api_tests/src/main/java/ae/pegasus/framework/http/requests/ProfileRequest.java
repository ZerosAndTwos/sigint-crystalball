package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.Profile;
import ae.pegasus.framework.model.Voice;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.ArrayList;

import static ae.pegasus.framework.http.HttpMethod.*;

public class ProfileRequest extends HttpRequest {

    private static final String URI = "/api/profiler/profiles";

    /**
     * Build HTTP request.
     * By Default: HttpMethod = GET, MediaType = APPLICATION_JSON.
     */
    public ProfileRequest() {
        super(URI);
    }

    /**
     * GET /profiles/{profileId} getProfile
     *
     * @param id ID of profile
     * @return {@link ProfileRequest}
     */
    public ProfileRequest view(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    /**
     * DELETE /profiles/{profileId} deleteProfile
     *
     * @param id ID of profile
     * @return {@link ProfileRequest}
     */
    public ProfileRequest delete(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }


    /**
     * POST /profiles/merge merge
     *
     * @param firstProfileId  first Profile ID
     * @param secondProfileId second Profile ID
     * @return {@link ProfileRequest}
     */
    public ProfileRequest merge(String firstProfileId, String secondProfileId) {

        ArrayList<String> mergeIDs = new ArrayList<>();
        mergeIDs.add(firstProfileId);
        mergeIDs.add(secondProfileId);

        this
                .setURI(URI + "/merge")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(mergeIDs);
        return this;
    }

    public ProfileRequest identifiersSummary(String profileID) {
        this
                .setURI(URI + "/" + profileID + "/identifierAggregations")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public ProfileRequest summary(String profileID) {
        this
                .setURI(URI + "/" + profileID + "/summary")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public ProfileRequest getOrCreateDraft(String profileID) {
        Profile profile = new Profile();
        profile.setId(profileID);
        this
                .setURI(URI + "/" + profileID + "/draft")
                .setHttpMethod(HttpMethod.POST);
        return this;
    }

    public ProfileRequest create(Profile profile) {
        this
                .setURI(URI)
                .setHttpMethod(POST)
                .setPayload(profile);
        return this;
    }

    public ProfileRequest imageUpload(String profileID, File file) {
        addBodyFile("file", file, new MediaType("image", "jpeg"));
        this
                .setURI(URI + "/" + profileID + "/image/upload")
                .setHttpMethod(POST);
        return this;
    }

    public ProfileRequest deleteImage(String profileID) {
        this
                .setURI(URI + "/" + profileID + "/image/delete")
                .setHttpMethod(DELETE);
        return this;
    }

    public ProfileRequest voiceEvents(String profileID) {
        this
                .setURI(URI + "/" + profileID + "/voice/events")
                .setHttpMethod(GET);
        return this;
    }

    public ProfileRequest voice(String profileID) {
        this
                .setURI(URI + "/" + profileID + "/voice")
                .setHttpMethod(GET);
        return this;
    }

    public ProfileRequest createVoiceID(String profileID, Voice voice) {
        this
                .setURI(URI + "/" + profileID + "/voice")
                .setHttpMethod(POST)
                .setPayload(voice);
        return this;
    }

    public ProfileRequest removeVoiceID(Profile profile) {
        this
                .setURI(URI + "/" + profile.getId() + "/voice")
                .setHttpMethod(DELETE);
        return this;
    }

    public ProfileRequest uploadAudioFile(String profileID, File file) {
        addBodyFile("file", file, new MediaType("audio", "x-m4a"));
        this
                .setURI(URI + "/" + profileID + "/voice/upload")
                .setHttpMethod(POST);
        return this;
    }

    public ProfileRequest update(Profile profile) {
        this
                .setURI(URI + "/" + profile.getId())
                .setHttpMethod(PUT)
                .setPayload(profile);
        return this;
    }
}
