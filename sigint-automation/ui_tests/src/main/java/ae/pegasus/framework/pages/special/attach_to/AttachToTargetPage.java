package ae.pegasus.framework.pages.special.attach_to;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.basic_pages.ModalDialogPage;
import ae.pegasus.framework.utils.PageUtils;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

public class AttachToTargetPage extends ModalDialogPage {
    @Override
    public boolean isPageDisplayed() {
        return getCreateNewTargetButton().isDisplayed();
    }

    private SelenideElement getCreateNewTargetButton() {
        return getDialogBody().$x(".//pg-btn[@label='Create New Target']");
    }

    private SelenideElement getExistingTargetSearchBlock() {
        return getDialogBody().$x(".//div[contains(@class, 'pg-search-group')]");
    }

    private SelenideElement getExistingTarget(String targetName) {
        PageUtils.clearAndType(getExistingTargetSearchBlock().$x(".//input"), targetName);
        getExistingTargetSearchBlock().$x(".//span").click();
        waitDialogLoading();
        for (SelenideElement targetElement : getDialogBody().$$x(".//profile-listbox-item")) {
            if (targetElement.$x(".//div[@class='media-body']/strong").getText().trim().equalsIgnoreCase(targetName)) {
                return targetElement.$x(".//div[contains(@class, 'pg-listbox-item__content')]");
            }
        }
        throw new IllegalArgumentException("Target with name '" + targetName + "' was not found");
    }

    public void addToExistingTarget(String targetName) {
        closeAllPopUps();
        getExistingTarget(targetName).click();
        getSuccessPopUp().shouldBe(visible);
    }

    public void createNewTarget() {
        getCreateNewTargetButton().shouldBe(enabled).click();
        Pages.createTargetPage().waitUntilPageAppeared();
    }
}
