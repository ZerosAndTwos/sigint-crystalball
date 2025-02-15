package ae.pegasus.framework.steps.profiler;


import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.profiler.ProfilerWidget;
import org.jbehave.core.annotations.Then;
import ae.pegasus.framework.pages.Pages;


public class UICheckProfilerInsightsBetaTabSteps {

    private void checkWidgetHeading(ProfilerWidget Widget) {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerTravelMovementTab().isWidgetHeadingCorrect(Widget),
                Widget.getWidgetName()+ " heading is correct",
                Widget.getWidgetName()+ " heading is not correct");
    }

    @Then("I should see Threat Score Prediction widget in Insights Beta page with correct heading")
    public void isThreatScorePredictionWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.THREAT_SCORE_PREDICTION);
    }

    @Then("I should see Suggested Identifiers widget in Insights Beta page with correct heading")
    public void isSuggestedIdentifiersWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.SUGGESTED_IDENTIFIERS);
    }

    @Then("I should see Similar Targets widget in Insights Beta with correct heading")
    public void isSimilarTargetsHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.SIMILAR_TARGETS);
    }



}
