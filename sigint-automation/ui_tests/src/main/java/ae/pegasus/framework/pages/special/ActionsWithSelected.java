package ae.pegasus.framework.pages.special;

import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.basic_pages.api.BasePage;
import com.codeborne.selenide.SelenideElement;

import static ae.pegasus.framework.constants.special.modal_dialog.ModalDialogButton.YES;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class ActionsWithSelected extends BasePage {
    private final String ADD_TO_TARGET_BTN_TITLE = "Add to Target";
    private final String ADD_TO_REPORT_BTN_TITLE = "Add to Report";
    private final String ADD_TO_MASTER_REPORT_BTN_TITLE = "Add to Master Report";
    private final String EXPORT_RECORDS_BTN_TITLE = "Export Record(s)";
    private final String EXPORT_DOCUMENT_BTN_TITLE = "Export Document(s)";
    private final String ASSIGN_TO_OTHER_BTN_TITLE = "Assign...";
    private final String ASSIGN_TO_ME_BTN_TITLE = "Take Ownership (Assign to me)";
    private final String UNASSIGN_BTN_TITLE = "Remove Ownership (Unassign from me)";
    private final String REASSIGN_TO_OTHER_BTN_TITLE = "Reassign...";
    private final String UNASSIGN_BTN_TITLE_ORGPAGE = "Unassign";
    private final String MARKREVIEWED_BTN_TITLE = "Mark Reviewed";
    private final String UNMARKREVIEWED_BTN_TITLE = "Unmark Reviewed";

    @Override
    public String getPageTitle() {
        return "Actions with selected";
    }

    @Override
    public boolean isPageDisplayed() {
        return getButtonWithTitle(ADD_TO_REPORT_BTN_TITLE).isDisplayed()
                && getButtonWithTitle(ADD_TO_TARGET_BTN_TITLE).isDisplayed()
                && getButtonWithTitle(EXPORT_RECORDS_BTN_TITLE).isDisplayed()
                && getButtonWithTitle(ASSIGN_TO_OTHER_BTN_TITLE).isDisplayed();
    }

    private SelenideElement getButtonWithTitle(String title) {
      //  $$x("//pg-btn");
        return $x("//pg-btn[contains(@pg-tooltip,'" + title + "')]");


    }

    public void addSelectedToReport() {
        getButtonWithTitle(ADD_TO_REPORT_BTN_TITLE).click();
    }

    public void exportSelectedRecord() {
        getButtonWithTitle(EXPORT_RECORDS_BTN_TITLE).click();
    }

    public void exportSelectedDocumnet() {
        getButtonWithTitle(EXPORT_DOCUMENT_BTN_TITLE).click();
        sleep(3000);
        $x("//pg-btn[@label ='Download Archive']").click();
    }
    public void addSelectedToMasterReport() {
        getButtonWithTitle(ADD_TO_MASTER_REPORT_BTN_TITLE).click();
    }

    public void addSelectedToTarget() {
        getButtonWithTitle(ADD_TO_TARGET_BTN_TITLE).click();
    }

    public void assignSelectedToOther() {
        getButtonWithTitle(ASSIGN_TO_OTHER_BTN_TITLE).click();
    }

    public void reassignSelectedToOther() {
        getButtonWithTitle(REASSIGN_TO_OTHER_BTN_TITLE).click();
    }

    public void assignSelectedToMe() {
        closeAllPopUps();
        getButtonWithTitle(ASSIGN_TO_ME_BTN_TITLE).click();
        Pages.modalDialogPage().clickButton(YES);
        getSuccessPopUp().shouldBe(visible);
    }

    public void unassignSelected() {
        closeAllPopUps();
        getButtonWithTitle(UNASSIGN_BTN_TITLE).click();
        Pages.modalDialogPage().clickButton(YES);
        getSuccessPopUp().shouldBe(visible);
    }

    public void unassignSelectedOrgPage() {
        closeAllPopUps();
        getButtonWithTitle(UNASSIGN_BTN_TITLE_ORGPAGE).click();
        Pages.modalDialogPage().clickButton(YES);
        getSuccessPopUp().shouldBe(visible);
    }

    public void markReivewedSelected() {
        getButtonWithTitle(MARKREVIEWED_BTN_TITLE).click();
        getSuccessPopUp().shouldBe(visible);
    }

    public void unMarkReivewedSelected() {
        getButtonWithTitle(UNMARKREVIEWED_BTN_TITLE).click();
        getSuccessPopUp().shouldBe(visible);
    }
 }
