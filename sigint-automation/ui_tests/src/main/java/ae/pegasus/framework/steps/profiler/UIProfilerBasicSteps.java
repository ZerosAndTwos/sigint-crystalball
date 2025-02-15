package ae.pegasus.framework.steps.profiler;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.profiler.ProfilerTab;
import ae.pegasus.framework.constants.profiler.ProfilerWidget;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.cbfinder.TargetAction.ADD_TO_REPORT;
import static ae.pegasus.framework.constants.profiler.ProfilerTab.SUMMARY;
import static ae.pegasus.framework.constants.profiler.ProfilerWidget.*;

public class UIProfilerBasicSteps {

    @Then("I should see Profiler page")
    public void profilerPageDisplayed(){
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerBasePage().isPageDisplayed(),
                "Profiler page is displayed",
                "Profiler page is NOT displayed");
    }

    @When("I open Summary tab on the Profiled page")
    public void iOpenSummaryTab() {
        Pages.profilerSummaryTab().openTab();
    }

    private void checkTabSelected(ProfilerTab tab) {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerBasePage().isTabSelected(tab),
                "'" + tab.getTabName() + "'-tab is selected",
                "'" + tab.getTabName() + "'-tab is NOT selected");
    }

    @Then("I should see Summary tab selected on the Profiled page")
    public void summaryTabSelected(){
        checkTabSelected(SUMMARY);
    }

    private void checkTabDisplayed(ProfilerTab tab) {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerBasePage().isTabDisplayed(tab),
                "'" + tab.getTabName() + "'-tab is displayed",
                "'" + tab.getTabName() + "'-tab is NOT displayed");
    }

    @Then("I should see Profile Details tab on the Profiled page")
    public void profileDetailsTabDisplayed(){
        checkTabDisplayed(ProfilerTab.PROFILE_DETAILS);
    }

    @Then("I should see Communication tab on the Profiled page")
    public void communicationTabDisplayed(){
        checkTabDisplayed(ProfilerTab.COMMUNICATION);
    }

    @Then("I should see Open Data tab on the Profiled page")
    public void openDataTabDisplayed(){
        checkTabDisplayed(ProfilerTab.OPEN_DATA);
    }

    @Then("I should see Travel and Movement tab on the Profiled page")
    public void travelAndMovementTabDisplayed(){
        checkTabDisplayed(ProfilerTab.TRAVEL_AND_MOVEMENT);
    }

    @Then("I should see Mentions tab on the Profiled page")
    public void mentionsTabDisplayed(){
        checkTabDisplayed(ProfilerTab.MENTIONS);
    }

    @Then("I should see Network tab on the Profiled page")
    public void networkTabDisplayed(){
        checkTabDisplayed(ProfilerTab.NETWORK);
    }

    @Then("I should see Attachments tab on the Profiled page")
    public void attachmentsTabDisplayed(){
        checkTabDisplayed(ProfilerTab.ATTACHMENTS);
    }

    @Then("I should see Audit Trail tab on the Profiled page")
    public void targetActivityTabDisplayed(){
        checkTabDisplayed(ProfilerTab.AUDIT_TRAIL);
    }

    @Then("I should see Insights tab on the Profiled page")
    public void insightTabDisplayed(){
        checkTabDisplayed(ProfilerTab.INSIGHTS);
    }


    private void createNewReportForWidget(ProfilerWidget widget) {
        Pages.profilerBasePage().performActionForWidget(widget, ADD_TO_REPORT);
        Pages.attachToReportPage().createNewReport();
    }

    private void addWidgetToExistingReport(ProfilerWidget widget, String reportName) {
        Pages.profilerBasePage().performActionForWidget(widget, ADD_TO_REPORT);
        Pages.attachToReportPage().addToExistingReport(reportName);
    }

    @Given("I create report for Target Summary widget on the Profiled page")
    public void createReportForTargetSummary() {
        createNewReportForWidget(TARGET_SUMMARY);
    }

    @Given("I add Target Summary widget to existing report ($reportName) on the Profiled page")
    public void addTargetSummaryToReport(String reportName) {
        addWidgetToExistingReport(TARGET_SUMMARY, reportName);
    }

    @Given("I create report for Last Seen widget on the Profiled page")
    public void createReportForLastSeen() {
        createNewReportForWidget(LAST_SEEN);
    }

    @Given("I add Last Seen widget to existing report ($reportName) on the Profiled page")
    public void addLastSeenToReport(String reportName) {
        addWidgetToExistingReport(LAST_SEEN, reportName);
    }

    @Given("I create report for Identifiers widget on the Profiled page")
    public void createReportForIdentifiers() {
        createNewReportForWidget(IDENTIFIERS);
    }

    @Given("I add Identifiers widget to existing report ($reportName) on the Profiled page")
    public void addIdentifiersToReport(String reportName) {
        addWidgetToExistingReport(IDENTIFIERS, reportName);
    }

    @Given("I create report for RFIs widget on the Profiled page")
    public void createReportForRFIs() {
        createNewReportForWidget(RFIS);
    }

    @Given("I add RFIs widget to existing report ($reportName) on the Profiled page")
    public void addRFIsToReport(String reportName) {
        addWidgetToExistingReport(RFIS, reportName);
    }

    @When("I click on Summary tab on the Profiled page")
    public void iClickOnSummaryTab(){
        Pages.profilerBasePage().openTab(ProfilerTab.SUMMARY);
    }

    @When("I click on Profile Details tab on the Profiled page")
    public void iClickOnProfileDetailsTab(){
        Pages.profilerBasePage().openTab(ProfilerTab.PROFILE_DETAILS);
    }



    @When("I click on Communication tab on the Profiled page")
    public void iClickOnCommunicationTab(){
        Pages.profilerBasePage().openTab(ProfilerTab.COMMUNICATION);
    }

    @When("I click on Open Data tab on the Profiled page")
    public void iClickOnOpenDataTab(){
        Pages.profilerBasePage().openTab(ProfilerTab.OPEN_DATA);
    }

    @When("I click on Travel and Movement tab on the Profiled page")
    public void iClickOnTravelAndMovementab(){
        Pages.profilerBasePage().openTab(ProfilerTab.TRAVEL_AND_MOVEMENT);
    }

    @When("I click on Mentions tab on the Profiled page")
    public void iClickOnMentionsab(){
        Pages.profilerBasePage().openTab(ProfilerTab.MENTIONS);
    }

    @When("I click on Network tab on the Profiled page")
    public void iClickOnNetworkTab(){
        Pages.profilerBasePage().openTab(ProfilerTab.NETWORK);
    }

    @When("I click on Attachments tab on the Profiled page")
    public void iClickOnAttachmentsab(){
        Pages.profilerBasePage().openTab(ProfilerTab.ATTACHMENTS);
    }

    @When("I click on Audit Trail tab on the Profiled page")
    public void iClickOnTargetActivityTab(){
        Pages.profilerBasePage().openTab(ProfilerTab.AUDIT_TRAIL);
    }

    @When("I click on Insights tab on the Profiled page")
    public void iClickOnInsightsTab(){
        Pages.profilerBasePage().openTab(ProfilerTab.INSIGHTS);
    }
}
