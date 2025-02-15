package ae.pegasus.framework.pages.special.search_results.basic;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.special.search_results.SearchResultsView;

import static ae.pegasus.framework.conditions.ContinuouslyConditions.enabledFor;

public class SearchResultViewsPage extends BasicSearchResultsPage {
    private SelenideElement getSearchResultsSortByContainer() {
        return getSearchResultsSetupContainer().$x(".//div[@class='m-r-md']");
    }

    public void openSearchResultsView(SearchResultsView view) {
        for (SelenideElement item : getSearchResultsViews()) {
            if (getSearchResultsViewName(item).equalsIgnoreCase(view.getDisplayName())) {
                if (!isElementSelected(item)) {
                    item.shouldBe(enabledFor(100)).click();
                    waitForPageLoading();
                }
                return;
            }
        }
        throw new IllegalArgumentException("View '" + view.getDisplayName() + "' was not found");
    }

    private String getSearchResultsViewName(SelenideElement view) {
        return view.$("input").getAttribute("model").trim();
    }

    private ElementsCollection getSearchResultsViews() {
        return getSearchResultsViewSwitcher().$$x(".//label");
    }

    public SearchResultsView determineCurrentSearchResultsView() {
        for (SelenideElement item : getSearchResultsViews()) {
            if (isElementSelected(item)) {
                return SearchResultsView.getEnumByName(getSearchResultsViewName(item));
            }
        }
        throw new IllegalStateException("Unable to determine current search results view");
    }
}
