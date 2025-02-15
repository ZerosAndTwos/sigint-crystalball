package ae.pegasus.framework.steps.special.IM_requests.operator_report;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.context.Context;
import ae.pegasus.framework.utils.PDFReader;
import ae.pegasus.framework.utils.ParametersHelper;
import ae.pegasus.framework.constants.profiler.ProfilerWidget;

import ae.pegasus.framework.constants.special.IM.IMField;
import ae.pegasus.framework.utils.UnzipFolder;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.model.ExamplesTable;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.utils.AssertUtils;

import java.util.Arrays;
import java.util.List;

import static ae.pegasus.framework.constants.profiler.ProfilerWidget.*;

public class UIVerifyOperatorReportFieldsSteps {
    private void checkListValue(IMField field, List<String> expectedValues, String verifiedParameter) {
        AssertUtils.checkLists(
                Pages.verifyOperatorReportPage().getListOfValues(field),
                expectedValues,
                verifiedParameter);
    }

    private void checkSingleValue(IMField field, String expectedValue, String verifiedParameter) {
        Asserter.getAsserter().softAssertEquals(
                Pages.verifyOperatorReportPage().getSingleValue(field),
                expectedValue,
                verifiedParameter);
    }





    @Then("I should see that currently opened operator report has status ($reportStatus)")
    public void iShouldSeeReportStatus(String reportStatus) {
        Asserter.getAsserter().softAssertEquals(Pages.verifyOperatorReportPage().getReportOrRequestStatus(),
                reportStatus,
                "Status of currently opened operator report");
    }

    @Then("I should see card attached to currently opened operator report with all following label/value pair(s): $labelValuePairs")
    public void iShouldSeeCardWithLabelValuePairs(ExamplesTable labelValuePairs) {
        Asserter.getAsserter().softAssertTrue(
                Pages.verifyOperatorReportPage().isCardWithLabelValuePairsAttached(ParametersHelper.processExampleTable(labelValuePairs)),
                "Card is attached to currently opened report",
                "Card is NOT attached to currently opened report");
    }

    @Then("I should see card attached to currently opened operator report with Text Message ($textMessage) and all following label/value pair(s): $labelValuePairs")
    public void iShouldSeeCardWithLabelValuePairs(String textMessage, ExamplesTable labelValuePairs) {
        Asserter.getAsserter().softAssertTrue(
                Pages.verifyOperatorReportPage().isCardWithTextMessageAndLabelValuePairsAttached(textMessage, ParametersHelper.processExampleTable(labelValuePairs)),
                "Card is attached to currently opened report",
                "Card is NOT attached to currently opened report");
    }

    @Then("I should see Classification ($classification) in operator report")
    public void iShouldSeeClassification(String classification) {
        checkSingleValue(IMField.CLASSIFICATION, classification, "Classification in operator report");
    }

    @Then("I should see Request Number ($requestNumber) in operator report")
    public void iShouldSeeRequestNumber(String requestNumber) {
        checkSingleValue(IMField.REQUEST_NUMBER, requestNumber, "Request Number in operator report");
    }

    @Then("I should see Created For ($createdFor) in operator report")
    public void iShouldSeeCreatedFor(String createdFor) {
        checkSingleValue(IMField.CREATED_FOR, createdFor, "Created For in operator report");
    }

    @Then("I should see File Name/Case Name(s) ($fileOrCaseNames) in operator report")
    public void iShouldSeeFileOrCaseNames(ExamplesTable fileOrCaseNames) {
        checkListValue(IMField.FILE_OR_CASE_NAME,
                Arrays.asList(ParametersHelper.processExampleTable(fileOrCaseNames)),
                "File Name/Case Name(s) in operator report");
    }

    @Then("I should see Organization Units ($orgUnits) in operator report")
    public void iShouldSeeOrgUnits(ExamplesTable orgUnits) {
        checkListValue(IMField.ORGANIZATION_UNITS,
                Arrays.asList(ParametersHelper.processExampleTable(orgUnits)),
                "Organization Units in operator report");
    }

