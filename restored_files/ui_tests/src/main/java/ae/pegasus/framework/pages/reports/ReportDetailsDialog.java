package ae.pegasus.framework.pages.reports;

import ae.pegasus.framework.blocks.context.Context;
import ae.pegasus.framework.pages.SigintPage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;

public class ReportDetailsDialog extends SigintPage {


    public SelenideElement getReportDetailsDialog() {
        return $(By.xpath("//*[@id='reportDetailsDialog']"));
    }

    private SelenideElement getRecordsTable() {
        return getReportDetailsDialog()
                .$(By.xpath(".//report-records-table"));
    }

    public ArrayList<ReportRecordRow> getRecords() {
        ArrayList<ReportRecordRow> reportRecordRows = new ArrayList<>();
        for (SelenideElement record : getRecordsTable().$$(By.xpath(".//tbody/tr")).shouldHave(sizeGreaterThan(0))) {
            reportRecordRows.add(new ReportRecordRow(record));
        }
        return reportRecordRows;
    }

    @Override
    public Context context() {
        return null;
    }
}
