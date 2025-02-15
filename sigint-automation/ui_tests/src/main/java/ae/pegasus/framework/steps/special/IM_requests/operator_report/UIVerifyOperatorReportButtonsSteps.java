package ae.pegasus.framework.steps.special.IM_requests.operator_report;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.special.IM.IMAction;
import org.jbehave.core.annotations.Then;
import ae.pegasus.framework.pages.Pages;

public class UIVerifyOperatorReportButtonsSteps {
    private void checkReportActionButtonIsNotPresent(IMAction action) {
        Asserter.getAsserter().softAssertFalse(
                Pages.verifyOperatorReportPage().checkVisibilityOfReportAction(action),
                "Report action button is not present",
                "Report action button is present without permission ");
    }

    private void checkReportActionButtonVisibleAndEnable(IMAction action) {
        Asserter.getAsserter().softAssertTrue(
                Pages.verifyOperatorReportPage().checkVisibilityOfReportAction(action),
                "Report action button is present and enabled",
                "Report action button is not enable or not present");
    }

    private void checkVisibilityOnReportCreation(IMAction action) {
        Asserter.getAsserter().softAssertTrue(
                Pages.verifyOperatorReportPage().checkVisibilityOnReportCreation(action),
                "Report action button is present and enabled",
                "Report action button is not enable or not present");
    }

    @Then("I should see Cancel button in operator report")
    public void iShouldSeeCancelButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.CANCEL);
    }

    @Then("I should see Submit for Review button")
    public void iSeeSubmitForReviewButton() {
        checkVisibilityOnReportCreation(IMAction.SUBMIT_FOR_REVIEW);
    }

    @Then("I should see Reject button in operator report")
    public void iShouldSeeRejectButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.REJECT);
    }

    @Then("I should see Return to Author button in operator report")
    public void iShouldSeeReturnToAuthorButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.RETURN_TO_AUTHOR);
    }

    @Then("I should see Approve button in operator report")
    public void iShouldSeeApproveButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.APPROVE);
    }

    @Then("I should see Unassign button in operator report")
    public void iShouldSeeUnassignButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.UNASSIGN);
    }

    @Then("I should see Assign button in operator report")
    public void iShouldSeeAssignButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.ASSIGN);
    }

    @Then("I should see Edit button in operator report")
    public void iShouldSeeEditButton() {
        checkReportActionButtonVisibleAndEnable(IMAction.EDIT);
    }

}
