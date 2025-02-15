package ae.pegasus.framework.steps.profiler;


import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.constants.profiler.ProfilerWidget;
import org.jbehave.core.annotations.Then;
import ae.pegasus.framework.pages.Pages;


public class UICheckProfilerCommunicationTabSteps {

    private void checkWidgetHeading(ProfilerWidget Widget) {
        Asserter.getAsserter().softAssertTrue(
                Pages.profilerCommunicationTab().isWidgetHeadingCorrect(Widget),
                Widget.getWidgetName()+ " heading is correct",
                Widget.getWidgetName()+ " heading is not correct");
    }



    @Then("I should see Identifiers widget in Communication page with correct heading")
    public void isIdentifiersWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.COMMUNICATION_IDENTIFIERS);
    }

    @Then("I should see Event Types widget in Communication page with correct heading")
    public void isEventTypesWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.EVENT_TYPES);
    }

    @Then("I should see Activity Pattern widget in Communication page with correct heading")
    public void isActivityPatternScoreWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.ACTIVITY_PATTERN);
    }

    @Then("I should see Key Activity Stream in Communication page with correct heading")
    public void isActivityStreamWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.ACTIVITY_STREAM);
    }

    @Then("I should see Most Frequent Contacts widget in Communication page with correct heading")
    public void isMostFrequentContactsWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.MOST_FREQUENT_CONTACTS);
    }

    @Then("I should see Map widget in Communication page with correct heading")
    public void isMapWidgetHeadingCorrect() {
        checkWidgetHeading(ProfilerWidget.MAP);
    }



}
