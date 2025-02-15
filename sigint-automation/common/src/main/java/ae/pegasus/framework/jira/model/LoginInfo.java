package ae.pegasus.framework.jira.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginInfo {

  private Integer failedLoginCount;
  private Integer loginCount;
  private Date lastFailedLoginTime;
  private Date previousLoginTime;

  @Override
  public String toString() {
    return "LoginInfo{" +
        "failedLoginCount=" + failedLoginCount +
        ", loginCount=" + loginCount +
        ", lastFailedLoginTime=" + lastFailedLoginTime +
        ", previousLoginTime=" + previousLoginTime +
        '}';
  }

  public Integer getFailedLoginCount() {
    return failedLoginCount;
  }

  public void setFailedLoginCount(Integer failedLoginCount) {
    this.failedLoginCount = failedLoginCount;
  }

  public Integer getLoginCount() {
    return loginCount;
  }

  public void setLoginCount(Integer loginCount) {
    this.loginCount = loginCount;
  }

  public Date getLastFailedLoginTime() {
    return lastFailedLoginTime;
  }

  public void setLastFailedLoginTime(Date lastFailedLoginTime) {
    this.lastFailedLoginTime = lastFailedLoginTime;
  }

  public Date getPreviousLoginTime() {
    return previousLoginTime;
  }

  public void setPreviousLoginTime(Date previousLoginTime) {
    this.previousLoginTime = previousLoginTime;
  }
}
