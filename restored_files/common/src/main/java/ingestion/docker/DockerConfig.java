package ingestion.docker;

import static ingestion.IngestionService.INGESTION_DIR;

import app_context.properties.DockerProperties;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DefaultDockerClient.Builder;
import com.spotify.docker.client.DockerCertificates;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.HostConfig;
import ingestion.DockerDataGenerator;
import java.nio.file.Paths;
import org.apache.log4j.Logger;

public class DockerConfig {

  private static final Logger log = Logger.getLogger(DockerDataGenerator.class);
  private static DockerProperties props =  new DockerProperties();

  public static DockerClient getDockerClient() {
    Builder dockerBuilder = DefaultDockerClient.builder();
    dockerBuilder.uri(props.getClientURI());

    if (props.getClientURI().startsWith("https")) {
      try {
        dockerBuilder.dockerCertificates(new DockerCertificates(Paths.get(props.getCertPath())));
      } catch (DockerCertificateException e) {
        log.error(e.getMessage());
      }
    }
    return dockerBuilder.build();
  }

  public static ContainerConfig getTDataConfig(String... cmd) {

    final HostConfig hostConfig = HostConfig.builder()
        .appendBinds(INGESTION_DIR + ":/src/test_data")
        .build();

    ContainerConfig.Builder containerBuilder = ContainerConfig.builder();
    containerBuilder
        .hostConfig(hostConfig)
        .image("docker-registry.pegasus.ae/tdata-generator")
        .cmd(cmd);

    if (!props.getUser().isEmpty()) {
      containerBuilder.user(props.getUser());
    }
    return containerBuilder.build();
  }
}
