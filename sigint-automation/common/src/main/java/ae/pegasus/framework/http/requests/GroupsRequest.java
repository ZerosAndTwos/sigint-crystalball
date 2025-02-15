package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.Group;
import ae.pegasus.framework.model.PegasusMediaType;

public class GroupsRequest extends HttpRequest {

    private final static String URI = "/api/auth/groups";

    public GroupsRequest() {
        super(URI);
        this.setMediaType(PegasusMediaType.PEGASUS_JSON);
    }

    public GroupsRequest update(Group group) {
        this
                .setURI(URI + "/" + group.getId())
                .setHttpMethod(HttpMethod.PUT)
                .setPayload(group);
        return this;
    }

    public GroupsRequest add(Group group) {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(group);
        return this;
    }

    public GroupsRequest delete(String groupId) {
        this
                .setURI(URI + "/" + groupId)
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }

    public GroupsRequest list() {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

}
