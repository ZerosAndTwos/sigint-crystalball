package ae.pegasus.framework.steps;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.app_context.properties.G4Properties;
import com.codeborne.selenide.Selenide;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import ae.pegasus.framework.pages.Pages;

public class UIMainPageSteps {

    @Then("I should see Confirm or Logout dialog")
    public void iSeeConFirmOrLogoutDialog() {
        String actualDialogHeaderText = Pages.mainPage().getConfirmOrLogoutDialogHeader().getText();
        Asserter.getAsserter().softAssertTrue(actualDialogHeaderText.equals("Confirm or Logout"),
                "Confirm or Logout dialog is displayed",
                "Confirm or Logout dialog is NOT displayed");
    }

    @Given("I push Accept button in the Confirm or Logout dialog")
    public void iPushAcceptButtonInConfirmOrLogoutDialog(){
        Pages.mainPage().getAcceptButton().click();
    }

    @Given("I open Queues page")
    public void iOpenQueuesPage(){
        Pages.mainPage().openQueuesPage();
    }

    @Given("I close Queues page")
    public void iCloseQueuesPage(){
        Pages.mainPage().closeQueuesPage();
    }

    @Given("I open Alerting History page")
    public void iOpenAlertHistoryPage() {
        Pages.mainPage().openAlertHistoryPage();
    }

    @Given("I wait until search index is updated")
    public void iWaitUntilSearchIndexUpdate() {
        Selenide.sleep(G4Properties.getRunProperties().getSearchIndexPeriodInMS());
    }

    @Given("I Sign Out")
    public void iSignOut (){
        Pages.mainPage().signOut();
    }

}
