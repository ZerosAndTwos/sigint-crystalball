package ae.pegasus.framework.steps.search;

import ae.pegasus.framework.assertion.Asserter;
import org.jbehave.core.annotations.Then;
import ae.pegasus.framework.pages.Pages;

public class UIGeoSearchSteps {
    @Then("I should see Geo Search page")
    public void iShouldSeeGeoSearchPage() {
        Asserter.getAsserter().softAssertTrue(Pages.geoSearchPage().isPageDisplayed(),
                "Geo Search page is displayed",
                "Geo Search page is NOT displayed");
    }
}
