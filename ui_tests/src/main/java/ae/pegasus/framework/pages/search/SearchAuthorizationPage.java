package ae.pegasus.framework.pages.search;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import ae.pegasus.framework.constants.search.search.SearchAuthorizationField;
import ae.pegasus.framework.pages.basic_pages.api.BasePageWithSearch;

import static com.codeborne.selenide.Selenide.$x;
import static ae.pegasus.framework.constants.CommonXPaths.INTERNAL_LOADING_XPATH;
import static ae.pegasus.framework.constants.search.search.SearchAuthorizationField.JUSTIFICATION;
import static com.codeborne.selenide.Selenide.sleep;

public class SearchAuthorizationPage extends BasePageWithSearch {

    @Override
    public boolean isPageDisplayed() {
        return getDialogHeader().isDisplayed()
                && getDialogHeader().getText().equalsIgnoreCase("Search Authorization");
    }

    public SelenideElement getDialogHeader() {
        return $x("//ux-dialog//h4");
    }

    private SelenideElement getDialogBody() {
        return $x("//ux-dialog//ux-dialog-body");
    }

    private SelenideElement getDialogFooter() {
        return $x("//ux-dialog//ux-dialog-footer");
    }

    private SelenideElement getDialogFieldRootByName(SearchAuthorizationField field) {
        return getDialogBody().$x(".//pg-field[@label='" + field.getDisplayName() + "']");
    }

    private SelenideElement getDialogLoading() {
        return getDialogBody().$x(INTERNAL_LOADING_XPATH);
    }

    protected void waitForDialogLoading() {
        try {
            getDialogLoading().waitUntil(Condition.visible, 500, 10);
            System.out.println("Dialog's loading appears");
        } catch (UIAssertionError e) {
            System.out.println("Dialog's loading does not appear");
            //Do nothing since element can be absent
        }
        getDialogLoading().shouldBe(Condition.hidden);
    }


    private SelenideElement getCancelButton() {
        return getDialogFooter().$x(".//pg-btn[1]");
    }

    private SelenideElement getContinueButton() {
        return getDialogFooter().$x(".//pg-btn[2]");
    }



    public void enterDataForSearchAuthorization() {
        if (isPageDisplayed()) {
            waitForDialogLoading();
            $x("//div[contains(@class, 'cb-file-tree-node__name')]").click();

            getDialogFieldRootByName(JUSTIFICATION).$x(".//textarea").sendKeys("For test");

            getContinueButton().click();
            sleep(2000);

            waitForPageLoading();
        }
    }
    public boolean isCancelButtonDisplayed()
    {
       return getCancelButton().isDisplayed();
    }
}
