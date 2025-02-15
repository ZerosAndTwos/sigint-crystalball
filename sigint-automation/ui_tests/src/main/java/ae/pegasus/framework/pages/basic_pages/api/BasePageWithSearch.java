package ae.pegasus.framework.pages.basic_pages.api;

import ae.pegasus.framework.assertion.Asserter;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.utils.PageUtils;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static ae.pegasus.framework.conditions.ContinuouslyConditions.enabledFor;
import static ae.pegasus.framework.conditions.ContinuouslyConditions.hiddenFor;

public abstract class BasePageWithSearch extends BasePage {
    private final String SEARCH_RELOAD_BUTTON = "Reload";
    private final String SEARCH_RESET_BUTTON = "Reset";


    private static ThreadLocal<SearchStates> searchStates = ThreadLocal.withInitial(SearchStates::new);

    @Override
    protected int getYCenterPageOffset() {
        return 600;
    }

    @Override
    public boolean isPageDisplayed() {
        return  getSearchBlock().isDisplayed()
                && getSearchFilterButton().isDisplayed()
                && getSearchActionButton(SEARCH_RELOAD_BUTTON).isDisplayed()
                && getSearchActionButton(SEARCH_RESET_BUTTON).isDisplayed();
    }

    @Override
    public void waitForPageLoading(long delayForLoadingAppearInMS) {
        try {
            if (!searchStates.get().searchAuthorizationAppeared && Pages.searchAuthorizationPage().isPageDisplayed()) {
                searchStates.get().searchAuthorizationAppeared = true;
                return;
            }
            getPageLoading().waitUntil(visible, 100 + delayForLoadingAppearInMS, 10);
            System.out.println("Search page's loading appears");
        } catch (UIAssertionError e) {
            System.out.println("Search page's loading does not appear");
            //Do nothing since element can be absent
        }
        if (!searchStates.get().searchAuthorizationAppeared && Pages.searchAuthorizationPage().isPageDisplayed()) {
            searchStates.get().searchAuthorizationAppeared = true;
            return;
        }
        getPageLoading().shouldBe(hiddenFor(100));
        searchStates.get().searchAuthorizationAppeared = false;
    }

    protected SelenideElement getSearchBlock() {
        return  $x("//div[contains(@class, 'pg-search-group')]");
    }

    protected SelenideElement getSearchInputField() {
        return getSearchBlock().$x("./input");
    }

    protected SelenideElement getSubmitSearchButton() {
        return getSearchBlock().$x("./span");
    }

    public void enterSearchCriteria(String searchCriteria) {
        getSearchInputField().shouldBe(enabled).click();
        PageUtils.clearAndType(getSearchInputField(), searchCriteria);
    }

    public void startSearch() {
        getSubmitSearchButton().click();
        if (!Pages.searchAuthorizationPage().getDialogHeader().isDisplayed()) {
            waitForPageLoading();
        } else {
            searchStates.get().searchAuthorizationAppeared = true;
        }
    }

    public String getCurrentSearchCriteria() {
        return getSearchInputField().getValue();
    }

    protected SelenideElement getSearchFilterButton() {
        return $x("//search-filter-dropdown").shouldBe(visible, enabled);
    }

    public void openSearchFilter() {
        getSearchFilterButton().click();
        /*if (!Pages.searchFilterPage().isPageDisplayed()) {
            Asserter.getAsserter().softAssertTrue(false, "", "Applied patch for open search filter page");
            getSearchFilterButton().click();
        }*/
    }

    protected SelenideElement getSearchActionButton(String actionButton) {
        return $x("//pg-btn[@pg-tooltip='" + actionButton + "']");
    }

    public void reloadSearch() {
        getSearchActionButton(SEARCH_RELOAD_BUTTON).click();
        waitForPageLoading();
    }

    public void resetSearch() {
        getSearchActionButton(SEARCH_RESET_BUTTON).shouldBe(enabledFor(100)).click();
    }



    protected SelenideElement getSortByBlock() {
        return $x("//span[contains(., 'Sort by')]/..");
    }

    protected SelenideElement getSortBySelector() {
        return getSortByBlock().$x(".//pg-select");
    }
}

class SearchStates {
    boolean searchAuthorizationAppeared = false;
}
