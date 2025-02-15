package ae.pegasus.framework.blocks.context.tables.reports;

import ae.pegasus.framework.blocks.context.tables.Row;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ae.pegasus.framework.pages.reports.ReportDetailsDialog;
import ae.pegasus.framework.pages.reports.ReportsEditPage;

import static com.codeborne.selenide.Selenide.page;

public class ReportRow extends Row {

    public ReportRow(SelenideElement row) {
        super(row);
    }

    public SelenideElement getShowInfoButton() {
        return row
                .$(By.xpath(".//button[contains(@class, 'btn-info')]"));
    }

    public ReportDetailsDialog clickShowInfoButton() {
        getShowInfoButton().hover().click();
        return page(ReportDetailsDialog.class);
    }


    public SelenideElement getActionsCell() {
        return row
                .$(By.xpath(".//div[contains(@class, 'pg-actions-cell')]"));
    }

    public SelenideElement getEditReportButton() {
        return getActionsCell()
                .$(By.xpath(".//a[contains(@route-href, 'ROUTE_REPORT_EDIT')]"));
    }

    public ReportsEditPage clickEditReportButton() {
        getEditReportButton().hover().click();
        return page(ReportsEditPage.class);
    }


    public String getSubject() {
        return getCellByColumnName("Subject").text();
    }

    public String getReportID() {
        return getCellByColumnName("Report ID").text();
    }

    public String getOwner() {
        return getCellByColumnName("Owner").text();
    }
}
