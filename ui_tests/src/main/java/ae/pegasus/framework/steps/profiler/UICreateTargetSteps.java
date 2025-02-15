package ae.pegasus.framework.steps.profiler;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.utils.ParametersHelper;
import ae.pegasus.framework.utils.TimeUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.model.ExamplesTable;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.profiler.CreateTargetField.*;

public class UICreateTargetSteps {

    @Then("I should see Create Target page")
    public void iShouldSeeCreateTargetPage() {
        Asserter.getAsserter().softAssertTrue(Pages.createTargetPage().isPageDisplayed(),
                "Create Target page is displayed",
                "Create Target page is NOT displayed");
    }

    @Given("I set Target's Name to $targetName")
    public void iSetTargetName(String targetName) {
        Pages.createTargetPage().setSingleValueData(NAME, targetName);
    }

    @Given("I set Target's Description to $targetDescriptionLines")
    public void iSetTargetDescription(ExamplesTable targetDescriptionLines) {
        Pages.createTargetPage().setMultiValueData(DESCRIPTION, ParametersHelper.processExampleTable(targetDescriptionLines));
    }

    @Given("I set Target's Type to $targetType")
    public void iSetTargetType(String targetType) {
        Pages.createTargetPage().setSingleValueData(TYPE, targetType);
    }

    @Given("I set Target's Classification to $targetClassification")
    public void iSetTargetClassification(String targetClassification) {
        Pages.createTargetPage().setSingleValueData(CLASSIFICATION, targetClassification);
    }

    @Given("I set Target's Organization Units to $targetOrgUnit")
    public void iSetTargetOrgUnits(ExamplesTable targetOrgUnit) {
        Pages.createTargetPage().setMultiValueData(ORGANIZATION_UNIT, ParametersHelper.processExampleTable(targetOrgUnit));
    }

    @Given("I set Target's Category to $targetCategory")
    public void iSetTargetCategory(String targetCategory) {
        Pages.createTargetPage().setSingleValueData(CATEGORY, targetCategory);
    }

    @Given("I set Target's Assigned Teams to $targetAssignedTeams")
    public void iSetTargetAssignedTeams(ExamplesTable targetAssignedTeams) {
        Pages.createTargetPage().setMultiValueData(ASSIGNED_TEAMS, ParametersHelper.processExampleTable(targetAssignedTeams));
    }

    @Given("I set Target's Assignment Priority to $targetAssignmentPriority")
    public void iSetTargetAssignmentPriority(String targetAssignmentPriority) {
        Pages.createTargetPage().setSingleValueData(ASSIGNMENT_PRIORITY, targetAssignmentPriority);
    }

    @Given("I set Target's File to $targetFile")
    public void iSetTargetFile(ExamplesTable targetFile) {
        Pages.createTargetPage().setMultiValueData(FILE, ParametersHelper.processExampleTable(targetFile));
    }

    @Given("I set Target's Status to $targetStatus")
    public void iSetTargetStatus(String targetStatus) {
        Pages.createTargetPage().setSingleValueData(TARGET_STATUS, targetStatus);
    }

    @Given("I set Target's Active Until to $targetActiveUntil")
    public void iSetActiveUntil(String targetActiveUntil) {
        Pages.createTargetPage().setActiveUntil(TimeUtils.convertToLocalDateTime(targetActiveUntil));
    }

    @Given("I set Target's Threat Score Likelihood to $targetTSL")
    public void iSetTargetTSL(String targetTSL) {
        Pages.createTargetPage().setSingleValueData(THREAT_SCORE_LIKELIHOOD, targetTSL);
    }

    @Given("I set Target's Threat Score Impact to $targetTSI")
    public void iSetTargetTSI(String targetTSI) {
        Pages.createTargetPage().setSingleValueData(THREAT_SCORE_IMPACT, targetTSI);
    }

    @Given("I set Target's Criminal Record to $targetCriminalRecord")
    public void iSetCriminalRecord(String targetCriminalRecord) {
        Pages.createTargetPage().setSingleValueData(CRIMINAL_RECORD, targetCriminalRecord);
    }

    @Given("I set Target's RFI to $targetRFI")
    public void iSetTargetRFI(ExamplesTable targetRFI) {
        Pages.createTargetPage().setMultiValueData(RFI, ParametersHelper.processExampleTable(targetRFI));
    }

    @Given("I create Target")
    public void iCreateTarget() {
        Pages.createTargetPage().createTarget();
    }

    @Given("I create Target and open profile")
    public void iCreateTargetAndOpenProfile() {
        Pages.createTargetPage().createTargetAndOpenProfile();
    }

    @Given("I cancel Target's creation")
    public void iCancelTarget() {
        Pages.createTargetPage().cancelTargetCreation();
    }
}
