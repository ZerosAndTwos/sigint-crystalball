package ae.pegasus.framework.ingestion.docker.adapters;

import com.spotify.docker.client.messages.ContainerConfig;
import ae.pegasus.framework.ingestion.docker.IDockerAdapter;

public class SEmailDockerAdapter implements IDockerAdapter {

    private static final String[] filemasks = {"*.eml", "*.xml"};
    private static final DockerImage dockerImage = DockerImage.dataManagerImage();

    @Override
    public ContainerConfig getContainerConfig(String recordsCount) {
        return dockerImage.getConfig("-n", recordsCount, "S-Email_ZDF_XML_Simulation.json");
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
