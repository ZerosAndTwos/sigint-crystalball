package ae.pegasus.framework.steps.special.IM_requests.operator_report;

import ae.pegasus.framework.utils.ParametersHelper;
import ae.pegasus.framework.constants.special.modal_dialog.ModalDialogButton;
import ae.pegasus.framework.constants.special.IM.IMAction;

import ae.pegasus.framework.constants.special.IM.IMField;
import ae.pegasus.framework.context.Context;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.special.modal_dialog.ModalDialogButton.CANCEL;
import static ae.pegasus.framework.constants.special.modal_dialog.ModalDialogButton.OK;
import static ae.pegasus.framework.constants.special.modal_dialog.ModalDialogField.COMMENTS;

public class UIUpdateOperatorReportSteps {
    @Given("I save Report number for the operator report in the context")
    public void iSaveRequestNumber() {
        Context.getContext().setReportNumber(Pages.updateOperatorReportPage().getReportOrRequestNumber());
    }

    @When("I set Classification ($classification) in operator report")
    public void iSetClassification(String classification) {
        Pages.updateOperatorReportPage().setSingleValueData(IMField.CLASSIFICATION, classification);
    }

    @When("I set Request Number ($requestNumber) in operator report")
    public void iSetRequestNumber(String requestNumber) {
        Pages.updateOperatorReportPage().setSingleValueData(IMField.REQUEST_NUMBER, requestNumber);
    }

    @When("I set Created For ($createdFor) in operator report")
    public void iSetCreatedFor(String createdFor) {
        Pages.updateOperatorReportPage().setSingleValueData(IMField.CREATED_FOR, createdFor);
    }

    @When("I set File Name/Case Name ($fileOrCaseNames) in operator report")
    public void iSetFileOrCaseName(ExamplesTable fileOrCaseNames) {
        Pages.updateOperatorReportPage().setMultiValueData(IMField.FILE_OR_CASE_NAME, ParametersHelper.processExampleTable(fileOrCaseNames));
    }

    @When("I set Organization Units ($orgUnits) in operator report")
    public void iSetOrgUnits(ExamplesTable orgUnits) {
        Pages.updateOperatorReportPage().setMultiValueData(IMField.ORGANIZATION_UNITS, ParametersHelper.processExampleTable(orgUnits));
    }

    @When("I set Subject ($subject) in operator report")
    public void iSetSubject(String subject) {
        Pages.updateOperatorReportPage().setSingleValueData(IMField.SUBJECT, subject);
    }

    @When("I set Description ($descriptionLines) in operator report")
    public void iSetDescription(ExamplesTable descriptionLines) {
        Pages.updateOperatorReportPage().setMultiValueData(IMField.DESCRIPTION, ParametersHelper.processExampleTable(descriptionLines));
    }

    @When("I set Considerations ($considerationLines) in operator report")
    public void iSetConsiderations(ExamplesTable considerationLines) {
        Pages.updateOperatorReportPage().setMultiValueData(IMField.CONSIDERATIONS, ParametersHelper.processExampleTable(considerationLines));
    }

    @When("I set Recommendations ($recommendationLines) in operator report")
    public void iSetRecommendations(ExamplesTable recommendationLines) {
        Pages.updateOperatorReportPage().setMultiValueData(IMField.RECOMMENDATIONS, ParametersHelper.processExampleTable(recommendationLines));
    }

    @When("I set Notes ($notesLines) in operator report")
    public void iSetNotes(ExamplesTable notesLines) {
        Pages.updateOperatorReportPage().setMultiValueData(IMField.NOTES, ParametersHelper.processExampleTable(notesLines));
    }

    @When("I set Related Files/Cases ($relatedCasesOrFiles) in operator report")
    public void iSetRelatedCaseOrFile(ExamplesTable relatedCasesOrFiles) {
        Pages.updateOperatorReportPage().setMultiValueData(IMField.RELATED_CASES_OR_FILES, ParametersHelper.processExampleTable(relatedCasesOrFiles));
    }

    @When("I save currently opened operator report as draft")
    public void iSaveReportAsDraft() {
        Pages.updateOperatorReportPage().performAction(IMAction.SAVE_AS_DRAFT);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Pages.updateOperatorReportPage().waitForPageLoading();
       // Pages.searchResultDetailsPage().closeDialog();
    }

    @Then("I close the operator report")
    public void iCloseReport() {
         Pages.searchResultDetailsPage().closeDialog();
}

    @When("I delete operator report which is currently opened")
    public void iDeleteReport() {
        Pages.updateOperatorReportPage().performAction(IMAction.DELETE_REPORT);
        Pages.modalDialogPage().clickButton(ModalDialogButton.YES);
        Pages.updateOperatorReportPage().waitForPageLoading();
    }

    @When("I start edit of operator report which is currently opened")
    public void iStartEditReport() {
        Pages.updateOperatorReportPage().performAction(IMAction.EDIT);
        Pages.updateOperatorReportPage().waitForPageLoading();
    }

    @When("I Submit For Review the operator report which is currently opened")
    public void iSubmitForReviewReport() {
        Pages.updateOperatorReportPage().performAction(IMAction.SUBMIT_FOR_REVIEW);
        Pages.updateOperatorReportPage().waitForPageLoading();

    }
    @When("I Take Ownership of the operator report which is currently opened")
    public void iTakeOwnershipOfReport() {
        Pages.updateOperatorReportPage().performAction(IMAction.TAKE_OWNERSHIP);
        Pages.updateOperatorReportPage().waitForPageLoading();
    }

    @When("I Unassign the operator report which is currently opened")
    public void iUnassignTheReport() {
        Pages.updateOperatorReportPage().performAction(IMAction.UNASSIGN);
    }

    @When("I Assign the operator report which is currently opened")
    public void iAssignTheReport() {
        Pages.updateOperatorReportPage().performAction(IMAction.ASSIGN);
    }

    @When("I Return to Author the operator report which is currently opened")
    public void iReturnReport() {
        Pages.updateOperatorReportPage().performAction(IMAction.RETURN_TO_AUTHOR);
    }


    @When("I Approve the operator report which is currently opened")
    public void iApproveReport() {
        Pages.updateOperatorReportPage().performAction(IMAction.APPROVE);
        Pages.updateOperatorReportPage().waitForPageLoading();
    }

    @When("I Cancel the operator report which is currently opened")
    public void iCancelReport() {
        Pages.updateOperatorReportPage().performAction(IMAction.CANCEL);
    }


    @When("I Reject the operator report which is currently opened")
    public void iRejectReport() {
        Pages.updateOperatorReportPage().performAction(IMAction.REJECT);
    }


    @When("I enter routing ($comment) for the operator report")
    public void iEnterRoutingComment(String comment) {
        Pages.modalDialogPage().setDataToField(COMMENTS, comment);
    }

    @When("I enter submititng routing ($comment) for the operator report")
    public void iEnterSubmittingRoutingComment(String comment) {
        Pages.modalDialogPage().setDataToField(COMMENTS, comment);
    }

    @When("I route the operator report")
    public void iRouteReport() {
        Pages.modalDialogPage().clickButton(OK);
        Pages.updateOperatorReportPage().waitForPageLoading();
    }

    @When("I cancel the routing of the operator report")
    public void iCancelReportRouting() {
        Pages.modalDialogPage().clickButton(CANCEL);
    }
}

