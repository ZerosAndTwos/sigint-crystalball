package ae.pegasus.framework.steps.special.IM_requests.request_for_information;

import ae.pegasus.framework.utils.ParametersHelper;
import ae.pegasus.framework.constants.special.modal_dialog.ModalDialogButton;
import ae.pegasus.framework.constants.special.modal_dialog.ModalDialogField;
import ae.pegasus.framework.constants.special.IM.IMAction;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import ae.pegasus.framework.pages.Pages;

public class UIRequestForInformationActionsSteps {
    @When("I start sending of currently opened request for information")
    public void iStartSendingRequest() {
        Pages.updateRequestForInformationPage().performAction(IMAction.SEND);
    }

    @When("I select Organization Unit ($orgUnit) for the sending request for information")
    public void iSelectOrgUnit(String orgUnit) {
        Pages.requestForInformationSendingDialog().selectOrganizationUnit(orgUnit);
    }

    @When("I remove Organization Unit for the sending request for information")
    public void iRemoveOrgUnit() {
        Pages.requestForInformationSendingDialog().removeOrganizationUnit();
    }

    @When("I enter Comments ($commentLines) for the sending request for information")
    public void iEnterComment(ExamplesTable commentLines) {
        Pages.requestForInformationSendingDialog().setDataToField(
                ModalDialogField.COMMENTS,
                String.join("\n", ParametersHelper.processExampleTable(commentLines)));
    }

    @When("I complete sending of request for information")
    public void iCompleteSending(){
        Pages.requestForInformationSendingDialog().completeSending();
        Pages.updateRequestForInformationPage().waitForPageLoading();
    }

    @When("I cancel sending of request for information")
    public void iCancelSending(){
        Pages.requestForInformationSendingDialog().clickButton(ModalDialogButton.CANCEL);
    }

    @When("I recall currently opened request for information")
    public void iRecallRequest() {
        Pages.updateRequestForInformationPage().performAction(IMAction.RECALL);
    }
}
