package ae.pegasus.framework.pages.search;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.pages.basic_pages.api.BasePageWithSearch;

import static com.codeborne.selenide.Selenide.$x;

public class SearchPage extends BasePageWithSearch {

    private final String SAVE_SEARCH_BUTTON_LABEL = "Save Search";
    private final String UPDATE_SAVED_SEARCH_BUTTON_LABEL = "Update Saved Search";


    @Override
    public boolean isPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("Search")
                && super.isPageDisplayed();

    }

    private SelenideElement getSaveSearchBase() {
        return $x("//save-search-button");
    }

    private SelenideElement getSaveSearchButton(String buttonLabel) {
        return getSaveSearchBase().$x(".//pg-btn[@label='" + buttonLabel + "']");
    }

    private SelenideElement getOpenRecentSavedSearchButton() {
        return getSaveSearchBase().$x(".//pg-dropdown");
    }

    public void pressSaveSearch() {
        getSaveSearchButton(SAVE_SEARCH_BUTTON_LABEL).click();
    }

    public void pressUpdateSavedSearch() {
        getSaveSearchButton(UPDATE_SAVED_SEARCH_BUTTON_LABEL).click();
    }

    public void openRecentSavedSearch() {
        getOpenRecentSavedSearchButton().click();
    }
}
