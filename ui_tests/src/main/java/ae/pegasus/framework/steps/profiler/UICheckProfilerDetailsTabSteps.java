package ae.pegasus.framework.steps.profiler;


import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.profiler.ProfilerWidget;
import ae.pegasus.framework.constants.profiler.TargetSummaryParameter;
import org.jbehave.core.annotations.Then;
import ae.pegasus.framework.pages.Pages;


public class UICheckProfilerDetailsTabSteps {

    private void checkWidgetHeading(ProfilerWidget Widget) {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerDetailsTab().isWidgetHeadingCorrect(Widget),
                Widget.getWidgetName()+ " heading is correct",
                Widget.getWidgetName()+ " heading is not correct");
    }

    private void checkIdentifierDisplayed(String parameter, String value, Integer hits, Integer mentions) {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerDetailsTab().isAttributeDisplayed(parameter,value),
                "Key Attributes with expected parameters is displayed",
                "Key Attributes with expected parameters is NOT displayed");
    }

    @Then("I should see Images widget in Profile Details page with correct heading")
    public void isImageWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.IMAGE);
    }

    @Then("I should see Voice ID widget in Profile Details page with correct heading")
    public void isVoiceIDWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.VOICE_ID);
    }
    @Then("I should see Threat Score widget in Profile Details page with correct heading")
    public void isThreatScoreWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.THREAT_SCORE);
    }

    @Then("I should see Key Attribute widget in Profile Details page with correct heading")
    public void isKeyAttributeWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.KEY_ATTRIBUTES);
    }

    @Then("I should see EID Information widget in Profile Details page with correct heading")
    public void isEIDInformationWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.EID_INFORMATION);
    }

    @Then("I should see correct EID name ($EIDname) in the EID Information widget in Profile Details tab")
    public void isEIDNameCorrect(String EIDname) {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerDetailsTab().isEIDPersonNameDisplayed(EIDname),
                "the EID name is" +EIDname+ "as expcted",
                "EID name should be" +EIDname+ "but it not the case");


    }

    @Then("I should see correct EID number ($EIDnumber) in the EID Information widget in Profile Details tab")
    public void isEIDNnumberDisplayed(String EIDnumber) {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerDetailsTab().checkEIDValueIsDiaplyed(EIDnumber),
                "the EID name is" +EIDnumber+ "as expcted",
                "EID name should be" +EIDnumber+ "but it not the case");


    }

    @Then("I should see Threat Score ($searchCriteria) in the Threat Score widget in Profile Details tab")
    public void isThreatScoreCorrect(String score) {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerDetailsTab().isThreadScoreCorrect(score),
                "the threat score is" +score+ "as expcted",
                "the threat score is not" +score+ "as expcted");


    }

    @Then("I should see key Attributes having key ($key) and value ($value) on the Profile Details tab of Profiled page")
    public void iShouldSeeIdentifierDisplayed(String key, String value) {
        checkIdentifierDisplayed(key, value, null, null);
    }



}
