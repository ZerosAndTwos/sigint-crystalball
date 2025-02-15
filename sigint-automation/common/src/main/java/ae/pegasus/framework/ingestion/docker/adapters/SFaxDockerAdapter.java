package ae.pegasus.framework.ingestion.docker.adapters;

import ae.pegasus.framework.ingestion.docker.IDockerAdapter;
import com.spotify.docker.client.messages.ContainerConfig;

public class SFaxDockerAdapter implements IDockerAdapter {

  private static final String[] filemasks = {"*.tif", "*.md"};
  private static final DockerImage dockerImage = DockerImage.dataGeneratorImage();

  @Override
  public ContainerConfig getContainerConfig(String recordsCount) {
    return dockerImage.getConfig("s-fax", "-n", recordsCount, "-d", dockerImage.getDataPath());
  }

  @Override
  public String[] getFilemasks() {
    return filemasks;
  }

  @Override
  public String getImageName() {
    return dockerImage.getImage();
  }

}
