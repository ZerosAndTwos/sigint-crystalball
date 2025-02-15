package ae.pegasus.framework.steps.special.search_result.verify_view;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.special.search_results.SearchResultsEntity;
import ae.pegasus.framework.constants.special.search_results.SearchResultsEvent;
import ae.pegasus.framework.constants.special.search_results.SearchResultsView;
import org.jbehave.core.annotations.Then;
import ae.pegasus.framework.pages.Pages;

public class UIVerifyResultsNumberSteps {

    private void checkExactNumberOfSearchResults(SearchResultsEvent searchResults, int exactNumber) {
        SearchResultsView currentView = Pages.searchResultsPage().determineCurrentSearchResultsView();
        int actualResults = Pages.searchResultsPage().getNumberOfResultsAsInt(searchResults);
        Asserter.getAsserter().softAssertEquals(actualResults,
                exactNumber,
                "Number of " + searchResults.getDisplayName() + " event(s) on " + currentView.getDisplayName());
    }

    @Then("I should see $number SIGINT event(s) on current view")
    public void iShouldSeeExactNumberOfSIGINTEvents(int number) {
        checkExactNumberOfSearchResults(SearchResultsEvent.SIGINT, number);
    }

    @Then("I should see at least $number search result(s) on the current view")
    public void iShouldSeeSearchResultsNumber(int number) {
        SearchResultsView currentView = Pages.searchResultsPage().determineCurrentSearchResultsView();
        int actualResults = Pages.searchResultsPage().getNumberOfResultsAsInt();
        Asserter.getAsserter().softAssertTrue(actualResults >= number,
                "Number of search results at least " + number + " on " + currentView.getDisplayName(),
                "Actual number of search results " + actualResults + " less than expected " + number + " on " + currentView.getDisplayName());
    }

    @Then("I should see $number search result(s) on the current view")
    public void iShouldSeeExactSearchResultsNumber(int number) {
        SearchResultsView currentView = Pages.searchResultsPage().determineCurrentSearchResultsView();
        int actualResults = Pages.searchResultsPage().getNumberOfResultsAsInt();
        Asserter.getAsserter().softAssertEquals(actualResults,
                number,
                "Number of search results on " + currentView.getDisplayName());
    }

    private void checkNumberOfSearchResults(SearchResultsEvent searchResults, int minNumber) {
        SearchResultsView currentView = Pages.searchResultsPage().determineCurrentSearchResultsView();
        int actualResults = Pages.searchResultsPage().getNumberOfResultsAsInt(searchResults);
        Asserter.getAsserter().softAssertTrue(actualResults >= minNumber,
                "Number of " + searchResults.getDisplayName() +" events at least " + minNumber + " on " + currentView.getDisplayName(),
                "Actual number of " + searchResults.getDisplayName() + " events " + actualResults + " less than expected " + minNumber + " on " + currentView.getDisplayName());
    }

    @Then("I should see at least $number of SIGINT events on current view")
    public void iShouldSeeSIGINTEvents(int number) {
        checkNumberOfSearchResults(SearchResultsEvent.SIGINT, number);
    }

    @Then("I should see at least $number of EID events on current view")
    public void iShouldSeeEIDEvents(int number) {
        checkNumberOfSearchResults(SearchResultsEvent.EID, number);
    }

    @Then("I should see at least $number of GOVINT events on current view")
    public void iShouldSeeGOVINTEvents(int number) {
        checkNumberOfSearchResults(SearchResultsEvent.GOVINT, number);
    }

    @Then("I should see at least $number of OSINT events on current view")
    public void iShouldSeeOSINTEvents(int number) {
        checkNumberOfSearchResults(SearchResultsEvent.OSINT, number);
    }

    @Then("I should see at least $number of CIO events on current view")
    public void iShouldSeeCIOEvents(int number) {
        checkNumberOfSearchResults(SearchResultsEvent.CIO, number);
    }

    @Then("I should see at least $number of FININT events on current view")
    public void iShouldSeeFININTEvents(int number) {
        checkNumberOfSearchResults(SearchResultsEvent.FININT, number);
    }

    @Then("I should see at least $number of TRAFFIC events on current view")
    public void iShouldSeeTRAFFICEvents(int number) {
        checkNumberOfSearchResults(SearchResultsEvent.TRAFFIC, number);
    }

    private void checkNumberOfSearchResults(SearchResultsEntity searchResults, int minNumber) {
        SearchResultsView currentView = Pages.searchResultsPage().determineCurrentSearchResultsView();
        int actualResults = Pages.searchResultsPage().getNumberOfResultsAsInt(searchResults);
        Asserter.getAsserter().softAssertTrue(actualResults >= minNumber,
                "Number of " + searchResults.getDisplayName() +" entities at least " + minNumber + " on " + currentView.getDisplayName(),
                "Actual number of " + searchResults.getDisplayName() + " entities " + actualResults + " less than expected " + minNumber + " on " + currentView.getDisplayName());
    }

    @Then("I should see at least $number of Profiler entities on current view")
    public void iShouldSeeProfilerEntities(int number) {
        checkNumberOfSearchResults(SearchResultsEntity.PROFILER, number);
    }

    @Then("I should see at least $number of Documents entities on current view")
    public void iShouldSeeDocumentsEntities(int number) {
        checkNumberOfSearchResults(SearchResultsEntity.DOCUMENTS, number);
    }

    @Then("I should see at least $number of SIGINT entities on current view")
    public void iShouldSeeSIGINTEntities(int number) {
        checkNumberOfSearchResults(SearchResultsEntity.SIGINT, number);
    }

    @Then("I should see at least $number of EID entities on current view")
    public void iShouldSeeEIDEntities(int number) {
        checkNumberOfSearchResults(SearchResultsEntity.EID, number);
    }

    @Then("I should see at least $number of GOVINT entities on current view")
    public void iShouldSeeGOVINTEntities(int number) {
        checkNumberOfSearchResults(SearchResultsEntity.GOVINT, number);
    }

    @Then("I should see at least $number of OSINT entities on current view")
    public void iShouldSeeOSINTEntities(int number) {
        checkNumberOfSearchResults(SearchResultsEntity.OSINT, number);
    }

    @Then("I should see at least $number of CIO entities on current view")
    public void iShouldSeeCIOEntities(int number) {
        checkNumberOfSearchResults(SearchResultsEntity.CIO, number);
    }

}
