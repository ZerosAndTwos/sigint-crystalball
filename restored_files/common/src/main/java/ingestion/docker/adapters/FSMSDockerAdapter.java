package ingestion.docker.adapters;

import com.spotify.docker.client.messages.ContainerConfig;
import ingestion.docker.IDockerAdapter;

import static ingestion.docker.adapters.DockerImage.dataGeneratorImage;

public class FSMSDockerAdapter implements IDockerAdapter {

  private static final String[] filemasks = {"*.csv"};
  private static final DockerImage dockerImage = dataGeneratorImage();

  @Override
  public ContainerConfig getContainerConfig(String recordsCount) {
    return dockerImage.getConfig("f-sms", "-n", recordsCount, "-o", dockerImage.getDataPath() + "/f_sms.csv");
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
