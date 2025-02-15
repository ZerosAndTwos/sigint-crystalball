package ae.pegasus.framework.pages.special.IM_requests.request_for_information;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.special.modal_dialog.ModalDialogButton;
import ae.pegasus.framework.elements.controls.dropdown.DropDown;
import org.openqa.selenium.Keys;
import ae.pegasus.framework.pages.basic_pages.ModalDialogPage;

import static com.codeborne.selenide.Condition.visible;

public class RequestForInformationSendingDialog extends ModalDialogPage {
    private SelenideElement getOrgUnit() {
        return getDialogBody().$x(".//pg-field//pg-select");
    }

    public void selectOrganizationUnit(String organizationUnit) {
        new DropDown(getOrgUnit(), false).searchAndSelectItems(organizationUnit);
    }

    public void removeOrganizationUnit() {
        getOrgUnit().click();
        getOrgUnit().sendKeys(Keys.BACK_SPACE);
    }

    public void completeSending() {
        closeAllPopUps();
        clickButton(ModalDialogButton.OK);
        getSuccessPopUp().shouldBe(visible);
    }
}
