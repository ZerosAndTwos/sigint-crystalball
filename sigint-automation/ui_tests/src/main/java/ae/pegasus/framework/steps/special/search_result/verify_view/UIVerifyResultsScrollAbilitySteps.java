package ae.pegasus.framework.steps.special.search_result.verify_view;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.special.search_results.SearchResultsEntity;
import ae.pegasus.framework.constants.special.search_results.SearchResultsEvent;
import ae.pegasus.framework.constants.special.search_results.SearchResultsView;
import org.jbehave.core.annotations.Then;
import ae.pegasus.framework.pages.Pages;

public class UIVerifyResultsScrollAbilitySteps {

    private void checkPresenceVerticalScrollOfSearchResults(SearchResultsEvent searchResults) {
        SearchResultsView currentView = Pages.searchResultsPage().determineCurrentSearchResultsView();
        Pages.searchResultsForScrollCheckPage().openSearchResultsForCategory(searchResults);
        Asserter.getAsserter().softAssertTrue(Pages.searchResultsForScrollCheckPage().isVerticalScrollPresentOnTheView(),
                "Vertical scroll for " + searchResults.getDisplayName() +" events present on " + currentView.getDisplayName(),
                "Vertical scroll for " + searchResults.getDisplayName() +" events absent on " + currentView.getDisplayName());
    }

