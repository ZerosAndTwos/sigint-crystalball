package ae.pegasus.framework.pages.special.attach_to;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.elements.special.attach_to.AssignRecordTableRow;
import ae.pegasus.framework.pages.basic_pages.ModalDialogPage;
import ae.pegasus.framework.utils.PageUtils;

import static com.codeborne.selenide.Condition.visible;
import static ae.pegasus.framework.constants.special.modal_dialog.ModalDialogButton.ASSIGN;

public class AssignRecordsPage extends ModalDialogPage {
    @Override
    public boolean isPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("Assign records...");
    }

    @Override
    public String getPageTitle() {
        return getDialogHeader().$x(".//h4").getText();
    }

    private SelenideElement getFilterInput() {
        return getDialogBody().$x(".//input");
    }

    private AssignRecordTableRow getRow(String userName) {
        for (SelenideElement record : getDialogBody().$$x(".//div[@class='pg-tbody']//div[contains(@class, 'actualRecord')]")) {
            AssignRecordTableRow row = new AssignRecordTableRow(record);
            if (row.getUserName().equalsIgnoreCase(userName)) {
                return row;
            }
        }
        throw new IllegalArgumentException("Row having user name '" + userName + "' was not found on the Assign Records dialog");
    }

    public void assignRecordToUser(String userName) {
        PageUtils.clearAndType(getFilterInput(), userName);
        getRow(userName).setSelected(true);
        closeAllPopUps();
        clickButton(ASSIGN);
        getSuccessPopUp().shouldBe(visible);
    }
}
