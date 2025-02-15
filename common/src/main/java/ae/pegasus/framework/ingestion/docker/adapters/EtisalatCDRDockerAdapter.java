package ae.pegasus.framework.ingestion.docker.adapters;

import com.spotify.docker.client.messages.ContainerConfig;
import ae.pegasus.framework.ingestion.docker.IDockerAdapter;

import static ae.pegasus.framework.ingestion.docker.adapters.DockerImage.dataGeneratorImage;

public class EtisalatCDRDockerAdapter implements IDockerAdapter {

  private static final String[] filemasks = {"*.FILT"};
  private static final DockerImage dockerImage = dataGeneratorImage();

  @Override
  public ContainerConfig getContainerConfig(String recordsCount) {
    return dockerImage.getConfig("etisalat-cdr", "-n", recordsCount, "-d", dockerImage.getDataPath());
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