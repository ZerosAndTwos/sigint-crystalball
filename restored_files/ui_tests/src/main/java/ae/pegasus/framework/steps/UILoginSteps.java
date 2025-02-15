package ae.pegasus.framework.steps;

import ae.pegasus.framework.blocks.context.Context;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.SigintPage;
import com.codeborne.selenide.WebDriverRunner;
import ae.pegasus.framework.controllers.APILogin;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.sleep;

public class UILoginSteps extends UISteps {

    private void login(String username, String password) {
        Pages.loginPage().getUsernameField().val(username);
        Pages.loginPage().getPasswordField().val(password);
        Pages.loginPage().clickSignInButton();
    }


    @Given("I as <role> try sign in with incorrect credentials")
    public void userAsRoleSignInWithIncorrectCredentials(@Named("role") String role) {
        user = APILogin.getUserByRole(role);
        Pages.loginPage().load();

        login(
                user.getName(),
                user.getPassword() + "qwe"
        );
    }


    @Then("I should see $message error")
    public void userDoesNotSignIn(String message) {
        Pages.loginPage().getErrorMessage().shouldHave(text(message));
    }


    private static void WaitForReady() {
        WebDriver driver = WebDriverRunner.getWebDriver();

        new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
        });
    }


    @When("I sign out")
    public void userSignOut() {
        WaitForReady();
        sleep(5000); //TODO
        new SigintPage() {
            @Override
            public Context context() {
                return null;
            }
        }.getHeader().clickUserProfile().clickSignOut();

    }


    @Then("I should see Login page")
    public void userSeeLoginPage() {
        Pages.loginPage().getFormSignin().shouldBe(appear);
    }


    @Given("I logged in as $role")
    @Then("I logged in as $role")
    public void iLoggedInAsOperator(@Named("role") String role) {
        // Arrange
        user = APILogin.getUserByRole(role);
        Pages.loginPage().load();

        // Act
        login(
                user.getName(),
                user.getPassword()
        );

        // Assert
        context.put("user", user);
    }
}
