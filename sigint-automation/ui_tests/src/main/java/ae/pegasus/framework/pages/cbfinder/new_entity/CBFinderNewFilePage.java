package ae.pegasus.framework.pages.cbfinder.new_entity;

import com.codeborne.selenide.Condition;

public class CBFinderNewFilePage extends CBFinderNewEntityBasePage {

    private static final String CREATE_FILE_BUTTON = "Create & Open File";
    private static final String CANCEL_BUTTON = "Cancel";

    public void saveCreatedFile() {
        closeAllPopUps();
        getButtonByName(CREATE_FILE_BUTTON).click();
        getSuccessPopUp().shouldBe(Condition.visible);
    }
}
