package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static ae.pegasus.framework.utils.StringUtils.capitalizeFirstLetter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserPassword {

  private String name;
  private String oldPassword;
  private String newPassword;
  private String adminPassword;

  private UserPassword(UserPasswordBuilder builder) {
    this.name = builder.name;
    this.oldPassword = builder.oldPassword;
    this.newPassword = builder.newPassword;
    this.adminPassword = builder.adminPassword;
  }

  public String getName() {
    return name;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public String getAdminPassword() {
    return adminPassword;
  }

  public static class UserPasswordBuilder {
    private String name;
    private String oldPassword;
    private String newPassword;
    private String adminPassword;

    public UserPasswordBuilder name(String name) {
      this.name = capitalizeFirstLetter(name);
      return this;
    }

    public UserPasswordBuilder oldPassword(String oldPassword) {
      this.oldPassword = oldPassword;
      return this;
    }

    public UserPasswordBuilder newPassword(String newPassword) {
      this.newPassword = newPassword;
      return this;
    }

    public UserPasswordBuilder adminPassword(String adminPassword) {
      this.adminPassword = adminPassword;
      return this;
    }

    public UserPassword build() {
      return new UserPassword(this);
    }
  }
}
