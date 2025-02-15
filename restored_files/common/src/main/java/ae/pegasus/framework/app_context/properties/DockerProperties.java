package ae.pegasus.framework.app_context.properties;

import java.io.InputStream;

public class DockerProperties extends ApplicationProperty {

  @Override
  InputStream loadPropertyFile() {
    return getClass().getClassLoader().getResourceAsStream("docker.properties");
  }

  public String getClientURI() {
    return getProperty().getProperty("clientURI");
  }

  public String getCertPath() {
    return getProperty().getProperty("certificate");
  }

  public String getUser() {
    return getProperty().getProperty("runAsUser");
  }

  public String getRegistryLogin() {
    return getProperty().getProperty("registryUser");
  }

  public String getRegistryPassword() {
    return getProperty().getProperty("registryPassword");
  }

  public String getRegistryAddress() {
    return getProperty().getProperty("registryURI");
  }
}
