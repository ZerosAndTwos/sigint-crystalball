package ae.pegasus.framework.steps.special.IM_requests.request_for_information;

import ae.pegasus.framework.utils.ParametersHelper;
import ae.pegasus.framework.constants.special.IM.IMAction;
import ae.pegasus.framework.constants.special.IM.IMField;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import ae.pegasus.framework.pages.Pages;

public class UIUpdateRequestForInformationSteps {
    @When("I set Classification ($classification) in request for information")
    public void iSetClassification(String classification) {
        Pages.updateRequestForInformationPage().setSingleValueData(IMField.CLASSIFICATION, classification);
    }

    @When("I set Priority ($priority) in request for information")
    public void iSetPriority(String priority) {
        Pages.updateRequestForInformationPage().setSingleValueData(IMField.PRIORITY, priority);
    }

    @When("I set File Name/Case Name ($fileOrCaseNames) in request for information")
    public void iSetFileOrCaseName(ExamplesTable fileOrCaseNames) {
        Pages.updateRequestForInformationPage().setMultiValueData(IMField.FILE_OR_CASE_NAME, ParametersHelper.processExampleTable(fileOrCaseNames));
    }

    @When("I set Organization Units ($orgUnits) in request for information")
    public void iSetOrgUnits(ExamplesTable orgUnits) {
        Pages.updateRequestForInformationPage().setMultiValueData(IMField.ORGANIZATION_UNITS, ParametersHelper.processExampleTable(orgUnits));
    }

    @When("I set Manual RFI number ($manualRFINumber) in request for information")
    public void iSetManualRFINumber(String manualRFINumber) {
        Pages.updateRequestForInformationPage().setSingleValueData(IMField.MANUAL_RFI_NUMBER, manualRFINumber);
    }

    @When("I set RFI Type ($rfiType) in request for information")
    public void iSetRFIType(ExamplesTable rfiType) {
        Pages.updateRequestForInformationPage().setMultiValueData(IMField.RFI_TYPE, ParametersHelper.processExampleTable(rfiType));
    }

    @When("I set Subject ($subject) in request for information")
    public void iSetSubject(String subject) {
        Pages.updateRequestForInformationPage().setSingleValueData(IMField.SUBJECT, subject);
    }

    @When("I set Required ($requiredLines) in request for information")
    public void iSetRequired(ExamplesTable requiredLines) {
        Pages.updateRequestForInformationPage().setMultiValueData(IMField.REQUIRED, ParametersHelper.processExampleTable(requiredLines));
    }

    @When("I set Notes ($notesLines) in request for information")
    public void iSetNotes(ExamplesTable notesLines) {
        Pages.updateRequestForInformationPage().setMultiValueData(IMField.NOTES, ParametersHelper.processExampleTable(notesLines));
    }

    @When("I set Related Files/Cases ($relatedCasesOrFiles) in request for information")
    public void iSetRelatedCaseOrFile(ExamplesTable relatedCasesOrFiles) {
        Pages.updateRequestForInformationPage().setMultiValueData(IMField.RELATED_CASES_OR_FILES, ParametersHelper.processExampleTable(relatedCasesOrFiles));
    }

    @When("I set Related RFIs ($relatedRFIs) in request for information")
    public void iSetRelatedRFIs(ExamplesTable relatedRFIs) {
        Pages.updateRequestForInformationPage().setMultiValueData(IMField.RELATED_RFIS, ParametersHelper.processExampleTable(relatedRFIs));
    }

    @When("I set Related Profiles ($relatedProfiles) in request for information")
    public void iSetRelatedProfiles(ExamplesTable relatedProfiles) {
        Pages.updateRequestForInformationPage().setMultiValueData(IMField.RELATED_PROFILES, ParametersHelper.processExampleTable(relatedProfiles));
    }

    @When("I save currently opened request for information as draft")
    public void iSaveReportAsDraft() {
        Pages.updateRequestForInformationPage().performAction(IMAction.SAVE_AS_DRAFT);
        Pages.updateRequestForInformationPage().waitForPageLoading();
    }

    @When("I delete request for information which is currently opened with comment ($commentLines)")
    public void iDeleteRequest(ExamplesTable commentLines) {
        Pages.updateRequestForInformationPage().deleteRequestForInformation(ParametersHelper.processExampleTable(commentLines));
    }

    @When("I start edit of request for information which is currently opened")
    public void iStartEditRequest() {
        Pages.updateRequestForInformationPage().performAction(IMAction.EDIT);
        Pages.updateRequestForInformationPage().waitForPageLoading();
    }

    @When("I Submit For Review the request for information which is currently opened")
    public void iSubmitForReviewRequest() {
        Pages.updateRequestForInformationPage().performAction(IMAction.SUBMIT_FOR_REVIEW);
        Pages.updateRequestForInformationPage().waitForPageLoading();
    }

    @When("I Take Ownership of the request for information which is currently opened")
    public void iTakeOwnershipOfRequest() {
        Pages.updateRequestForInformationPage().performAction(IMAction.TAKE_OWNERSHIP);
        Pages.updateRequestForInformationPage().waitForPageLoading();
    }

    @When("I Unassign the request for information which is currently opened")
    public void iUnassignTheRequest() {
        Pages.updateRequestForInformationPage().performAction(IMAction.UNASSIGN);
    }

    @When("I Return to Author the request for information which is currently opened")
    public void iReturnRequest() {
        Pages.updateRequestForInformationPage().performAction(IMAction.RETURN_TO_AUTHOR);
    }


    @When("I Approve the request for information which is currently opened")
    public void iApproveRequest() {
        Pages.updateRequestForInformationPage().performAction(IMAction.APPROVE);
        Pages.updateRequestForInformationPage().waitForPageLoading();
    }

    @When("I Reject the request for information which is currently opened")
    public void iRejectRequest() {
        Pages.updateRequestForInformationPage().performAction(IMAction.REJECT);
    }
}

