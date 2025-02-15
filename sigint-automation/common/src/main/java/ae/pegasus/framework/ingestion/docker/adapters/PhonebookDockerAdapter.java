package ae.pegasus.framework.ingestion.docker.adapters;

import ae.pegasus.framework.ingestion.docker.IDockerAdapter;
import com.spotify.docker.client.messages.ContainerConfig;

public class PhonebookDockerAdapter implements IDockerAdapter {

  private static final String[] filemasks = {"*.csv"};
  private static final DockerImage dockerImage = DockerImage.dataGeneratorImage();

  @Override
  public ContainerConfig getContainerConfig(String recordsCount) {
    return dockerImage.getConfig("phonebook", "-n", recordsCount, "-o", dockerImage.getDataPath() + "/phonebook.csv");
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
