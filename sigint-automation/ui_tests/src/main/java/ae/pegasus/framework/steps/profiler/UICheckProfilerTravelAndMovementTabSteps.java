package ae.pegasus.framework.steps.profiler;


import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.profiler.ProfilerWidget;
import org.jbehave.core.annotations.Then;
import ae.pegasus.framework.pages.Pages;


public class UICheckProfilerTravelAndMovementTabSteps {

    private void checkWidgetHeading(ProfilerWidget Widget) {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerTravelMovementTab().isWidgetHeadingCorrect(Widget),
                Widget.getWidgetName()+ " heading is correct",
                Widget.getWidgetName()+ " heading is not correct");
    }


    @Then("I should see Activity Stream widget in Travel And Movement page with correct heading")
    public void isActivityStreamWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.TRAVEL_ACTIVITY_STREAM);
    }

    @Then("I should see Map widget in Travel And Movement page with correct heading")
    public void isMapWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.TRAVEL_MAP);
    }



}
