package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.FinderCase;
import org.apache.log4j.Logger;

public class FinderCaseRequest extends HttpRequest {

    private static final String URI = "/api/file-system/cases";
    private static final Logger log = Logger.getLogger(FinderCaseRequest.class);

    public FinderCaseRequest() {
        super(URI);
    }

    public FinderCaseRequest add(FinderCase finderCase) {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(finderCase);
        return this;
    }

    public FinderCaseRequest get(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public FinderCaseRequest delete(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }

    public FinderCaseRequest update(FinderCase finderCase) {
        this
                .setURI(URI + "/" + finderCase.getId())
                .setHttpMethod(HttpMethod.PUT)
                .setPayload(finderCase);
        return this;
    }

    public FinderCaseRequest getContent(String fileID) {
        this
                .setURI(URI + "/" + fileID + "/contents")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }
}
