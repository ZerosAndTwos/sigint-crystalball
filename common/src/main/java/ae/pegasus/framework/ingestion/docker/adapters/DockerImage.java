package ae.pegasus.framework.ingestion.docker.adapters;

import ae.pegasus.framework.app_context.properties.DockerProperties;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.HostConfig;

import static ae.pegasus.framework.ingestion.IngestionService.INGESTION_DIR;

class DockerImage {

  private static DockerProperties props = new DockerProperties();

  private String image;
  private String dataPath;

  private DockerImage(String image, String dataPath) {
    this.image = image;
    this.dataPath = dataPath;
  }

  String getImage() {
    return image;
  }

  String getDataPath() {
    return dataPath;
  }

  ContainerConfig getConfig(String... cmd) {

    final HostConfig hostConfig = HostConfig.builder()
        .appendBinds(String.format("%s:%s", INGESTION_DIR, dataPath))
        .build();

    ContainerConfig.Builder containerBuilder = ContainerConfig.builder();
    containerBuilder
        .hostConfig(hostConfig)
        .image(getImage())
        .cmd(cmd);

    if (!props.getUser().isEmpty()) {
      containerBuilder.user(props.getUser());
    }
    return containerBuilder.build();
  }

  static DockerImage dataGeneratorImage() {
    final String IMAGE = "docker-registry.pegasus.ae/pg/data-generator:1.03";
    final String DATA_PATH = "/src/test_data";

    return new DockerImage(IMAGE, DATA_PATH);
  }

  static DockerImage dataManagerImage() {
    final String IMAGE = "docker-registry.pegasus.ae/pg/data-manager:0.05";
    final String DATA_PATH = "/opt/app/output_content";

    return new DockerImage(IMAGE, DATA_PATH);
  }
}
