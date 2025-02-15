package ae.pegasus.framework.pages.signin;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.app_context.properties.G4Properties;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.logging.LogType;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.basic_pages.api.BasePage;

import java.util.logging.Level;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$x;

public class SignInPage extends BasePage {

    private final String url;

    public SignInPage() {
        super();
        url = String.format("%s/#/login", G4Properties.getRunProperties().getApplicationURL());
    }

    @Override
    public String getPageTitle() {
        return "Sign In Page";
    }

    @Override
    public boolean isPageDisplayed() {
        return false;
    }

    @Override
    protected SelenideElement getPageLoading() {
        return $x("//div[@class='page-loading']");
    }

    public SelenideElement getUsernameField() {
        return getSignInForm().$x(".//input[@id='inputEmail']").shouldBe(exist);
    }

    public SelenideElement getPasswordField() {
        return getSignInForm().$x(".//input[@id='inputPassword']").shouldBe(exist);
    }

    public SelenideElement getSignInButton() {
        return getSignInForm().$x(".//button[contains(text(), 'Sign in')]");
    }

    public SelenideElement getSignInForm() {
        return $x("//form[contains(@class, 'form-signin')]");
    }

    /**
     * Load Login page
     *
     * @return pageObject
     */
    public SignInPage load() {
        System.out.println(url);
        Selenide.open(url);
        getPageLoading().waitUntil(hidden, G4Properties.getRunProperties().getLongTimeoutInMS());
        String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        Asserter.getAsserter().softAssertEquals(currentURL, url, "Current Login page url");
        if (G4Properties.getRunProperties().isSuppressKnownIssues()) {
            WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER).filter(Level.SEVERE);
        }
        return this;
    }


    public SignInPage typeUsername(String username) {
        getUsernameField().val(username);
        return this;
    }

    public SignInPage typePassword(String password) {
        getPasswordField().val(password);
        return this;
    }

    public void clickSignInButton() {
        getSignInButton().click();
        Pages.navigation().resetNavigation();
    }
}
