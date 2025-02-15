package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.G4File;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.Target;

import javax.ws.rs.core.MediaType;

public class TargetRequest extends HttpRequest {

    private final static String URI = "/api/profiler/targets";

    public TargetRequest() {
        super(URI);
    }

    public TargetRequest add(Target target) {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.PUT)
                .setPayload(target);
        return this;
    }

    public TargetRequest get(String id) {
        this
                .setURI(URI + "/" + id + "/details")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public TargetRequest update(Target target) {
        this
                .setHttpMethod(HttpMethod.POST)
                .setPayload(target);
        return this;
    }

    public TargetRequest delete(String id) {
        this
                .setURI(URI + "/" + id )
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }

    public TargetRequest upload(G4File file) {
        addBodyFile("file", file, MediaType.APPLICATION_JSON_TYPE);
        file.deleteOnExit();
        this
                .setURI(URI + "/upload")
                .setHttpMethod(HttpMethod.POST);
        return this;
    }

    public TargetRequest search(SearchFilter filter) {
        this
                .setURI(URI + "/search")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(filter);
        return this;
    }

    public TargetRequest findTargetGroups(String id) {
        this
                .setURI(URI + "/" + id + "/groups")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public TargetRequest list() {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

}