    @Then("I should see Subject ($subject) in operator report")
    public void iShouldSeeSubject(String subject) {
        checkSingleValue(IMField.SUBJECT, subject, "Subject in operator report");
    }

    private String convertToString (ExamplesTable toConvert) {
        return String.join("\n", ParametersHelper.processExampleTable(toConvert));
    }

    @Then("I should see Description ($descriptionLines) in operator report")
    public void iShouldSeeDescription(ExamplesTable descriptionLines) {
        checkSingleValue(IMField.DESCRIPTION, convertToString(descriptionLines), "Description in operator report");
    }

    @Then("I should see Considerations ($considerationsLines) in operator report")
    public void iShouldSeeConsiderations(ExamplesTable considerationsLines) {
        checkSingleValue(IMField.CONSIDERATIONS, convertToString(considerationsLines), "Considerations in operator report");
    }

    @Then("I should see Recommendations ($recommendationsLines) in operator report")
    public void iShouldSeeRecommendations(ExamplesTable recommendationsLines) {
        checkSingleValue(IMField.RECOMMENDATIONS, convertToString(recommendationsLines), "Recommendations in operator report");
    }

    @Then("I should see Notes ($notesLines) in operator report")
    public void iShouldSeeNotes(ExamplesTable notesLines) {
        checkSingleValue(IMField.NOTES, convertToString(notesLines), "Notes in operator report");
    }

    @Then("I should see Related Files/Cases ($relatedCasesOrFiles) in operator report")
    public void iShouldSeeRelatedCaseOrFile(ExamplesTable relatedCasesOrFiles) {
        checkListValue(IMField.RELATED_CASES_OR_FILES,
                Arrays.asList(ParametersHelper.processExampleTable(relatedCasesOrFiles)),
                "Related Files/Cases in operator report");
    }

    private void checkNumberOfSnapshots(ProfilerWidget widget, int expectedSnapshotNumber) {
        Asserter.getAsserter().softAssertEquals(
                Pages.verifyOperatorReportPage().getNumberOfAttachedSnapshots(widget),
                expectedSnapshotNumber,
                "Number of '" + widget.getWidgetName() + "'-widget's snapshot(s) attached");
    }

    @Then("I should see ($numberOfSnapshots) snapshot(s) of Target Summary widget in operator report")
    public void iShouldSeeTargetSummarySnapshots(int numberOfSnapshots) {
        checkNumberOfSnapshots(TARGET_SUMMARY, numberOfSnapshots);
    }

    @Then("I should see ($numberOfSnapshots) snapshot(s) of Last Seen widget in operator report")
    public void iShouldSeeLastSeenSnapshots(int numberOfSnapshots) {
        checkNumberOfSnapshots(LAST_SEEN, numberOfSnapshots);
    }

    @Then("I should see ($numberOfSnapshots) snapshot(s) of Identifiers widget in operator report")
    public void iShouldSeeIdentifiersSnapshots(int numberOfSnapshots) {
        checkNumberOfSnapshots(IDENTIFIERS, numberOfSnapshots);
    }

    @Then("I should see ($numberOfSnapshots) snapshot(s) of RFIs widget in operator report")
    public void iShouldSeeRFIsSnapshots(int numberOfSnapshots) {
        checkNumberOfSnapshots(RFIS, numberOfSnapshots);
    }

    @Then("I verify the downloaded operator report for ($values)")
    public void iShouldVerifyOperatorReport(String values) {

        UnzipFolder.startUnzip(PDFReader.getDownloadedReportPath() , Context.getContext().getReportNumber());

        PDFReader.validatePDF((UnzipFolder.getFileNameStartingWith((PDFReader.getDownloadedReportPath() +"/Output"), "Operator")), values);
    }

}