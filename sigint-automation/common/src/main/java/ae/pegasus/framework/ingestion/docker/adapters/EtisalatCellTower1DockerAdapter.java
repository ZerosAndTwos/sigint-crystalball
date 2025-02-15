package ae.pegasus.framework.ingestion.docker.adapters;

import com.spotify.docker.client.messages.ContainerConfig;
import ae.pegasus.framework.ingestion.docker.IDockerAdapter;

public class EtisalatCellTower1DockerAdapter implements IDockerAdapter {

  private static final String[] filemasks = {"*.txt"};
  private static final DockerImage dockerImage = DockerImage.dataGeneratorImage();

  @Override
  public ContainerConfig getContainerConfig(String recordsCount) {
    return dockerImage.getConfig("etisalat-celltower-format1", "-n", recordsCount,
            "-o", dockerImage.getDataPath() + "/cell_data_170723.txt");
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
