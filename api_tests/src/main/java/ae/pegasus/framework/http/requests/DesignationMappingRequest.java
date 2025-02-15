package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.DesignationMapping;
import ae.pegasus.framework.model.G4File;
import ae.pegasus.framework.model.SearchFilter;

public class DesignationMappingRequest extends HttpRequest {

  private static final String URI = "/api/admin/designation-mappings/";

  /**
   * Build HTTP request.
   * By Default GET MediaType.APPLICATION_JSON Request.
   */
  public DesignationMappingRequest() {
    super(URI);
  }

  public DesignationMappingRequest add(DesignationMapping designationMapping) {
    this
        .setURI(URI)
        .setHttpMethod(HttpMethod.POST)
        .setPayload(designationMapping);
    return this;
  }

  public DesignationMappingRequest list() {
    this
        .setURI(URI)
        .setHttpMethod(HttpMethod.GET);
    return this;
  }

  public DesignationMappingRequest view(String id) {
    this
        .setURI(URI + id)
        .setHttpMethod(HttpMethod.GET);
    return this;
  }

  public DesignationMappingRequest update(DesignationMapping designationMapping) {
    // update version field
    designationMapping.setVersion(designationMapping.getVersion() == null ? 1 : designationMapping.getVersion() + 1);

    this
        .setURI(URI + designationMapping.getId())
        .setHttpMethod(HttpMethod.PUT)
        .setPayload(designationMapping);
    return this;
  }

  public DesignationMappingRequest search(SearchFilter filter) {
    this
        .setURI(URI + "search")
        .setHttpMethod(HttpMethod.POST)
        .setPayload(filter);
    return this;
  }

  public DesignationMappingRequest delete(String id) {
    this
        .setURI(URI + id)
        .setHttpMethod(HttpMethod.DELETE);
    return this;
  }

  public DesignationMappingRequest upload(G4File file) {
    addBodyFile("file", file, file.getMediaType());
    this
        .setURI(URI + "import")
        .setHttpMethod(HttpMethod.POST);
    return this;
  }

}
