package ae.pegasus.framework.ingestion.docker;

import ae.pegasus.framework.app_context.properties.DockerProperties;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DefaultDockerClient.Builder;
import com.spotify.docker.client.DockerCertificates;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.RegistryAuth;
import ae.pegasus.framework.error_reporter.ErrorReporter;
import ae.pegasus.framework.ingestion.IIngestionDataGenerator;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ae.pegasus.framework.ingestion.IngestionService.INGESTION_DIR;
import static org.junit.Assert.assertNotNull;
import static ae.pegasus.framework.utils.FileHelper.extractTarGzFiles;
import static ae.pegasus.framework.utils.FileHelper.getFilesByWildcards;

public class DockerDataGenerator implements IIngestionDataGenerator {

  private static final Logger log = Logger.getLogger(DockerDataGenerator.class);
  private static DockerProperties props =  new DockerProperties();

  private DockerClient docker;
  private final IDockerAdapter dockerAdapter;

  public DockerDataGenerator(IDockerAdapter dockerAdapter) {
    this.dockerAdapter = dockerAdapter;
    this.docker = getDockerClient();
  }

  private static DockerClient getDockerClient() {
    Builder dockerBuilder = DefaultDockerClient.builder();
    final RegistryAuth registryAuth = RegistryAuth.builder()
        .username(props.getRegistryLogin())
        .password(props.getRegistryPassword())
        .serverAddress(props.getRegistryAddress())
        .build();
    dockerBuilder.registryAuth(registryAuth);

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

  private void generateDataInContainer(String recordsCount) {
    assertNotNull("Can't init container config", dockerAdapter.getContainerConfig(recordsCount));
    String containerId = null;
    try {
      log.info("Pulling image...");
      docker.pull(dockerAdapter.getImageName());

      log.info("Creating container...");
      final ContainerCreation creation = docker.createContainer(dockerAdapter.getContainerConfig(recordsCount));
      containerId = creation.id();
      // Start container, wait for execution and delete after that
      docker.startContainer(containerId);
      docker.waitContainer(containerId);

      final String logs;
      try (LogStream stream = docker.logs(containerId, DockerClient.LogsParam.stdout(), DockerClient.LogsParam.stderr())) {
        logs = stream.readFully();
      }
      log.info("Output:\n" + logs);

    } catch (DockerException | InterruptedException e) {
      log.error(e.getMessage());
    } finally {
      try {
        docker.removeContainer(containerId);
      } catch (DockerException | InterruptedException | NullPointerException e) {
        log.error(e.getMessage());
      }
      docker.close();
    }
  }

  @Override
  public List<File> generateIngestionFiles(String recordsCount) {

    generateDataInContainer(recordsCount);

    String path = INGESTION_DIR.normalize().toString();

    // extract tar.gz if exists
    File ingestionDir = new File(path);
    try {
      extractTarGzFiles(ingestionDir);
    } catch (IOException e) {
      log.error("Can't extract archives: " + e.getMessage());
    }

    List<File> files = new ArrayList<>();
    // Add files by priority in formats, described in filemasks array
    // Ex. Voice sources should ingest audio files firstly
    for (String filemask: dockerAdapter.getFilemasks()) {
      List<File> filesByMask = getFilesByWildcards(path, new String[]{filemask});
      files = Stream.concat(files.stream(), filesByMask.stream())
              .collect(Collectors.toList());

    }
    if (files.isEmpty()) {
      ErrorReporter.raiseError(String.format("Can't find files by mask %s in %s",
          Arrays.toString(dockerAdapter.getFilemasks()), path));
    } else {
      log.info("Found " + files.size() + " file(s) by mask " + Arrays.toString(dockerAdapter.getFilemasks()));
      files.forEach(file -> log.info(file.toString()));
    }

    return files;
  }
}
