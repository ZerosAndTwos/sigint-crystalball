package ae.pegasus.framework.ingestion.docker.adapters;

import com.spotify.docker.client.messages.ContainerConfig;
import ae.pegasus.framework.ingestion.docker.IDockerAdapter;

import static ae.pegasus.framework.ingestion.docker.adapters.DockerImage.dataGeneratorImage;

public class SCELLDockerAdapter implements IDockerAdapter {

  private static final String[] filemasks = {"*.csv"};
  private static final DockerImage dockerImage = dataGeneratorImage();

  @Override
  public ContainerConfig getContainerConfig(String recordsCount) {
    return dockerImage.getConfig("s-cell", "-n", recordsCount, "-o", dockerImage.getDataPath() + "/s_cell.csv");
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