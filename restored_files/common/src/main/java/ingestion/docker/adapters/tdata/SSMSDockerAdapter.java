package ingestion.docker.adapters.tdata;

import com.spotify.docker.client.messages.ContainerConfig;
import ingestion.docker.IDockerAdapter;

public class SSMSDockerAdapter extends TDataImage implements IDockerAdapter {

  private static final String[] filemasks = {"strategic*"};

  @Override
  public ContainerConfig getContainerConfig(String recordsCount) {
    return getConfig("-l", recordsCount, "-t", "strategic");
  }

  @Override
  public String[] getFilemasks() {
    return filemasks;
  }

  @Override
  public String getImageName() {
    return getImage();
  }
}
