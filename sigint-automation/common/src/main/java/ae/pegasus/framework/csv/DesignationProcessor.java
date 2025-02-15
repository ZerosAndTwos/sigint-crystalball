package ae.pegasus.framework.csv;

import java.util.List;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

class DesignationProcessor extends CellProcessorAdaptor {

  public DesignationProcessor() {
    super();
  }

  public DesignationProcessor(CellProcessor next) {
    super(next);
  }

  public Object execute(Object value, CsvContext context) {
    this.validateInputNotNull(value, context);
    if(!(value instanceof List)) {
      throw new SuperCsvCellProcessorException(List.class, value, context, this);
    } else {
      List<String> designations = (List<String>) value;
      String result = designations.get(designations.size() - 1);
      return this.next.execute(result, context);
    }
  }

}
