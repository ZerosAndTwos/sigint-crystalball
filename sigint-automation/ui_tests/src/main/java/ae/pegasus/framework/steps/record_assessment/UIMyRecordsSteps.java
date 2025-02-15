package ae.pegasus.framework.steps.record_assessment;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField;
import ae.pegasus.framework.context.Context;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField.FROM_NUMBER;
import static ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField.IMSI;
import static ae.pegasus.framework.constants.special.create_event_record.CreatedRecordField.TO_EMAIL;

public class UIMyRecordsSteps {


    private void enterSearchCriteriaAsFieldOfRecord(String recordID, CreatedRecordField field) {
        String fieldValue = (String) Context.getContext().getCreatedRecord(recordID).getRecordFieldValue(field);
        switch (field) {
            case TO_NUMBER:
            case FROM_NUMBER:
                fieldValue = fieldValue.replace("+", "");
                break;
        }
        Pages.myRecordsPage().enterSearchCriteria(fieldValue);

    }

    @Then("I should see My Records page")
    public void iShouldSeeSearchPage() {
        Asserter.getAsserter().softAssertTrue(Pages.myRecordsPage().isPageDisplayed(),
                "My Records page is displayed",
                "My Records page is NOT displayed");
    }

    @When("I enter search criteria ($searchCriteria) on the My Records page")
    public void iEnterSearchCriteria(String searchCriteria) {
        Pages.myRecordsPage().enterSearchCriteria(searchCriteria);
    }



    @When("I use IMSI from manually created record identified as ($recordID) as search criteria on the Record page")
    public void iUseIMSIFromRecord(String recordID) {
        enterSearchCriteriaAsFieldOfRecord(recordID, IMSI);
    }

    @When("I use From Number from manually created record identified as ($recordID) as search criteria on the Record page")
    public void iUseFromNumberFromRecord(String recordID) {
        enterSearchCriteriaAsFieldOfRecord(recordID, FROM_NUMBER);
    }

    @When("I use To E-mail from manually created record identified as ($recordID) as search criteria on the Record page")
    public void iUseToEmailFromRecord(String recordID) {
        enterSearchCriteriaAsFieldOfRecord(recordID, TO_EMAIL);
    }



    @When("I start search on My Records page")
    public void iStartSearch() {
        Pages.myRecordsPage().startSearch();
    }

    @Then("I should see search criteria ($expectedSearchCriteria) on the My Records page")
    public void iShouldSeeSearchCriteria(String expectedSearchCriteria) {
        Asserter.getAsserter().softAssertEquals(Pages.myRecordsPage().getCurrentSearchCriteria(),
                expectedSearchCriteria,
                "Search criteria on the My Records page");
    }

    @Given("I open Search Filter on the My Records page")
    public void iOpenSearchFilter() {
        Pages.myRecordsPage().openSearchFilter();
    }

    @Given("I Apply Search using Search Filter on the My Records page")
    public void iApplySearch() {
        Pages.searchFilterPage().applySearch();
        Pages.myRecordsPage().waitForPageLoading();
    }

    @Given("I start creation of new record identified as ($recordID) on the My Records page")
    public void iStartNewRecordCreation(String recordID) {
        Pages.myRecordsPage().startNewRecordCreation();
        Context.getContext().startNewRecordCreation(recordID);
    }
}
