package ae.pegasus.framework.ingestion.docker.adapters;

import ae.pegasus.framework.ingestion.docker.IDockerAdapter;
import com.spotify.docker.client.messages.ContainerConfig;

public class EtisalatCellTower2DockerAdapter implements IDockerAdapter {

  private static final String[] filemasks = {"*.csv"};
  private static final DockerImage dockerImage = DockerImage.dataGeneratorImage();

  @Override
  public ContainerConfig getContainerConfig(String recordsCount) {
    return dockerImage.getConfig("etisalat-celltower-format2", "-n", recordsCount,
            "-o", dockerImage.getDataPath() + "/cell_lbs_data_090723.csv");
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
