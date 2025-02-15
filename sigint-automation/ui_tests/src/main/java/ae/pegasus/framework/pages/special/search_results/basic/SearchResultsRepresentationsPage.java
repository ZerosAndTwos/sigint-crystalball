package ae.pegasus.framework.pages.special.search_results.basic;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.special.search_results.SearchResultsRepresentation;

import static com.codeborne.selenide.Condition.enabled;

public class SearchResultsRepresentationsPage extends SearchResultViewsPage {

    private ElementsCollection getSearchResultsRepresentations() {
        return getSearchResultsRepresentationSwitcher().$$x(".//label");
    }

    public SearchResultsRepresentation determineCurrentSearchResultsRepresentation() {
        for (SelenideElement item : getSearchResultsRepresentations()) {
            if (isElementSelected(item)) {
                return SearchResultsRepresentation.getEnumByName(item.$x("./span").getText());
            }
        }
        throw new IllegalStateException("Unable to determine current search results representation");
    }

    public void openSearchResultsRepresentation(SearchResultsRepresentation representation) {
        for(SelenideElement item : getSearchResultsRepresentations()) {
            if(item.$x("./span").getText().trim().equalsIgnoreCase(representation.getDisplayName())) {
                if (!isElementSelected(item)) {
                    item.shouldBe(enabled).click();
                    waitForPageLoading();
                }
                return;
            }
        }
        throw new IllegalArgumentException("Representation '" + representation.getDisplayName() + "' was not found");
    }
}
