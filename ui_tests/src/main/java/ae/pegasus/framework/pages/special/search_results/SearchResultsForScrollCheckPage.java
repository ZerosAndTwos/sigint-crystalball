package ae.pegasus.framework.pages.special.search_results;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.conditions.PositionConditions;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.$$x;

public class SearchResultsForScrollCheckPage extends SearchResultsPage {
    public boolean isHorizontalScrollPresentOnCurrentView() {
        switch (determineCurrentSearchResultsView()) {
            case GRID_VIEW:
                return checkPageScrollableToElement(GRID_TABLE_HEADER_XPATH);
            default:
                return false;
        }
    }

    public boolean isVerticalScrollPresentOnTheView() {
        switch (determineCurrentSearchResultsView()) {
            case CARD_VIEW:
                return checkPageScrollableToElement(CARD_XPATH);
            case GRID_VIEW:
                return checkPageScrollableToElement(GRID_ROW_XPATH);
            default:
                return false;
        }
    }

    private boolean checkPageScrollableToElement(String pathToElements) {
        boolean result = false;
        Selenide.executeJavaScript("window.scrollBy(0,0);");
        //TODO Uncomment in case of problem with "Load more" button appearance and faked scroll unavailability as result
//        if(getLoadMoreButton().is(PositionConditions.inViewPort)) {
//            getLoadMoreButton().click();
//        }
        waitForPageLoading();
        ElementsCollection elementsList = $$x(pathToElements);
        for (int i = elementsList.size()-1; i >= 0; i--) {
            SelenideElement element = elementsList.get(i);
            if (element.is(not(PositionConditions.inViewPort))) {
                scrollWindowTo(element);
                result = element.is(PositionConditions.inViewPort);
                Selenide.executeJavaScript("window.scrollBy(0,0);");
                break;
            }
        }
        return result;
    }

}
