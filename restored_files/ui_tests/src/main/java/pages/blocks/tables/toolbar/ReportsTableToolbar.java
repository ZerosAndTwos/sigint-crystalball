package pages.blocks.tables.toolbar;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.reports.ReportsCreateManualPage;

import static com.codeborne.selenide.Selenide.page;

public class ReportsTableToolbar extends TableToolbar {

    public SelenideElement getCreateManualReportButton() {
        return getToolbar()
                .$(By.xpath(".//button[@click.trigger='manualReportCreationHandler()']"));
    }

    public ReportsCreateManualPage clickCreateManualReportButton() {
        getCreateManualReportButton().click();
        return page(ReportsCreateManualPage.class);
    }

}
