package ae.pegasus.framework.steps.cbfinder;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.cbfinder.CreateAction;
import ae.pegasus.framework.constants.cbfinder.TargetAction;
import ae.pegasus.framework.constants.cbfinder.UpdateAction;
import ae.pegasus.framework.context.Context;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import ae.pegasus.framework.pages.Pages;

public class UICBFinderSteps {

    @Then("I should see CB Finder page")
    public void iShouldSeeCBFinderPage() {
        Asserter.getAsserter().softAssertTrue(Pages.cbFinderBasePage().isPageDisplayed(),
                "CB Finder page is displayed",
                "CB Finder page is NOT displayed");
    }

    @Given("I start creation of new file in the CB Finder")
    public void iStartNewFileCreation() {
        Pages.cbFinderBasePage().performAction(CreateAction.CREATE_FILE);
    }

    @Given("I save new file created in the CB Finder")
    public void iSaveNewFile() {
        Pages.cbFinderNewFilePage().saveCreatedFile();
    }

    @When("I select file with Name ($name) in the CB Finder")
    public void iSelectFile(String name) {
        Pages.cbFinderExistEntityPage().selectFile(name);
    }

    @When("I delete file which is currently selected in the CB Finder")
    public void iDeleteFile() {
        Pages.cbFinderExistEntityPage().performAction(UpdateAction.DELETE);
    }

    @When("I open operator report with Subject ($subject) in file/case which is currently selected in the CB Finder")
    public void iOpenReportWithSubject(String subject) {
        Pages.cbFinderExistEntityPage().openReportOrRequestByCardSubject(subject);
        Pages.cbFinderExistEntityPage().waitSelectedItemLoading();
    }

    @When("I open request for information with Subject ($subject) in file/case which is currently selected in the CB Finder")
    public void iOpenRequestForInformationWithSubject(String subject) {
        Pages.cbFinderExistEntityPage().openReportOrRequestByCardSubject(subject);
        Pages.cbFinderExistEntityPage().waitSelectedItemLoading();
    }

    @When("I open report with Report number from the context in file which is currently selected in the CB Finder")
    public void iOpenReportWithId() {
        Pages.cbFinderExistEntityPage().openReportByReportNumber(Context.getContext().getReportNumber());
        Pages.cbFinderExistEntityPage().waitSelectedItemLoading();
    }

    @Then("I check if save search by name ($name) is present under case/file which is currently selected in the CB Finder")
    public void iCheckSaveSearchIsPresent(String name) {
        Asserter.getAsserter().softAssertTrue(
                Pages.cbFinderExistEntityPage().isSaveSearchByNamePresent(name), "the save search was present and enabled", "the save search was not present");
    }

    @Then("I check if target by name ($name) is present under case/file which is currently selected in the CB Finder")
    public void iCheckTargetIsPresent(String name) {
        Asserter.getAsserter().softAssertTrue(
                Pages.cbFinderExistEntityPage().isTargetByNamePresent(name), "the target was present and enabled", "the target was not present");
    }

    @When("I open save search by name ($name) is present under case/file which is currently selected in the CB Finder")
    public void iOpenSaveSearchByName(String name) {
        Pages.cbFinderExistEntityPage().openSaveSearchByName(name);
    }

    @When("I open target by name ($name) is present under case/file which is currently selected in the CB Finder")
    public void iOpenTargetByName(String name) {
        Pages.cbFinderExistEntityPage().openTargetByName(name);
    }

    @Given("I create a report for target which is currently selected in the CB Finder")
    public void addTargetToReport() {
        Pages.cbFinderTargetPage().performAction(TargetAction.ADD_TO_REPORT);
        Pages.attachToReportPage().createNewReport();
    }

    @Given("I start creation of a case from file which is currently selected in the CB Finder")
    public void iCreateCase() {
        Pages.cbFinderExistEntityPage().performAction(CreateAction.CREATE_CASE);
    }

    @Given("I save new case created in the CB Finder")
    public void iSaveNewCase() {
        Pages.cbFinderNewCasePage().saveCreatedCase();
    }

    @When("I select case with Name ($name) in the CB Finder")
    public void iSelectCase(String name) {
        Pages.cbFinderExistEntityPage().selectCase(name);
    }

    @When("I delete case which is currently selected in the CB Finder")
    public void iDeleteCase() {
        Pages.cbFinderExistEntityPage().performAction(UpdateAction.DELETE);
    }

    @Given("I start creation of a RFI from file/case which is currently selected in the CB Finder")
    public void iCreateRFI() {
        Pages.cbFinderExistEntityPage().performAction(CreateAction.CREATE_RFI);
    }


    @Given("I Collapse the CB Finder view")
    public void iCollapseCBFinderView() {
        Pages.cbFinderExistEntityPage().clickCollapseView();
    }

    @Given("I Expand the CB Finder view")
    public void iExpandCBFinderView() {
        Pages.cbFinderExistEntityPage().clickExpandView();
    }

}
