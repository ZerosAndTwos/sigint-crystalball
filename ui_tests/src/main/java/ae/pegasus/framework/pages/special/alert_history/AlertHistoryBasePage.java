package ae.pegasus.framework.pages.special.alert_history;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.special.alert_history.AlertHistoryTab;
import ae.pegasus.framework.pages.basic_pages.api.BasePage;

import static com.codeborne.selenide.Selenide.$$x;

public class AlertHistoryBasePage extends BasePage {

    private ElementsCollection getAlertHistoryTabs() {
        return $$x("//div[@class='pg-nav-tabs__tabs']/div");
    }

    private SelenideElement getAlertHistoryTab(AlertHistoryTab tab) {
        for (SelenideElement item : getAlertHistoryTabs()) {
            if (item.getText().trim().equalsIgnoreCase(tab.getTabName())) {
                return item;
            }
        }
        throw new IllegalArgumentException("Tab with name '" + tab.getTabName() + "' was not found");
    }

    @Override
    public boolean isPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("Alerting History");
    }

    protected void openAlertHistoryTab(AlertHistoryTab tab) {
        getAlertHistoryTab(tab).click();
        waitForPageLoading();
    }
}
