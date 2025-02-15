package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.UserPassword;

public class ChangePasswordRequest extends HttpRequest {

  private static final String PASSWORDS_URI = "/api/auth/passwords";
  private static final String USER_URI = "/api/auth/users/";

  public ChangePasswordRequest() {
    super(PASSWORDS_URI);
  }

  public ChangePasswordRequest updateTempPassword(UserPassword entity) {
    this
        .setURI(PASSWORDS_URI)
        .setHttpMethod(HttpMethod.POST)
        .setPayload(entity);
    return this;
  }

  public ChangePasswordRequest updatePassword(UserPassword entity, String userId) {
    this
        .setURI(USER_URI + userId)
        .setHttpMethod(HttpMethod.PUT)
        .setPayload(entity);
    return this;
  }
}
