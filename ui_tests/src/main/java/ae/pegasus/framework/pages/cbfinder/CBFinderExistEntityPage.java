package ae.pegasus.framework.pages.cbfinder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.cbfinder.CardInformationField;
import ae.pegasus.framework.constants.cbfinder.CreateAction;
import ae.pegasus.framework.constants.cbfinder.UpdateAction;
import ae.pegasus.framework.constants.special.modal_dialog.ModalDialogButton;
import org.apache.commons.lang.NotImplementedException;
import ae.pegasus.framework.pages.Pages;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CBFinderExistEntityPage extends CBFinderBasePage {

    @Override
    public void performAction(CreateAction action) {
        getRightHalfOfHeader().$x(".//pg-dropdown[@actions.bind='addActions']").click();
        switch (action) {
            case CREATE_FILE:
            case CREATE_CASE:
            case CREATE_PROFILE:
            case CREATE_RFI:
                getActionFromDropDownMenu(action.getActionLabelInReport()).click();
                break;
            default:
                throw new NotImplementedException("Unknown action '" + action.getActionLabelInReport() + "'");
        }
    }

    public void performAction(UpdateAction action) {
        closeAllPopUps();
        getRightHalfOfHeader().$x(".//pg-dropdown[@actions.bind='moreActions']").click();
        getActionFromDropDownMenu(action.getActionName()).click();
        switch (action) {
            case DELETE:
                Pages.modalDialogPage().clickButton(ModalDialogButton.DELETE , 600);
                getSuccessPopUp().shouldBe(Condition.visible);
                break;
            default:
                throw new NotImplementedException("Unknown action '" + action.getActionName() + "'");
        }
    }

    protected String getFieldValueFromCard(SelenideElement card, CardInformationField fieldName) {
        return card.$x(".//pg-col/small[text()='" + fieldName.getFieldName() + "']/../div").getText();
    }

    protected SelenideElement getCardBySubject(String subject) {

        for (SelenideElement card : $$x("//div[contains(@class, 'pg-card--interactive')]")) {
            if (getFieldValueFromCard(card, CardInformationField.SUBJECT).equals(subject)) {
                return card;
            }
        }
        throw new IllegalArgumentException("Card with subject '" + subject + "' was not found");
    }

    protected SelenideElement getCardById(String Id) {

        for (SelenideElement card : $$x("//div[contains(@class, 'pg-card--interactive')]")) {
            //div[contains(@class, 'pg-card__header')]//strong")
            if (card.$x(".//div[contains(@class, 'pg-card__header')]//strong").getText().equals(Id)) {
                return card;
            }
        }
        throw new IllegalArgumentException("Card with subject '" + Id + "' was not found");
    }

    public void openReportOrRequestByCardSubject(String subject) {
        getCardBySubject(subject).$x(".//div[@class='pg-card__footer']").click();
    }

    public void openReportByReportNumber(String Id) {
        getCardById(Id).$x(".//div[@class='pg-card__footer']").click();
    }

    public void openSaveSearchByName(String Name) {
        getCardById(Name).$x(".//div[@class='pg-card__footer']").click();
    }

    public void openTargetByName(String Name) {
        getCardById(Name).$x(".//div[@class='pg-card__footer']").click();
    }

    public boolean isSaveSearchByNamePresent(String Name) {
        return getCardById(Name).isDisplayed();
    }

    public boolean isTargetByNamePresent(String Name) {
        return getCardById(Name).isDisplayed();
    }

    public void clickCollapseView() {
        $x(".//pg-col[@class='au-target pg-f-col pg-f--noflex pg-f-0']//pg-btn[@type='primary-link']").click();
    }

    public void clickExpandView() {
        $x(".//div[@class='pg-f pg-f-col au-target']//pg-btn[2]//button[1]").click();
    }
}
