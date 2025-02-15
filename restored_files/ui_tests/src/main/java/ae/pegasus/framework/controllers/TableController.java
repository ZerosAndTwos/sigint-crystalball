package ae.pegasus.framework.controllers;

import ae.pegasus.framework.blocks.context.tables.Row;
import ae.pegasus.framework.pages.SigintPage;
import ae.pegasus.framework.model.G4Entity;
import ae.pegasus.framework.blocks.context.tables.EntityTable;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TableController extends PageController {

    protected EntityTable table;
    Logger log = Logger.getLogger(TableController.class);

    public TableController(SigintPage page)  {
        super(page);
        this.table = this.getPage().context().getTable();
    }

    protected abstract <T extends G4Entity>T initFromRow(Row recordRow);

    protected Row selectRecordInTable(Row recordRow) {
        if (!recordRow.getCheckBox().isSelected()) {
            recordRow.getCheckBox().click();
        }
        return recordRow;
    }

    public void waitLoading() {
        table.getTable().waitLoad();
    }


    public static Filter filter() {
        return new Filter();

    }

    protected Row selectRowByFilter(Filter filter) {
        List<Row> rowList;
        rowList = table.filterBy(filter.getFilterValues());
        if (rowList.size()==0){
            log.error("There are not record in the table set by isAppliedToEntity");
            throw new AssertionError();
        }
        Row row = rowList.get(0);
        row.getCheckBox().click();
        return row;

    }

    public static class Filter {
        Map<String, String> getFilterValues() {
            return filterValues;
        }

        @Override
        public String toString(){
            return filterValues.toString();

        }

        Filter(){}

        Map<String, String> filterValues = new HashMap<>();


        public Filter set(String param, String value) {
            filterValues.put(param, value);
            return this;
        }

    }

}
