package ae.pegasus.framework.pages.special.attach_to;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.elements.controls.dropdown.DropDown;
import ae.pegasus.framework.pages.basic_pages.api.BaseModalDialogPage;

public class AttachToOperatorReportPage extends BaseModalDialogPage {

    @Override
    public boolean isPageDisplayed() {
        return getCreateNewReportButton().isDisplayed();
    }

    private SelenideElement getCreateNewReportButton() {
        return getDialogBody().$x(".//pg-btn[@label='Create New Report']");
    }
    private SelenideElement getCreateNewMasterReportButton() {
        return getDialogBody().$x(".//pg-btn[@label='Create New Master Report']");
    }
    private DropDown getAttachToExistingReportDropDown() {
        return new DropDown(getDialogBody().$x(".//attach-to-report//pg-select"), false);
    }

    public void addToExistingReport(String reportName) {
        getAttachToExistingReportDropDown().searchAndSelectItems(reportName);
        waitDialogLoading();
    }

    public void createNewReport() {
        getCreateNewReportButton().click();
        waitDialogLoading();
    }

    public void createNewMasterReport() {
        getCreateNewMasterReportButton().click();
        waitDialogLoading();
    }
}
