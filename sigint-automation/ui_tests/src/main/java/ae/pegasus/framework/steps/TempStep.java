package ae.pegasus.framework.steps;

import com.codeborne.selenide.Selenide;
import org.jbehave.core.annotations.Then;
import ae.pegasus.framework.pages.basic_pages.api.BasePage;

import static com.codeborne.selenide.Condition.exactTextCaseSensitive;
import static com.codeborne.selenide.Selenide.$x;

public class TempStep extends BasePage {

    // This is created temparoary for checking all page heading once we have
    // page objects for all pages we would use those steps instead of these
    @Then("The page heading should be ($heading)")
    public void thenThePageHeadingShouldBe(String heading) {
        Selenide.sleep(2000);
        $x("//div[@class='pg-header']//ul[@class='pg-breadcrumb']//span").shouldHave(exactTextCaseSensitive(heading));
        System.out.println("this is the main o/p ****************************" + getPageTitle());
    }
    @Then("I sleep for $number second")
    public void sleep(int number) {
        try {
            Thread.sleep((number*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean isPageDisplayed() {
        return false;
    }


}