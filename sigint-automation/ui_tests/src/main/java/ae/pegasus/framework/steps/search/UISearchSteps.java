package ae.pegasus.framework.steps.search;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField;
import ae.pegasus.framework.context.Context;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField.*;

public class UISearchSteps {

    @Then("I should see Search page")
    public void iShouldSeeSearchPage() {
        Asserter.getAsserter().softAssertTrue(Pages.searchPage().isPageDisplayed(),
                "Search page is displayed",
                "Search page is NOT displayed");
    }

    @When("I enter search criteria ($searchCriteria) on the Search page")
    public void iEnterSearchCriteria(String searchCriteria) {
        Pages.searchPage().enterSearchCriteria(searchCriteria);
    }

    private void enterSearchCriteriaAsFieldOfRecord(String recordID, CreatedRecordField field) {
        String fieldValue = (String) Context.getContext().getCreatedRecord(recordID).getRecordFieldValue(field);
        switch (field) {
            case TO_NUMBER:
            case FROM_NUMBER:
                fieldValue = fieldValue.replace("+", "");
                break;
        }
        Pages.searchPage().enterSearchCriteria(fieldValue);
    }

    @When("I use To Number from manually created record identified as ($recordID) as search criteria on the Search page")
    public void iUseToNumberFromRecord(String recordID) {
        enterSearchCriteriaAsFieldOfRecord(recordID, TO_NUMBER);
    }

    @When("I use From Number from manually created record identified as ($recordID) as search criteria on the Search page")
    public void iUseFromNumberFromRecord(String recordID) {
        enterSearchCriteriaAsFieldOfRecord(recordID, FROM_NUMBER);
    }

    @When("I use To E-mail from manually created record identified as ($recordID) as search criteria on the Search page")
    public void iUseToEmailFromRecord(String recordID) {
        enterSearchCriteriaAsFieldOfRecord(recordID, TO_EMAIL);
    }

    @When("I use From E-mail from manually created record identified as ($recordID) as search criteria on the Search page")
    public void iUseFromEmailFromRecord(String recordID) {
        enterSearchCriteriaAsFieldOfRecord(recordID, FROM_EMAIL);
    }

    @When("I use IMSI from manually created record identified as ($recordID) as search criteria on the Search page")
    public void iUseIMSIFromRecord(String recordID) {
        enterSearchCriteriaAsFieldOfRecord(recordID, IMSI);
    }

    @When("I start search on the Search page")
    public void iStartSearch() {
        Pages.searchPage().startSearch();
    }

    @Given("I reset search on the Search page")
    public void iResetSearch() {
        Pages.searchPage().resetSearch();
    }



    @Then("I should see search criteria ($expectedSearchCriteria) on the Search page")
    public void iShouldSeeSearchCriteria(String expectedSearchCriteria) {
        Asserter.getAsserter().softAssertEquals(Pages.searchPage().getCurrentSearchCriteria(),
                expectedSearchCriteria,
                "Search criteria on the Search page");
    }

    @Given("I open Search Filter on the Search page")
    public void iOpenSearchFilter() {
        Pages.searchPage().openSearchFilter();

    }

    @Given("I Apply Search using Search Filter on the Search page")
    public void iApplySearchFilter() {
        Pages.searchFilterPage().applySearch();
        Pages.searchPage().waitForPageLoading();
    }

    @Given("I press Save Search button on the Search Page")
    public void iPressSaveSearchPage() {
        Pages.searchPage().pressSaveSearch();
    }

    @Given("I press Update Saved Search button on the Search Page")
    public void iPressUpdateSavedSearchPage() {
        Pages.searchPage().pressUpdateSavedSearch();
    }
}
