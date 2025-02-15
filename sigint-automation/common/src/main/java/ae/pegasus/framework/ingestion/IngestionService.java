package ae.pegasus.framework.ingestion;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IngestionService {

  private static final Logger log = Logger.getLogger(IngestionService.class);

  public static final Path INGESTION_DIR = Paths.get(System.getProperty("user.dir"), "ingestionData");
  public static final Path IMPORT_DIR = Paths.get(System.getProperty("user.dir"), "importData");
  public static final Path INJECTIONS_FILE = Paths.get(INGESTION_DIR.toString(), "data.injections");
  private final IIngestionDataGenerator ingestionDataGenerator;

  public IngestionService(IIngestionDataGenerator ingestionDataGenerator) {
    this.ingestionDataGenerator = ingestionDataGenerator;
  }

  public IIngestionDataGenerator getGenerator() {
    return ingestionDataGenerator;
  }

  public static void cleanIngestionDir() {
    cleanDirectory(INGESTION_DIR.toString());
  }

  public static void cleanImportDir() {
    cleanDirectory(IMPORT_DIR.toString());
  }

  private static void cleanDirectory(String directory) {
    FileUtils.deleteQuietly(new File(directory));
    try {
      FileUtils.forceMkdir(new File(directory));
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }

  public static boolean pathExists(Path path) {
    return Files.exists(path);
  }

  public static void removePath(Path path) {
    FileUtils.deleteQuietly(new File(path.toString()));
  }

}
