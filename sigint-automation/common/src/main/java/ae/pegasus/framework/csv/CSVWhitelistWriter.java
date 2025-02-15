package ae.pegasus.framework.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ae.pegasus.framework.model.G4File;
import ae.pegasus.framework.model.AbstractEntity;
import ae.pegasus.framework.model.Whitelist;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

public class CSVWhitelistWriter extends CSVFileWriter<Whitelist> {

  public CSVWhitelistWriter(String filename, boolean withHeader) {
    super(filename, withHeader);
  }

  @Override
  public CellProcessor[] getCellProcessors() {
    return new CellProcessor[] {
        new NotNull(), // identifier
        new NotNull(), // type
        new NotNull(), // description
    };
  }

  @Override
  public G4File writeEntitiesToCsv(List<Whitelist> entities) throws IOException {

    String outputFile = getOutputFile();

    ICsvBeanWriter beanWriter = null;
    try {
      beanWriter = new CsvBeanWriter(new FileWriter(outputFile), CsvPreference.STANDARD_PREFERENCE);

      // the header elements are used to map the bean values to each column (names must match)
      final String[] header = new String[] { "identifier", "type", "description" };
      final CellProcessor[] processors = getCellProcessors();

      // write the header
      if (isWithHeader()) {
        beanWriter.writeHeader(header);
      }
      // write the beans
      for (final AbstractEntity entity : entities) {
        beanWriter.write(entity, header, processors);
      }

    }
    finally {
      if( beanWriter != null ) {
        beanWriter.close();
      }
    }
    return new G4File(outputFile);
  }
}
