package ae.pegasus.framework.blocks.context.tables.reports;

import com.codeborne.selenide.SelenideElement;

public class ReportAllRow extends ReportRow {

    public ReportAllRow(SelenideElement row) {
        super(row);
    }

    public String getStatus() {
        return getCellByColumnName("Status").text();
    }
}
