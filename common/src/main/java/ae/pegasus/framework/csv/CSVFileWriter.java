package ae.pegasus.framework.csv;

import ae.pegasus.framework.model.G4File;
import ae.pegasus.framework.ingestion.IngestionService;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import ae.pegasus.framework.model.AbstractEntity;
import org.supercsv.cellprocessor.ift.CellProcessor;

abstract class CSVFileWriter<T extends AbstractEntity> {

  private String filename;
  private boolean withHeader;

  public CSVFileWriter(String filename, boolean withHeader) {
    this.filename = filename;
    this.withHeader = withHeader;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public boolean isWithHeader() {
    return withHeader;
  }

  public void setWithHeader(boolean withHeader) {
    this.withHeader = withHeader;
  }

  String getOutputFile() {
    return Paths.get(IngestionService.IMPORT_DIR.toString(), filename).toString();
  }

  public abstract G4File writeEntitiesToCsv(List<T> entities) throws IOException;

  public abstract CellProcessor[] getCellProcessors();
}
