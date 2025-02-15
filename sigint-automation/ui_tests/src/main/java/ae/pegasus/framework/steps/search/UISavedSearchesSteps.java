package ae.pegasus.framework.steps.search;

import ae.pegasus.framework.assertion.Asserter;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import ae.pegasus.framework.pages.Pages;

public class UISavedSearchesSteps {
    @Then("I should see Saved Searches page")
    public void iShouldSeeSavedSearchesPage() {
        Asserter.getAsserter().softAssertTrue(Pages.savedSearchesPage().isPageDisplayed(),
                "Saved Searches page is displayed",
                "Saved Searches page is NOT displayed");
    }

    @When("I enter search criteria ($searchCriteria) on the Saved Searches page")
    public void iEnterSearchCriteria(String searchCriteria) {
        Pages.savedSearchesPage().enterSearchCriteria(searchCriteria);
    }

    @When("I start search on the the Saved Searches page")
    public void iStartSearch() {
        Pages.savedSearchesPage().startSearch();
    }

    @Then("I should see search criteria ($expectedSearchCriteria) on the Saved Searches page")
    public void iShouldSeeSearchCriteria(String expectedSearchCriteria) {
        Asserter.getAsserter().softAssertEquals(Pages.savedSearchesPage().getCurrentSearchCriteria(),
                expectedSearchCriteria,
                "Search criteria on the Saved Searches page");
    }



    @Then("I should see $number card(s) on the Saved Searches page")
    public void iShouldSeeNumberOfCards(int number) {
        Asserter.getAsserter().softAssertEquals(
                Pages.savedSearchesPage().getCardsNumber(),
                number,
                "Number of cards displayed on the Saved Searches page");
    }


    @Then("I should see at least $number save search search result(s) on the Saved Searches page")
    public void iShouldSeeAtleastNumberOfCards(int number) {

        Asserter.getAsserter().softAssertTrue(Pages.savedSearchesPage().getCardsNumber() >= number,
                "Number of save search results at least " + number + " on save search screen",
                "Actual number of search results " + Pages.savedSearchesPage().getCardsNumber() + " which is less than expected " + number + "on save search screen");
    }


    @Given("I open for edit 1-st card displayed on the Saved Searches page")
    public void iOpen1stCardForEdit() {
        Pages.savedSearchesPage().edit1stCard();
    }

    @Given("I perform run of 1-st card displayed on the Saved Searches page")
    public void iRun1stCard() {
        Pages.savedSearchesPage().run1stCard();
        Pages.searchPage().waitForPageLoading();
    }

    @Given("I delete all cards displayed on the Saved Searches page")
    public void iDeleteAllCards() {
        Pages.savedSearchesPage().deleteAllCards();
    }
}
