package ae.pegasus.framework.steps.special.IM_requests.master_report;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.special.IM.IMAction;
import ae.pegasus.framework.pages.Pages;
import org.jbehave.core.annotations.Then;


public class UIVerifyMasterReportButtonsSteps {
    private void checkReportActionButtonIsNotPresent(IMAction action) {
        Asserter.getAsserter().softAssertFalse(
                Pages.verifyMasterReportPage().checkVisibilityOfReportAction(action),
                "Report action button is not present",
                "Report action button is present without permission ");
    }

    private void checkReportActionButtonVisibleAndEnable(IMAction action) {
        Asserter.getAsserter().softAssertTrue(
                Pages.verifyMasterReportPage().checkVisibilityOfReportAction(action),
                "Report action button is present and enabled",
                "Report action button is not enable or not present");
    }

    private void checkVisibilityOnReportCreation(IMAction action) {
        Asserter.getAsserter().softAssertTrue(
                Pages.verifyMasterReportPage().checkVisibilityOnReportCreation(action),
                "Report action button is present and enabled",
                "Report action button is not enable or not present");
    }

    @Then("I should see Cancel button in master report")
    public void iShouldSeeCancelButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.CANCEL);
    }

    @Then("I should see Submit for Review button")
    public void iSeeSubmitForReviewButton() {
        checkVisibilityOnReportCreation(IMAction.SUBMIT_FOR_REVIEW);
    }

    @Then("I should see Reject button in master report")
    public void iShouldSeeRejectButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.REJECT);
    }

    @Then("I should see Return to Author button in master report")
    public void iShouldSeeReturnToAuthorButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.RETURN_TO_AUTHOR);
    }

    @Then("I should see Approve button in master report")
    public void iShouldSeeApproveButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.APPROVE);
    }

    @Then("I should see Assign button in master report")
    public void iShouldSeeAssignButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.ASSIGN);
    }

    @Then("I should see Unassign button in master report")
    public void iShouldSeeUnassignButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.UNASSIGN);
    }


    @Then("I should see Edit button in master report")
    public void iShouldSeeEditButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.EDIT);
    }

}
