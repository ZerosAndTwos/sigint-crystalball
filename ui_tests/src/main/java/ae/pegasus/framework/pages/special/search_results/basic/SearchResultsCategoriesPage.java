package ae.pegasus.framework.pages.special.search_results.basic;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.special.search_results.SearchResultsEntity;
import ae.pegasus.framework.constants.special.search_results.SearchResultsEvent;
import ae.pegasus.framework.constants.special.search_results.SearchResultsRepresentation;

import static com.codeborne.selenide.Selenide.$x;
import static ae.pegasus.framework.conditions.ContinuouslyConditions.enabledFor;

public class SearchResultsCategoriesPage extends SearchResultsRepresentationsPage {

    private SelenideElement getSearchResultsCategoryTabByDisplayName(String categoryDisplayName) {
        for (SelenideElement item : getSearchResultsTabsContainer().$$x("./div")) {
            if (extractTabName(item).equalsIgnoreCase(categoryDisplayName)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Category '" + categoryDisplayName + "' was not found");
    }

    private SelenideElement getSearchResultsCategoryTab(SearchResultsEvent searchResultsCategory) {
        openSearchResultsRepresentation(SearchResultsRepresentation.EVENT);
        return getSearchResultsCategoryTabByDisplayName(searchResultsCategory.getDisplayName());
    }

    private SelenideElement getSearchResultsCategoryTab(SearchResultsEntity searchResultsCategory) {
        openSearchResultsRepresentation(SearchResultsRepresentation.ENTITY);
        return getSearchResultsCategoryTabByDisplayName(searchResultsCategory.getDisplayName());
    }

    private SelenideElement getSearchResultWithoutCategory() {
        return $x("//pg-row[2]//pg-col[1]//div/span[contains(@class, 'text-muted')]");
    }

    private String extractTabName(SelenideElement tab) {
        return tab.getText().replace(getNumberOfSearchResultAsString(tab), "").trim();
    }

    private String getSearchResultsNumberWithoutCategory() {
        SelenideElement searchResults = getSearchResultWithoutCategory();
        if (searchResults.isDisplayed()) {
            String result = searchResults.getText();
            return result;
        }
        return "";
    }

    private int convertTextToInt(String text) {
        if (text.isEmpty()) {
            return 0;
        }

        return Integer.valueOf(text
                .replace("M+", "000000")
                .replace("k+", "000")
                .replace(",", ""));
    }

    private void selectTab(SelenideElement tab) {
        if (!tab.getAttribute("class").contains("pg-nav-tabs__tab--active")) {
            tab.shouldBe(enabledFor(100)).click();
            waitForPageLoading();
        }
    }

    private String getNumberOfSearchResultAsString(SelenideElement searchResultsCategory) {
        SelenideElement resultsNumber = searchResultsCategory.$x("./span");
        if (resultsNumber.isDisplayed()) {
            return resultsNumber.getText();
        }
        return "";
    }

    public String getNumberOfResultsAsString(SearchResultsEvent searchResultsCategory) {
        return getNumberOfSearchResultAsString(getSearchResultsCategoryTab(searchResultsCategory));
    }

    public String getNumberOfResultsAsString(SearchResultsEntity searchResultsCategory) {
        return getNumberOfSearchResultAsString(getSearchResultsCategoryTab(searchResultsCategory));
    }

    public String getNumberOfResultsAsString() {
        return getSearchResultsNumberWithoutCategory();
    }

    public int getNumberOfResultsAsInt(SearchResultsEvent searchResultsCategory) {
        return convertTextToInt(getNumberOfResultsAsString(searchResultsCategory));
    }

    public int getNumberOfResultsAsInt(SearchResultsEntity searchResultsCategory) {
        return convertTextToInt(getNumberOfResultsAsString(searchResultsCategory));
    }

    public int getNumberOfResultsAsInt() {
        return convertTextToInt(getNumberOfResultsAsString());
    }

    public void openSearchResultsForCategory(SearchResultsEvent searchResultsCategory) {
        selectTab(getSearchResultsCategoryTab(searchResultsCategory));
    }

    public void openSearchResultsForCategory(SearchResultsEntity searchResultsCategory) {
        selectTab(getSearchResultsCategoryTab(searchResultsCategory));
    }
}
