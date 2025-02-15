package ae.pegasus.framework.steps;

import ae.pegasus.framework.app_context.properties.G4Properties;
import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.pages.Pages;
import com.codeborne.selenide.WebDriverRunner;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.logging.LogType;

import java.util.logging.Level;

import static com.codeborne.selenide.Condition.appear;

public class UISignInSteps {

    @Given("I open Sign In page")
    public void openLoginPage() {
        Pages.signInPage().load();
    }

    @Then("I should see Sign In page")
    public void userSeeLoginPage() {
        Pages.signInPage().getSignInForm().shouldBe(appear);
    }

    @Given("I enter login $login")
    public void iEnterLogin(String login) {
        Pages.signInPage().typeUsername(login);
        if(G4Properties.getRunProperties().isSuppressKnownIssues()) {
            WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER).filter(Level.SEVERE);
        }
    }

    @Given("I enter password $password")
    public void iEnterPassword(String password) {
        Pages.signInPage().typePassword(password);
    }

    @When("I push Sign In button")
    public void iPushSignInButton() {
        Pages.signInPage().clickSignInButton();
    }

    @Then("I should see enabled Sign In button")
    public void iShouldSeeEnabledLoginButton() {
        Asserter.getAsserter().softAssertTrue(
                Pages.signInPage().getSignInButton().isEnabled(),
                "Login button is enabled",
                "Login button is disabled"
        );
    }
}
