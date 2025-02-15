package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.model.Profile;

import static ae.pegasus.framework.http.HttpMethod.*;

public class ProfileDraftRequest extends HttpRequest {

    private static final String URI = "/api/profiler/profileDrafts";

    /**
     * Build HTTP request.
     * By Default: HttpMethod = GET, MediaType = APPLICATION_JSON.
     */
    public ProfileDraftRequest() {
        super(URI);
    }

    public ProfileDraftRequest create(Profile profile) {
        this
                .setURI(URI)
                .setHttpMethod(POST)
                .setPayload(profile);
        return this;
    }

    public ProfileDraftRequest delete(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(DELETE);
        return this;
    }

    public ProfileDraftRequest view(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(GET);
        return this;
    }

    public ProfileDraftRequest publish(Profile profile) {
        this
                .setURI(URI + "/" + profile.getId() + "/publish")
                .setHttpMethod(POST)
                .setPayload(profile);
        return this;
    }

    public ProfileDraftRequest list() {
        this
                .setURI(URI)
                .setHttpMethod(GET);
        return this;
    }

    public ProfileDraftRequest update(Profile profile) {
        profile.getProperties().setProfileVersion(
                profile.getProperties().getProfileVersion() == null ? 1 : profile.getProperties().getProfileVersion() + 1);
        this
                .setURI(URI + "/" + profile.getId())
                .setHttpMethod(PUT)
                .setPayload(profile);
        return this;
    }
}
