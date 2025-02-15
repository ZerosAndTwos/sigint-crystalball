package ae.pegasus.framework.pages.basic_pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import ae.pegasus.framework.constants.special.modal_dialog.ModalDialogButton;
import ae.pegasus.framework.constants.special.modal_dialog.ModalDialogField;
import org.apache.commons.lang.NotImplementedException;
import ae.pegasus.framework.pages.basic_pages.api.BaseModalDialogPage;
import ae.pegasus.framework.utils.PageUtils;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;

public class ModalDialogPage extends BaseModalDialogPage {

    @Override
    public boolean isPageDisplayed() {
        return getDialogContainer().isDisplayed();
    }

    @Override
    public String getPageTitle() {
        String result = "";
        if (getDialogHeaderContent().isDisplayed()) {
            result = getDialogHeaderContent().getText().trim();
            for (SelenideElement extraElement : getDialogHeaderContent().$$x(".//button")) {
                result = result.replace("\n" + extraElement.getText(), "");
            }
        }
        return result;
    }

    private SelenideElement getDialogHeaderContent() {
        return getDialogContainer().$x(".//ux-dialog-header//div[@class='dialog-header-content']");
    }

    private ElementsCollection getDialogButtons() {
        return getDialogContainer().$$x("//ux-dialog-footer//button");
    }

    public void clickButton(ModalDialogButton button) {
        for (SelenideElement item : getDialogButtons()) {
            String buttonText = button.getXPathToButtonName().isEmpty() ? item.getText() : item.$x(button.getXPathToButtonName()).getText();
            if (buttonText.trim().equalsIgnoreCase(button.getButtonName())) {
                item.click();
                return;
            }
        }
        throw new IllegalArgumentException("Button '" + button.getButtonName() + "' was not found in the modal dialog");
    }

    public void setDataToField(ModalDialogField field, String data) {
        SelenideElement fieldElement = getBasicDataElementForField(field);
        switch (field.getControlType()) {
            case TEXT_AREA:
            case INPUT:
                PageUtils.clearAndType(fieldElement, data);
                break;
            default:
                throw new NotImplementedException("Method is not implemented for '" + field.getControlType().toString() + "'-control");
        }
    }

    public void clickButton(ModalDialogButton button, long delayBeforeClickInMS) {
        sleep(delayBeforeClickInMS);
        clickButton(button);
    }

    public void clickButtonIfDialogWithTitleAppeared(String dialogTitle, ModalDialogButton button, long waitForDialogInMS) {
        try {
            getDialogContainer().waitUntil(visible,  waitForDialogInMS, 10);
            if (getPageTitle().equalsIgnoreCase(dialogTitle)) {
                clickButton(button);
            }
        } catch (UIAssertionError e) {
            System.out.println("Dialog doesn't appear within " + waitForDialogInMS + "ms");
            //Do nothing since element can be absent
        }
    }

    public final void waitForModalDialog() {
        waitForModalDialog(0);
    }

    public void waitForModalDialog(long delayForLoadingAppearInMS) {
        try {
            getDialogContainer().waitUntil(visible, 100 + delayForLoadingAppearInMS, 10);
            System.out.println("Page's loading appears");
        } catch (UIAssertionError e) {
            System.out.println("Page's loading does not appear");
            //Do nothing since element can be absent
        }
       // getPageLoading().shouldBe(hidden);
    }
}
