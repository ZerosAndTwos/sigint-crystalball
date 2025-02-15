package ae.pegasus.framework.steps.profiler;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.assertion.DateTimeTolerance;
import ae.pegasus.framework.utils.ParametersHelper;
import ae.pegasus.framework.utils.TimeUtils;
import ae.pegasus.framework.constants.profiler.TargetSummaryParameter;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.model.ExamplesTable;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.utils.AssertUtils;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static ae.pegasus.framework.constants.profiler.TargetSummaryParameter.*;

public class UICheckProfilerSummaryTabSteps {

    @Then("I should see Photo on the Summary tab of Profiled page")
    public void iSeePhoto() {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerSummaryTab().isPhotoDisplayed(),
                "Photo is displayed",
                "Photo is NOT displayed");
    }

    private void checkSingleValueParameter(TargetSummaryParameter parameter, String expectedValue) {
        Asserter.getAsserter().softAssertEquals(
                Pages.profilerSummaryTab().getSingleValueParameter(parameter),
                expectedValue,
                parameter.getSummaryParameterName());
    }

    @Then("I should see Target Name ($targetName) on the Summary tab of Profiled page")
    public void checkTargetName(String targetName) {
        checkSingleValueParameter(TARGET_NAME, targetName);
    }

    @Then("I should see Description ($descriptionLines) on the Summary tab of Profiled page")
    public void checkDescription(ExamplesTable descriptionLines) {
        checkSingleValueParameter(DESCRIPTION,
                String.join("\n", ParametersHelper.processExampleTable(descriptionLines)));
    }

    @Then("I should see Category ($category) on the Summary tab of Profiled page")
    public void checkCategory(String category) {
        checkSingleValueParameter(CATEGORY, category);
    }

    @Then("I should see Voice ID ($voiceID) on the Summary tab of Profiled page")
    public void checkVoiceID(String voiceID) {
        checkSingleValueParameter(VOICE_ID, voiceID);
    }

    @Then("I should see Threat Score ($threatScore) on the Summary tab of Profiled page")
    public void checkThreatScore(String threatScore) {
        checkSingleValueParameter(THREAT_SCORE, threatScore);
    }

    @Then("I should see Target Status ($targetStatus) on the Summary tab of Profiled page")
    public void checkTargetStatus(String targetStatus) {
        checkSingleValueParameter(TARGET_STATUS, targetStatus);
    }

    @Then("I should see Expires On ($expiresOn) on the Summary tab of Profiled page")
    public void checkExpiresOn(String expiresOn) {
        Asserter.getAsserter().softAssertEqualsDateWithTolerance(
                Pages.profilerSummaryTab().getExpiresOn(),
                TimeUtils.convertToLocalDateTime(expiresOn),
                new DateTimeTolerance(10, TimeUnit.MINUTES),
                "Expires On");
    }

    @Then("I should see Classification ($classification) on the Summary tab of Profiled page")
    public void checkClassification(String classification) {
        checkSingleValueParameter(CLASSIFICATION, classification);
    }

    @Then("I should see Criminal Record ($criminalRecord) on the Summary tab of Profiled page")
    public void checkCriminalRecord(String criminalRecord) {
        checkSingleValueParameter(CRIMINAL_RECORD, criminalRecord);
    }

    @Then("I should see Files ($files) on the Summary tab of Profiled page")
    public void checkTargetName(ExamplesTable files) {
        AssertUtils.checkLists(
                Pages.profilerSummaryTab().getMultiValueParameter(FILES),
                Arrays.asList(ParametersHelper.processExampleTable(files)),
                FILES.getSummaryParameterName());
    }

    @Then("I should see Last Seen map on the Summary tab of Profiled page")
    public void iSeeLastSeenMap() {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerSummaryTab().isLastSeenMapDisplayed(),
                "Last Seen map is displayed",
                "Last Seen map is NOT displayed");
    }

    private void checkIdentifierDisplayed(String value, String type, Integer hits, Integer mentions) {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerSummaryTab().isIdentifierDisplayed(value, type, hits, mentions),
                "Identifier with expected parameters is displayed",
                "Identifier with expected parameters is NOT displayed");
    }

    @Then("I should see identifier having value ($value) and type ($type) on the Summary tab of Profiled page")
    public void iShouldSeeIdentifierDisplayed(String value, String type) {
        checkIdentifierDisplayed(value, type, null, null);
    }

    @Then("I should see identifier having value ($value), type ($type), hits ($hits) and mentions ($mentions) on the Summary tab of Profiled page")
    public void iShouldSeeIdentifierDisplayed(String value, String type, int hits, int mentions) {
        checkIdentifierDisplayed(value, type, hits, mentions);
    }
}
