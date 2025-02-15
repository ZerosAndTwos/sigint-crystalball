package ae.pegasus.framework.pages.special.queues;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.queue.SearchQueueTab;
import ae.pegasus.framework.pages.basic_pages.api.BaseSpecialPage;

import java.util.ArrayList;
import java.util.List;

import static ae.pegasus.framework.constants.CommonXPaths.INTERNAL_LOADING_XPATH;

public class QueuesBasePage extends BaseSpecialPage {

    private ElementsCollection getQueueTabs() {
        return getSpecialPageBase().$$x(".//div[@class='pg-nav-tabs__tabs']/div");
    }

    protected SelenideElement getQueueTab(SearchQueueTab tab) {
        for (SelenideElement item : getQueueTabs()) {
            if (item.getText().trim().equalsIgnoreCase(tab.getTabName())) {
                return item;
            }
        }
        throw new IllegalArgumentException("Tab with name '" + tab.getTabName() + "' was not found");
    }

    private List<String> getQueueTabsNames() {
        List<String> tabsNames = new ArrayList<>();
        for (SelenideElement item : getQueueTabs()) {
            tabsNames.add(item.getText().trim());
        }
        return tabsNames;
    }

    @Override
    public boolean isPageDisplayed() {
        return getSpecialPageBase().isDisplayed()
                && SearchQueueTab.getTabsNames().containsAll(getQueueTabsNames());
    }

    @Override
    public SelenideElement getPageLoading() {
        return getSpecialPageBase().$x(INTERNAL_LOADING_XPATH);
    }

    protected void openQueuesTab(SearchQueueTab tab) {
        getQueueTab(tab).click();
        waitForPageLoading();
    }
}
