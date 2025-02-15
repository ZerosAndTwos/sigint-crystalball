package ae.pegasus.framework.ingestion.docker;

import com.spotify.docker.client.messages.ContainerConfig;

public interface IDockerAdapter {

  ContainerConfig getContainerConfig(String recordsCount);

  String[] getFilemasks();

  String getImageName();
}
