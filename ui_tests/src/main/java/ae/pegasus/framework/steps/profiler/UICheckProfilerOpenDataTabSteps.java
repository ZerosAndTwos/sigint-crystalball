package ae.pegasus.framework.steps.profiler;


import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.profiler.ProfilerWidget;
import org.jbehave.core.annotations.Then;
import ae.pegasus.framework.pages.Pages;


public class UICheckProfilerOpenDataTabSteps {

    private void checkWidgetHeading(ProfilerWidget Widget) {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerTravelMovementTab().isWidgetHeadingCorrect(Widget),
                Widget.getWidgetName()+ " heading is correct",
                Widget.getWidgetName()+ " heading is not correct");
    }

    @Then("I should see Account widget in Open Data tab with correct heading")
    public void isAccountWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.OPEN_DATA_ACCOUNTS);
    }

    @Then("I should see Activity Stream widget in Open Data tab with correct heading")
    public void isActivityStreamsWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.OPEN_DATA_ACTIVITY_STREAM);

        Pages.actionsWithSelected().addSelectedToReport();
    }





}
