package ae.pegasus.framework.blocks.context.tables.records;

import ae.pegasus.framework.blocks.context.tables.EntityTable;

public class RecordsTable<T extends RecordRow> extends EntityTable<T> {

    public RecordsTable(Class classType) {
        super(classType);
    }


    @Override
    public T getRecordByColumnNameAndValue(String columnName, String columnValue) {
        String value;
        for (T recordRow : getEntitiesRows()) {
            if (columnName.equalsIgnoreCase("status")) {
                value = recordRow.getCellByColumnName(columnName).$("i.au-target").attr("title");
            }
            else {
                value = recordRow.getCellByColumnName(columnName).getText();
            }
            if (columnValue.equalsIgnoreCase(value)) {
                return recordRow;
            }
        }
        log.warn("Record with column name:" + columnName + " and value:" + columnValue + " was not found in the Records table!");
        throw new AssertionError();
    }
}
