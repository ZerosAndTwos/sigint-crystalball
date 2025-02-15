package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.User;

public class UserRequest extends HttpRequest {

    private static final String URI = "/api/auth/users";

    public UserRequest() {
        super(URI);
    }

    public UserRequest me() {
        this
                .setURI(URI + "/me")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public UserRequest add(User user) {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(user);
        return this;
    }

    public UserRequest update(User user) {
        this
                .setURI(URI + "/" + user.getId())
                .setHttpMethod(HttpMethod.PUT)
                .setPayload(user);
        return this;
    }

    public UserRequest delete(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }

    public UserRequest search(SearchFilter filter) {
        this
                .setURI(URI + "search")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(filter);
        return this;
    }

}
