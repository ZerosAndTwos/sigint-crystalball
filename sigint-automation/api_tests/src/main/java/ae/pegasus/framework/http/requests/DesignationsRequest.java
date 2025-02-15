package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.Designation;
import ae.pegasus.framework.model.SearchFilter;

public class DesignationsRequest extends HttpRequest {

  private static final String URI = "/api/admin/designations/";

  /**
   * Build HTTP request.
   * By Default GET MediaType.APPLICATION_JSON Request.
   */
  public DesignationsRequest() {
    super(URI);
  }

  public DesignationsRequest add(Designation designation) {
    this
        .setURI(URI)
        .setHttpMethod(HttpMethod.POST)
        .setPayload(designation);
    return this;
  }

  public DesignationsRequest list() {
    this
        .setURI(URI)
        .setHttpMethod(HttpMethod.GET);
    return this;
  }

  public DesignationsRequest view(String id) {
    this
        .setURI(URI + id)
        .setHttpMethod(HttpMethod.GET);
    return this;
  }

  public DesignationsRequest update(Designation designation) {
    // update version field
    designation.setVersion(designation.getVersion() == null ? 1 : designation.getVersion() + 1);

    this
        .setURI(URI + designation.getId())
        .setHttpMethod(HttpMethod.PUT)
        .setPayload(designation);
    return this;
  }

  public DesignationsRequest search(SearchFilter filter) {
    this
        .setURI(URI + "search")
        .setHttpMethod(HttpMethod.POST)
        .setPayload(filter);
    return this;
  }

  public DesignationsRequest delete(String id) {
    this
        .setURI(URI + id)
        .setHttpMethod(HttpMethod.DELETE);
    return this;
  }
}
