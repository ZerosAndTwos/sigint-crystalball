package ae.pegasus.framework.steps.special.IM_requests.master_report;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.profiler.ProfilerWidget;

import ae.pegasus.framework.constants.special.IM.IMField;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.utils.AssertUtils;
import ae.pegasus.framework.utils.ParametersHelper;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.model.ExamplesTable;

import java.util.Arrays;
import java.util.List;

import static ae.pegasus.framework.constants.profiler.ProfilerWidget.*;

public class UIVerifyMasterReportFieldsSteps {
    private void checkListValue(IMField field, List<String> expectedValues, String verifiedParameter) {
        AssertUtils.checkLists(
                Pages.verifyMasterReportPage().getListOfValues(field),
                expectedValues,
                verifiedParameter);
    }





    private void checkListValue( List<String> actualValues,List<String> expectedValues, String verifiedParameter) {
        AssertUtils.checkLists(
                actualValues,
                expectedValues,
                verifiedParameter);
    }

    private void checkSingleValue(IMField field, String expectedValue, String verifiedParameter) {
        Asserter.getAsserter().softAssertEquals(
                Pages.verifyMasterReportPage().getSingleValue(field),
                (expectedValue.isEmpty() ? "-" : expectedValue),
                verifiedParameter);


    }

    @Then("I should see that currently opened master report has status ($reportStatus)")
    public void iShouldSeeReportStatus(String reportStatus) {
        Asserter.getAsserter().softAssertEquals(Pages.verifyMasterReportPage().getReportOrRequestStatus(),
                reportStatus,
                "Status of currently opened master report");
    }

    @Then("I should see card attached to currently opened master report with all following label/value pair(s): $labelValuePairs")
    public void iShouldSeeCardWithLabelValuePairs(ExamplesTable labelValuePairs) {
        Asserter.getAsserter().softAssertTrue(
                Pages.verifyMasterReportPage().isCardWithLabelValuePairsAttached(ParametersHelper.processExampleTable(labelValuePairs)),
                "Card is attached to currently opened report",
                "Card is NOT attached to currently opened report");
    }

    @Then("I should see card attached to currently opened master report with Text Message ($textMessage) and all following label/value pair(s): $labelValuePairs")
    public void iShouldSeeCardWithLabelValuePairs(String textMessage, ExamplesTable labelValuePairs) {
        Asserter.getAsserter().softAssertTrue(
                Pages.verifyMasterReportPage().isCardWithTextMessageAndLabelValuePairsAttached(textMessage, ParametersHelper.processExampleTable(labelValuePairs)),
                "Card is attached to currently opened report",
                "Card is NOT attached to currently opened report");
    }

    @Then("I should see Classification ($classification) in master report")
    public void iShouldSeeClassification(String classification) {
        checkSingleValue(IMField.CLASSIFICATION, classification, "Classification in master report");
    }

    @Then("I should see Request Number ($requestNumber) in master report")
    public void iShouldSeeRequestNumber(String requestNumber) {
        checkSingleValue(IMField.REQUEST_NUMBER, requestNumber, "Request Number in master report");
    }

    @Then("I should see Created For ($createdFor) in master report")
    public void iShouldSeeCreatedFor(String createdFor) {
        checkSingleValue(IMField.CREATED_FOR, createdFor, "Created For in master report");
    }

    @Then("I should see File Name/Case Name(s) ($fileOrCaseNames) in master report")
    public void iShouldSeeFileOrCaseNames(ExamplesTable fileOrCaseNames) {
        checkListValue(IMField.FILE_OR_CASE_NAME,
                Arrays.asList(ParametersHelper.processExampleTable(fileOrCaseNames)),
                "File Name/Case Name(s) in master report");
    }

    @Then("I should see Organization Units ($orgUnits) in master report")
    public void iShouldSeeOrgUnits(ExamplesTable orgUnits) {
        checkListValue(IMField.ORGANIZATION_UNITS_CB,
                Arrays.asList(ParametersHelper.processExampleTable(orgUnits)),
                "Organization Units in master report");
    }

    @Then("I should see Subject ($subject) in master report")
    public void iShouldSeeSubject(String subject) {
        checkSingleValue(IMField.SUBJECT, subject, "Subject in master report");
    }

    @Then("I should see Overview ($overview) in master report")
    public void iShouldSeeOverview(String subject) {
        checkSingleValue(IMField.OVERVIEW, subject, "Overview in master report");
    }

    private String convertToString (ExamplesTable toConvert) {
        return String.join("\n", ParametersHelper.processExampleTable(toConvert));
    }

    @Then("I should see Results ($results) in master report")
    public void iShouldSeeDescription(String results) {
        checkSingleValue(IMField.RESULT, results, "Results in master report");
    }

    @Then("I should see Originating Reports section heading as ($heading) in master report")
    public void iShouldSeeConsiderations(String heading) {
        Asserter.getAsserter().softAssertEquals(Pages.verifyMasterReportPage().getMasterReportOriginatingReportsBlockHeading(),
                heading,
                "Originating Reports section heading");
    }






    @Then("I should see Related Files/Cases ($relatedCasesOrFiles) in master report")
    public void iShouldSeeRelatedCaseOrFile(ExamplesTable relatedCasesOrFiles) {
        checkListValue(IMField.RELATED_CASES_OR_FILES,
                Arrays.asList(ParametersHelper.processExampleTable(relatedCasesOrFiles)),
                "Related Files/Cases in master report");
    }

    private void checkNumberOfSnapshots(ProfilerWidget widget, int expectedSnapshotNumber) {
        Asserter.getAsserter().softAssertEquals(
                Pages.verifyMasterReportPage().getNumberOfAttachedSnapshots(widget),
                expectedSnapshotNumber,
                "Number of '" + widget.getWidgetName() + "'-widget's snapshot(s) attached");
    }

    @Then("I should see ($numberOfSnapshots) snapshot(s) of Target Summary widget in master report")
    public void iShouldSeeTargetSummarySnapshots(int numberOfSnapshots) {
        checkNumberOfSnapshots(TARGET_SUMMARY, numberOfSnapshots);
    }

    @Then("I should see ($numberOfSnapshots) snapshot(s) of Last Seen widget in master report")
    public void iShouldSeeLastSeenSnapshots(int numberOfSnapshots) {
        checkNumberOfSnapshots(LAST_SEEN, numberOfSnapshots);
    }

    @Then("I should see ($numberOfSnapshots) snapshot(s) of Identifiers widget in master report")
    public void iShouldSeeIdentifiersSnapshots(int numberOfSnapshots) {
        checkNumberOfSnapshots(IDENTIFIERS, numberOfSnapshots);
    }

    @Then("I should see ($numberOfSnapshots) snapshot(s) of RFIs widget in master report")
    public void iShouldSeeRFIsSnapshots(int numberOfSnapshots) {
        checkNumberOfSnapshots(RFIS, numberOfSnapshots);
    }


    @Then("I should see Originating Reports column header as ($colHeaders) in master report")
    public void iShouldSeeOrgRepColHeaders(String colHeaders) {
        checkListValue(
                Arrays.asList(colHeaders.split(",")),
                Pages.verifyMasterReportPage().getMasterReportOriginatingReportsBlockTableCol(),
                "Originating Reports colHeaders in master report");
    }

    @Then("I should see Originating Reports values as ($repValues) in master report")
    public void iShouldSeeOrgRepValues(String repValues) {
        checkListValue(
                Arrays.asList(repValues.split(",")),
                Pages.verifyMasterReportPage().getMasterReportOriginatingReportsValues(),
                "Originating Reports Values in master report");
    }
}