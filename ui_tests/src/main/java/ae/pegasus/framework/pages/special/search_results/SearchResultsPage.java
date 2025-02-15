package ae.pegasus.framework.pages.special.search_results;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.special.search_results.SearchResultsEntity;
import ae.pegasus.framework.constants.special.search_results.SearchResultsEvent;
import org.apache.commons.lang.NotImplementedException;
import ae.pegasus.framework.pages.special.search_results.basic.SearchResultsCategoriesPage;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsPage extends SearchResultsCategoriesPage {
    public boolean areSearchResultsDisplayed() {
        switch (determineCurrentSearchResultsView()) {
            case CARD_VIEW:
                return $x(CARD_XPATH).isDisplayed() ||
                        !(getLoadingFailed().isDisplayed());
            case GRID_VIEW:
                String resultsNumber = "";
                switch(determineCurrentSearchResultsRepresentation()) {
                    case EVENT:
                        resultsNumber = getNumberOfResultsAsString(SearchResultsEvent.GOVINT);
                        break;
                    case ENTITY:
                        resultsNumber = getNumberOfResultsAsString(SearchResultsEntity.GOVINT);
                        break;
                    default:
                        throw new NotImplementedException();
                }
                return $x(GRID_ROW_XPATH).isDisplayed() || !resultsNumber.isEmpty();
            case MAP_VIEW:
                return  !(getLoadingFailed().isDisplayed());
            default:
                throw new NotImplementedException();
        }
    }

    protected SelenideElement getLoadingFailed() {
        return $x("//span[contains(., 'Loading failed')]");
    }

}
