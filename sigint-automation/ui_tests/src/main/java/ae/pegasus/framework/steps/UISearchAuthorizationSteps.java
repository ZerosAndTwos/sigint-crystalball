package ae.pegasus.framework.steps;

import ae.pegasus.framework.app_context.properties.G4Properties;
import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.pages.Pages;
import com.codeborne.selenide.WebDriverRunner;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.openqa.selenium.logging.LogType;

import java.util.logging.Level;

public class UISearchAuthorizationSteps {
    @Given("I setup Search Authorization")
    public void iSetupSearchAuthorization() {
        Pages.searchAuthorizationPage().enterDataForSearchAuthorization();
    }

    @Then("I should see Cancel and Continue buttons on Search Authorization page")
    public void iSeeCancelButtonOnSearchAuthorization() {
        Asserter.getAsserter().softAssertTrue(Pages.searchAuthorizationPage().isCancelButtonDisplayed(),
                "Cancel and Continue buttons are displayed on Search Authorization page",
                "Cancel and Continue buttons are not displayed on Search Authorization page");
    }


    @Given("I setup Search Authorization for failed query")
    public void iSetupSearchAuthorizationfailedquery() {
        Pages.searchAuthorizationPage().enterDataForSearchAuthorization();
        if(G4Properties.getRunProperties().isSuppressKnownIssues()) {
            WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER).filter(Level.SEVERE);
        }
    }
}
