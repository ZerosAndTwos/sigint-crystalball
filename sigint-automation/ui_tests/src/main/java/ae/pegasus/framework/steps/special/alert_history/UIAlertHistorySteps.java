package ae.pegasus.framework.steps.special.alert_history;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.utils.TimeUtils;
import ae.pegasus.framework.context.Context;
import ae.pegasus.framework.elements.special.alert_history.ToDoListEntry;
import org.jbehave.core.annotations.Then;
import ae.pegasus.framework.pages.Pages;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class UIAlertHistorySteps {
    @Then("I should see assign of ($recordsNum) record(s) by ($assignerLogin) at row ($rowNumber) of ($entryDate) To Do List entry on the Alerting History page")
    public void iShouldSeeQueueWithStandardActionSet(int recordsNum, String assignerLogin, int rowNumber, String entryDate) {
        LocalDateTime dateOfEntry = TimeUtils.convertToLocalDateTime(entryDate);
        ToDoListEntry toDoEntry = Pages.toDoListTab().getListEntryForDate(dateOfEntry);
        Asserter.getAsserter().softAssertEquals(
                toDoEntry.getRowText(rowNumber),
                assignerLogin.toLowerCase() + " has assigned " + recordsNum + " record to you for assessment.",
                "To Do List entry's text");

        Asserter.getAsserter().softAssertEqualsDateWithTolerance(
                toDoEntry.getRowDate(rowNumber),
                dateOfEntry,
                Context.getContext().getToleranceBasedOnScenarioStartTime(TimeUnit.MINUTES),
                "To Do List entry's date");
    }
}
