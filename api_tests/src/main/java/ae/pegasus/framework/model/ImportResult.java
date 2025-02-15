package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportResult {

  private Integer importedRows;
  private Integer modifiedObjects;
  private HashMap<String, String> rejectedRows;

  public Integer getImportedRows() {
    return importedRows;
  }

  public void setImportedRows(Integer importedRows) {
    this.importedRows = importedRows;
  }

  public Integer getModifiedObjects() {
    return modifiedObjects;
  }

  public void setModifiedObjects(Integer modifiedObjects) {
    this.modifiedObjects = modifiedObjects;
  }

  public HashMap<String, String> getRejectedRows() {
    return rejectedRows;
  }

  public void setRejectedRows(HashMap<String, String> rejectedRows) {
    this.rejectedRows = rejectedRows;
  }
}
