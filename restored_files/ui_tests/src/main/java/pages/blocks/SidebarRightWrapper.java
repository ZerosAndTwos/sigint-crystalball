package pages.blocks;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.reports.ReportsDraftPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SidebarRightWrapper {

    public SelenideElement getSidebar() {
        return $(By.xpath("//div[@class='sidebar-right-wrapper']"));
    }

    public SelenideElement getSaveDraftButton() {
        return getSidebar()
                .$(By.xpath(".//button[contains(normalize-space(),'Save as Draft')]"));
    }

    public ReportsDraftPage clickSaveDraft() {
        getSaveDraftButton().click();
        return page(ReportsDraftPage.class);
    }

    public ReportsDraftPage clickSubmitButton() {
        $(By.xpath(".//button[@ref='publishButton' and contains(normalize-space(), 'Submit')]")).click();
        return page(ReportsDraftPage.class);
    }

    public SelenideElement getPublishButton() {
        return getSidebar()
                .$(By.xpath(".//button[@ref='publishButton' and contains(normalize-space(), 'Publish')]"));
    }

    public void clickPublishButton() {
        getPublishButton().click();
    }

    public SelenideElement getRemoveReportOwnership(){
        return getSidebar()
                .$(By.xpath(".//button[@click.trigger='removeReportOwnership()']"));
    }

    public void clickRemoveReportOwnership() {
        getRemoveReportOwnership().click();
    }

}
