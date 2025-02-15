package file_generator;

import ae.pegasus.framework.model.G4Entity;
import model.G4File;
import org.apache.log4j.Logger;

import java.util.List;

interface FileService<T extends G4Entity> {

    Logger log = Logger.getLogger(FileService.class);
    String path = "data/";

    G4File write(List<T> entities);
}
