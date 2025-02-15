package http.requests;

import http.HttpMethod;
import model.UserPassword;

/**
 * Created by ayak on 4/14/17.
 */
public class PasswordsRequest extends HttpRequest {

  private static final String URI = "/api/auth/passwords";

  public PasswordsRequest() {
    super(URI);
  }

  public PasswordsRequest create(UserPassword entity) {
    this
        .setHttpMethod(HttpMethod.POST)
        .setPayload(entity);
    return this;
  }
}
