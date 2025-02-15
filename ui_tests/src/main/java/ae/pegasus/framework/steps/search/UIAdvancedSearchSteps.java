package ae.pegasus.framework.steps.search;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.pages.Pages;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class UIAdvancedSearchSteps {
    @Then("I should see Advanced Search page")
    public void iShouldSeeAdvancedSearchPage() {
        Asserter.getAsserter().softAssertTrue(Pages.advancedSearchPage().isPageDisplayed(),
                "Advanced Search page is displayed",
                "Advanced Search page is NOT displayed");
    }

    @When("I enter search criteria ($searchCriteria) on the Advance Search Page")
    public void iEnterSearchCriteria(String searchCriteria) {
        Pages.advancedSearchPage().enterAdvanceSearchCriteria(searchCriteria);
    }
    @When("I start search on the Advance Search page")
    public void iStartSearch() {
        Pages.advancedSearchPage().startAdvanceSearch();

    }

    @Then("I build search query on the Advance Search page")
    public void iBuildQueryOnAdvanceSearch() {
        Pages.advancedSearchPage().clickBuildAdvanceQuery();
    }

}
