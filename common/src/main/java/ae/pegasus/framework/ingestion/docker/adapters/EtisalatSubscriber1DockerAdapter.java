package ae.pegasus.framework.ingestion.docker.adapters;

import com.spotify.docker.client.messages.ContainerConfig;
import ae.pegasus.framework.ingestion.docker.IDockerAdapter;

public class EtisalatSubscriber1DockerAdapter implements IDockerAdapter {

  private static final String[] filemasks = {"*subdata-1*"};
  private static final DockerImage dockerImage = DockerImage.dataGeneratorImage();

  @Override
  public ContainerConfig getContainerConfig(String recordsCount) {
    return dockerImage.getConfig("etisalat-subscriber-format1", "-n", recordsCount, "-d", dockerImage.getDataPath());
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