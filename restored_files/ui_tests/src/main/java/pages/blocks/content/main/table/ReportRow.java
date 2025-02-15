package pages.blocks.content.main.table;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.reports.ReportDetailsDialog;

import static com.codeborne.selenide.Selenide.page;

public class ReportRow extends Row {

    public ReportRow(SelenideElement row) {
        super(row);
    }

    public SelenideElement getShowInfoButton() {
        return getRow()
                .$(By.xpath(".//button[contains(@class, 'btn-info')]"));
    }

    public ReportDetailsDialog clickShowInfoButton() {
        getShowInfoButton().click();
        return page(ReportDetailsDialog.class);
    }

    public ReportRow selectReport() {
        if (!getCheckBox().isSelected()) {
            getCheckBox().click();
        }
        return this;
    }
}
