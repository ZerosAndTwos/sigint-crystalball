package ae.pegasus.framework.pages.basic_pages.api;

import ae.pegasus.framework.app_context.properties.G4Properties;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import ae.pegasus.framework.constants.special.modal_dialog.ModalDialogField;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static ae.pegasus.framework.constants.CommonXPaths.INTERNAL_LOADING_XPATH;
import static ae.pegasus.framework.constants.CommonXPaths.INTERNAL_SAVING_XPATH;

public abstract class BaseModalDialogPage extends BasePage {
    protected SelenideElement getDialogContainer() {
        return $x("//ux-dialog-container");
    }

    protected SelenideElement getDialogHeader() {
        return getDialogContainer().$x(".//ux-dialog-header");
    }

    protected SelenideElement getDialogBody() {
        return getDialogContainer().$x(".//ux-dialog-body");
    }

    protected SelenideElement getDialogFooter() {
        return getDialogContainer().$x(".//ux-dialog-footer");
    }

    protected SelenideElement getDialogLoading() {
        return getDialogContainer().$x(INTERNAL_LOADING_XPATH);
    }

    protected SelenideElement getDialogSaving() {
        return getDialogContainer().$x(INTERNAL_SAVING_XPATH);
    }

    protected SelenideElement getFieldByLabel(String fieldLabel) {
        return getDialogBody().$x(".//pg-field[@label='" + fieldLabel + "']");
    }

    protected SelenideElement getBasicDataElementForField(ModalDialogField field) {
        return getBasicDataElement(field.getControlType(), getFieldByLabel(field.getFieldName()));
    }

    @Override
    public String getPageTitle() {
        return getDialogHeader().$x(".//div[@class='dialog-header-content']").getText().trim();
    }

    public void closeDialog() {
        getDialogHeader().$x(".//button[contains(@class, 'dialog-close')]").click();
    }

    public void waitDialogLoading() {
        waitForElementDisappear(getDialogLoading(), null);
    }

    public void waitDialogSaving() {
        waitForElementDisappear(getDialogSaving(), G4Properties.getRunProperties().getLongTimeoutInMS());
    }

    private void waitForElementDisappear(SelenideElement whichElementShouldDisappear, Integer waitDisappearTimeoutInMS) {
        try {
            whichElementShouldDisappear.waitUntil(visible, 100, 10);
            System.out.println("Element appear");
        } catch (UIAssertionError e) {
            //Do nothing since element can be absent
        }
        if (waitDisappearTimeoutInMS == null) {
            whichElementShouldDisappear.shouldBe(hidden);
        } else {
            whichElementShouldDisappear.waitUntil(hidden, waitDisappearTimeoutInMS);
        }
        System.out.println("Element disappear");
    }
}