    @Then("I should be able to scroll SIGINT events vertically on current view")
    public void iShouldBeAbleToScrollSIGINTEventsVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEvent.SIGINT);
    }

    @Then("I should be able to scroll EID events vertically on current view")
    public void iShouldBeAbleToScrollEIDEventsVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEvent.EID);
    }

    @Then("I should be able to scroll GOVINT events vertically on current view")
    public void iShouldBeAbleToScrollGOVINTEventsVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEvent.GOVINT);
    }

    @Then("I should be able to scroll OSINT events vertically on current view")
    public void iShouldBeAbleToScrollOSINTEventsVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEvent.OSINT);
    }

    @Then("I should be able to scroll CIO events vertically on current view")
    public void iShouldBeAbleToScrollCIOEventsVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEvent.CIO);
    }
    @Then("I should be able to scroll FININT events vertically on current view")
    public void iShouldBeAbleToScrollFININTEventsVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEvent.FININT);
    }

    @Then("I should be able to scroll TRAFFIC events vertically on current view")
    public void iShouldBeAbleToScrollTRAFFICEventsVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEvent.TRAFFIC);
    }

    private void checkPresenceHorizontalScrollOfSearchResults(SearchResultsEvent searchResults) {
        SearchResultsView currentView = Pages.searchResultsPage().determineCurrentSearchResultsView();
        Pages.searchResultsForScrollCheckPage().openSearchResultsForCategory(searchResults);
        Asserter.getAsserter().softAssertTrue(Pages.searchResultsForScrollCheckPage().isHorizontalScrollPresentOnCurrentView(),
                "Horizontal scroll for " + searchResults.getDisplayName() +" events present on " + currentView.getDisplayName(),
                "Horizontal scroll for " + searchResults.getDisplayName() +" events absent on " + currentView.getDisplayName());
    }

    @Then("I should be able to scroll SIGINT events horizontally on current view")
    public void iShouldBeAbleToScrollSIGINTEventsHorizontallyOnCurrentView() {
        checkPresenceHorizontalScrollOfSearchResults(SearchResultsEvent.SIGINT);
    }

    @Then("I should be able to scroll EID events horizontally on current view")
    public void iShouldBeAbleToScrollEIDEventsHorizontallyOnCurrentView() {
        checkPresenceHorizontalScrollOfSearchResults(SearchResultsEvent.EID);
    }

    @Then("I should be able to scroll GOVINT events horizontally on current view")
    public void iShouldBeAbleToScrollGOVINTEventsHorizontallyOnCurrentView() {
        checkPresenceHorizontalScrollOfSearchResults(SearchResultsEvent.GOVINT);
    }

    @Then("I should be able to scroll OSINT events horizontally on current view")
    public void iShouldBeAbleToScrollOSINTEventsHorizontallyOnCurrentView() {
        checkPresenceHorizontalScrollOfSearchResults(SearchResultsEvent.OSINT);
    }

    @Then("I should be able to scroll CIO events horizontally on current view")
    public void iShouldBeAbleToScrollCIOEventsHorizontallyOnCurrentView() {
        checkPresenceHorizontalScrollOfSearchResults(SearchResultsEvent.CIO);
    }

    @Then("I should be able to scroll FININT events horizontally on current view")
    public void iShouldBeAbleToScrollFININTEventsHorizontallyOnCurrentView() {
        checkPresenceHorizontalScrollOfSearchResults(SearchResultsEvent.FININT);
    }

    @Then("I should be able to scroll TRAFFIC events horizontally on current view")
    public void iShouldBeAbleToScrollTRAFFICEventsHorizontallyOnCurrentView() {
        checkPresenceHorizontalScrollOfSearchResults(SearchResultsEvent.TRAFFIC);
    }

    private void checkPresenceVerticalScrollOfSearchResults(SearchResultsEntity searchResults) {
        SearchResultsView currentView = Pages.searchResultsPage().determineCurrentSearchResultsView();
        Pages.searchResultsForScrollCheckPage().openSearchResultsForCategory(searchResults);
        Asserter.getAsserter().softAssertTrue(Pages.searchResultsForScrollCheckPage().isVerticalScrollPresentOnTheView(),
                "Vertical scroll for " + searchResults.getDisplayName() +" entities present on " + currentView.getDisplayName(),
                "Vertical scroll for " + searchResults.getDisplayName() +" entities absent on " + currentView.getDisplayName());
    }

    @Then("I should be able to scroll Profiler entities vertically on current view")
    public void iShouldBeAbleToScrollProfilerEntitiesVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEntity.PROFILER);
    }

    @Then("I should be able to scroll Documents entities vertically on current view")
    public void iShouldBeAbleToScrollDocumentsEntitiesVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEntity.DOCUMENTS);
    }

    @Then("I should be able to scroll SIGINT entities vertically on current view")
    public void iShouldBeAbleToScrollSIGINTEntitiesVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEntity.SIGINT);
    }

    @Then("I should be able to scroll EID entities vertically on current view")
    public void iShouldBeAbleToScrollEIDEntitiesVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEntity.EID);
    }

    @Then("I should be able to scroll GOVINT entities vertically on current view")
    public void iShouldBeAbleToScrollGOVINTEntitiesVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEntity.GOVINT);
    }

    @Then("I should be able to scroll OSINT entities vertically on current view")
    public void iShouldBeAbleToScrollOSINTEntitiesVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEntity.OSINT);
    }

    @Then("I should be able to scroll CIO entities vertically on current view")
    public void iShouldBeAbleToScrollCIOEntitiesVerticallyOnCurrentView() {
        checkPresenceVerticalScrollOfSearchResults(SearchResultsEntity.CIO);
    }

    private void checkPresenceHorizontalScrollOfSearchResults(SearchResultsEntity searchResults) {
        SearchResultsView currentView = Pages.searchResultsPage().determineCurrentSearchResultsView();
        Pages.searchResultsForScrollCheckPage().openSearchResultsForCategory(searchResults);
        Asserter.getAsserter().softAssertTrue(Pages.searchResultsForScrollCheckPage().isHorizontalScrollPresentOnCurrentView(),
                "Horizontal scroll for " + searchResults.getDisplayName() +" entities present on " + currentView.getDisplayName(),
                "Horizontal scroll for " + searchResults.getDisplayName() +" entities absent on " + currentView.getDisplayName());
    }

    private void checkAbsenceHorizontalScrollOfSearchResults(SearchResultsEntity searchResults) {
        SearchResultsView currentView = Pages.searchResultsPage().determineCurrentSearchResultsView();
        Pages.searchResultsForScrollCheckPage().openSearchResultsForCategory(searchResults);
        Asserter.getAsserter().softAssertFalse(Pages.searchResultsForScrollCheckPage().isHorizontalScrollPresentOnCurrentView(),
                "Horizontal scroll for " + searchResults.getDisplayName() +" entities absent on " + currentView.getDisplayName(),
                "Horizontal scroll for " + searchResults.getDisplayName() +" entities present on " + currentView.getDisplayName());
    }

    @Then("I should not be able to scroll Profiler entities horizontally on current view")
    public void iShouldNotBeAbleToScrollProfilerEntitiesHorizontallyOnCurrentView() {
        checkAbsenceHorizontalScrollOfSearchResults(SearchResultsEntity.PROFILER);
    }

    @Then("I should be able to scroll Documents entities horizontally on current view")
    public void iShouldBeAbleToScrollDocumentsEntitiesHorizontallyOnCurrentView() {
        checkPresenceHorizontalScrollOfSearchResults(SearchResultsEntity.DOCUMENTS);
    }

    @Then("I should be able to scroll SIGINT entities horizontally on current view")
    public void iShouldBeAbleToScrollSIGINTEntitiesHorizontallyOnCurrentView() {
        checkPresenceHorizontalScrollOfSearchResults(SearchResultsEntity.SIGINT);
    }

    @Then("I should be able to scroll EID entities horizontally on current view")
    public void iShouldBeAbleToScrollEIDEntitiesHorizontallyOnCurrentView() {
        checkPresenceHorizontalScrollOfSearchResults(SearchResultsEntity.EID);
    }

    @Then("I should be able to scroll GOVINT entities horizontally on current view")
    public void iShouldBeAbleToScrollGOVINTEntitiesHorizontallyOnCurrentView() {
        checkPresenceHorizontalScrollOfSearchResults(SearchResultsEntity.GOVINT);
    }

    @Then("I should be able to scroll OSINT entities horizontally on current view")
    public void iShouldBeAbleToScrollOSINTEntitiesHorizontallyOnCurrentView() {
        checkPresenceHorizontalScrollOfSearchResults(SearchResultsEntity.OSINT);
    }

    @Then("I should not be able to scroll CIO entities horizontally on current view")
    public void iShouldNotBeAbleToScrollCIOEntitiesHorizontallyOnCurrentView() {
        checkAbsenceHorizontalScrollOfSearchResults(SearchResultsEntity.CIO);
    }
}
