package docker;

import docker.model.DockerContainer;
import errors.NullReturnException;
import model.AppContext;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import json.JsonCoverter;
import json.RsClient;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dm on 4/12/16.
 */
public class Docker {

    private String dockerHost;
    private RsClient client = new RsClient();
    private final String port = "2376";
    private Logger log = Logger.getRootLogger();
    private List<DockerContainer> containers = new ArrayList<>();


    public Docker() {
        try {
            Pattern pattern = Pattern.compile("(^http(s)*://)?.*([^:81])");
            Matcher matcher = pattern.matcher(AppContext.getContext().environment().getSigintHost());
            matcher.find();
            this.dockerHost = matcher.group();
        } catch (IllegalStateException e) {
            log.error(e.getMessage());
        }

    }

    private List<DockerContainer> getContainers() throws IOException {
        log.debug("Getting list of docker containers");
        if (containers.size() > 0) {
            return containers;
        }
        try {
            Response response = client.get(dockerHost + ":" + port + "/containers/json");
            String jsonString = response.readEntity(String.class);
            containers = JsonCoverter.fromJsonToObjectsList(jsonString, DockerContainer[].class);
            if (containers.size() == 0) {
                throw new IOException();
            }
        } catch (ProcessingException e) {
            log.error("Connection to docker daemon wasn't established");
            throw new IOException("Cannot connect to docker daemon");
        } catch (NullReturnException e) {
            log.error("Error occurred converting json string to list of containers");
            throw new IOException("Error getting list of docker containers");
        }
        return containers;

    }

    public DockerContainer getContainerByRole(String role) throws NullReturnException, IOException {
        log.debug("Getting docker container by role:" + role);
        List<DockerContainer> containers = getContainers();
        for (DockerContainer container: containers) {
            for (String name: container.getNames()) {
                if (name.toLowerCase().contains(role.toLowerCase())) {
                    return container;
                }
            }
        }
        throw new NullReturnException("There are no containers with given role:"+role);
    }

    public void putLogFileToDir(File outputDir, DockerContainer dockerContainer) throws IOException {
        log.debug("Putting logs from container:" + dockerContainer + " to output dir");
        String containerName = dockerContainer.getNames().get(0);
        InputStream is  = client.client()
                .target(dockerHost + ":"+ port + "/containers/" + containerName +
                        "/logs?stderr=1&stdout=1&timestamps=0g&follow=0")
                .request(MediaType.APPLICATION_OCTET_STREAM)
                .get(InputStream.class);
        log.debug("Writing log info to output file with name " + containerName);
        File tempFile = File.createTempFile(containerName, ".txt", outputDir);
        OutputStream os = new FileOutputStream(tempFile);
        byte[] buffer = new byte[8 * 1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }
}
