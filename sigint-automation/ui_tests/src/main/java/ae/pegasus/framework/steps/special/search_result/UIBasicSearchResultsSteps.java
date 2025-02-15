package ae.pegasus.framework.steps.special.search_result;

import ae.pegasus.framework.assertion.Asserter;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.special.search_results.SearchResultsView.*;

public class UIBasicSearchResultsSteps {
    @Then("I should not see search result")
    public void iShouldNotSeeSearchResults() {
        Asserter.getAsserter().softAssertFalse(Pages.searchResultsPage().areSearchResultsDisplayed(),
                "Search result are not displayed",
                "Search results are DISPLAYED");
    }

    @Then("I should see search result")
    public void iShouldSeeSearchResults() {
        Asserter.getAsserter().softAssertTrue(Pages.searchResultsPage().areSearchResultsDisplayed(),
                "Search result are displayed",
                "Search results are NOT displayed");
    }

    @When("I open Card View")
    public void iOpenCardView() {
        Pages.searchResultsPage().openSearchResultsView(CARD_VIEW);
    }

    @When("I open Grid View")
    public void iOpenGridView() {
        Pages.searchResultsPage().openSearchResultsView(GRID_VIEW);
    }

    @When("I open Map View")
    public void iOpenMapView() {
        Pages.searchResultsPage().openSearchResultsView(MAP_VIEW);
    }
}
