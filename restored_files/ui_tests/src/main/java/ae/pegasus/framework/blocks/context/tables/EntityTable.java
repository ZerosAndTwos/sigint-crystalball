package ae.pegasus.framework.blocks.context.tables;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.errors.NotFoundException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class EntityTable <T extends Row> {

    private Class<T> classType;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    private Table table = new Table();
    public Logger log = Logger.getLogger(EntityTable.class);

    public EntityTable(Class<T> classType) {
        this.classType = classType;
    }

    public List<T> getEntitiesRows() {
        ArrayList<T> rows = new ArrayList<>();
        for (SelenideElement selRow : table.getRows()) {
            try {
                T row = classType.getConstructor(SelenideElement.class).newInstance(selRow);
                rows.add(row);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new AssertionError();
            }
        }
        return rows;
    }

    public List<T> filterBy(Map<String,String> filters) {
        List<T> rows = getEntitiesRows();
            for(String column:filters.keySet()) {
                rows = filter(rows, column, filters.get(column));
        }
        return rows;
    }


    private List<T> filter(List<T> rows, String column, String value) {
        List<T> newRows = new ArrayList<>(rows);
        for (Row row: rows) {
            if (!row.getCellByColumnName(column).text().toLowerCase().
                    contentEquals(value.toLowerCase())) {
                newRows.remove(row);
            }
        }
        return newRows;
    }


    public T getRecordByColumnNameAndValue(String columnName, String columnValue) throws NotFoundException {
        log.debug("Finding record by column name:" + columnName + " and value:" + columnValue + " in the Records table");
        for (T recordRow : getEntitiesRows()) {
            if (recordRow.getCellByColumnName(columnName).getText().contentEquals(columnValue)) {
                return recordRow;
            }
        }

        log.warn("Record with column name:" + columnName + " and value:" + columnValue + " was not found in the table!");
        throw new NotFoundException();
    }
}
