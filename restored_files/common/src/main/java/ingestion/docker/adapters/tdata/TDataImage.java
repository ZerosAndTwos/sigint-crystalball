package ingestion.docker.adapters.tdata;

import static ingestion.IngestionService.INGESTION_DIR;

import app_context.properties.DockerProperties;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.HostConfig;

class TDataImage {

  private static DockerProperties props =  new DockerProperties();
  private static final String IMAGE = "docker-registry.pegasus.ae/tdata-generator:1.01";
  private static final String DATA_PATH = "/src/test_data";

  static String getImage() {
    return IMAGE;
  }

  static ContainerConfig getConfig(String... cmd) {

    final HostConfig hostConfig = HostConfig.builder()
        .appendBinds(String.format("%s:%s", INGESTION_DIR, DATA_PATH))
        .build();

    ContainerConfig.Builder containerBuilder = ContainerConfig.builder();
    containerBuilder
        .hostConfig(hostConfig)
        .image(IMAGE)
        .cmd(cmd);

    if (!props.getUser().isEmpty()) {
      containerBuilder.user(props.getUser());
    }
    return containerBuilder.build();
  }
}
