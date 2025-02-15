package ae.pegasus.framework.pages.cbfinder.new_entity;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;

public class CBFinderNewCasePage extends CBFinderNewEntityBasePage {
    private static final String CREATE_CASE_BUTTON = "Create & Open Case";

    public void saveCreatedCase() {
        closeAllPopUps();
        getButtonByName(CREATE_CASE_BUTTON).click();
        getSuccessPopUp().shouldBe(Condition.visible);
    }
}
