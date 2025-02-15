package ae.pegasus.framework.pages.basic_pages;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.basic_pages.api.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class AppMainPage extends BasePage {

    private final String OPEN_QUEUES_BUTTON = "Queues";
    private final String OPEN_ALERT_HISTORY_BUTTON = "Alerting History";

    @Override
    public String getPageTitle() {
        return "Main Page";
    }

    @Override
    public boolean isPageDisplayed() {
        return getUserActionsDropDownButton().isDisplayed();
    }

    private SelenideElement getConfirmOrLogoutDialog() {
        return $x("//ux-dialog[contains(@class, 'policy-reminder')]");
    }

    private SelenideElement getUserActionsDropDownButton() {
        return $x("//div[contains(@class, 'pg-header__user')]");
    }

    public SelenideElement getConfirmOrLogoutDialogHeader() {
        return getConfirmOrLogoutDialog().$x(".//ux-dialog-header//h5");
    }

    public SelenideElement getAcceptButton() {
        return getConfirmOrLogoutDialog("Accept");
    }

    public SelenideElement getCancelButton() {
        return getConfirmOrLogoutDialog("Cancel");
    }

    private SelenideElement getConfirmOrLogoutDialog(String buttonName) {
        return getConfirmOrLogoutDialog().$x(".//ux-dialog-footer//pg-btn[@label='" + buttonName + "']");
    }

    private SelenideElement getTopMenuButton(String buttonName) {
        return $x("//pg-btn[@pg-tooltip='" + buttonName + "']");
    }

    public void openQueuesPage() {
        if (!Pages.queuesBasePage().isPageDisplayed()) {
            getTopMenuButton(OPEN_QUEUES_BUTTON).click();
            Pages.queuesBasePage().waitForPageLoading();
        }
    }

    public void closeQueuesPage() {
        if (Pages.queuesBasePage().isPageDisplayed()) {
            getTopMenuButton(OPEN_QUEUES_BUTTON).click();
        }
    }

    public void openAlertHistoryPage() {
        getTopMenuButton(OPEN_ALERT_HISTORY_BUTTON).click();
    }

    public void signOut() {
        getUserActionsDropDownButton().click();
        getActionFromDropDownMenu("Sign Out").click();
    }
}
