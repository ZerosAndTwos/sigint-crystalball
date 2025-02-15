package pages.blocks.tables;

import com.codeborne.selenide.SelenideElement;
import pages.blocks.tables.toolbar.RecordsTableToolbar;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.page;

public class RecordsTable extends Table {

    public RecordsTableToolbar getTableToolbar() {
        return page(RecordsTableToolbar.class);
    }

    public ArrayList<RecordRow> getRecords() {
        ArrayList<RecordRow> recordRows = new ArrayList<>();
        for (SelenideElement row : getRows()) {
            recordRows.add(new RecordRow(row));
        }
        return recordRows;
    }

    public RecordRow getRecordByColumnNameAndValue(String columnName, String columnValue) {
        log.debug("Find record by column name:" + columnName + " and value:" + columnValue + " in the Records table");

        for (RecordRow recordRow : getRecords()) {
            if (recordRow.getCellByColumnName(columnName).getText().contentEquals(columnValue)) {
                return recordRow;
            }
        }

        log.warn("Record with column name:" + columnName + " and value:" + columnValue + " was not found in the Records table!");
        return null;
    }

    public RecordRow firstRecord() {
        return getRecords().get(0);
    }
}
