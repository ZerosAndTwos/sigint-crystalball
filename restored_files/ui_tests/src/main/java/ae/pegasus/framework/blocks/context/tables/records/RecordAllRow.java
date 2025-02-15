package ae.pegasus.framework.blocks.context.tables.records;

import com.codeborne.selenide.SelenideElement;

public class RecordAllRow extends RecordRow {

    public RecordAllRow(SelenideElement row) {
        super(row);
    }

    public String getSource() {
        return getCellByColumnName("Source").text();
    }


}
