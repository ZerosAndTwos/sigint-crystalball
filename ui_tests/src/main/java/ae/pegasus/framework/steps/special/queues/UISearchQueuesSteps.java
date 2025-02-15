package ae.pegasus.framework.steps.special.queues;

import ae.pegasus.framework.utils.TimeUtils;
import ae.pegasus.framework.constants.queue.SearchQueuesColon;
import ae.pegasus.framework.constants.special.modal_dialog.ModalDialogButton;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import ae.pegasus.framework.pages.Pages;

import java.time.LocalDateTime;

import static ae.pegasus.framework.constants.queue.SearchQueueAction.REMOVE;
import static ae.pegasus.framework.constants.queue.SearchQueueAction.RERUN;

public class UISearchQueuesSteps {
    @Then("I should see search queue $queueNumber: Query ($query), Type ($type), Status ($status), Created At ($createdAt) with standard actions set")
    public void iShouldSeeQueueWithStandardActionSet(int queueNumber, String query, String type, String status, String createdAt) {
        LocalDateTime createAtDate = null;
        if (!createdAt.isEmpty()) {
            createAtDate = TimeUtils.convertToLocalDateTime(createdAt);
        }
        Pages.searchQueuesTab().checkRowValuesAndActions(queueNumber, query, type, status, createAtDate, true,
                RERUN, REMOVE);
    }

    @When("I perform Rerun action for the search queue $queueNumber")
    public void iPushRerunButton(int queueNumber) {
        Pages.searchQueuesTab().performActionForRow(queueNumber, RERUN);
        Pages.modalDialogPage().clickButton(ModalDialogButton.YES, 600);
        Pages.mainPage().openQueuesPage();
    }

    @When("I perform Remove action for the search queue $queueNumber")
    public void iPushRemoveButton(int queueNumber) {
        Pages.searchQueuesTab().performActionForRow(queueNumber, REMOVE);
        Pages.modalDialogPage().clickButton(ModalDialogButton.YES, 600);
        Pages.mainPage().openQueuesPage();
    }

    @Given("I wait until progress bar for the search queue $queueNumber disappear")
    public void iWaitUntilProgressBarDisappear(int queueNumber) {
        Pages.searchQueuesTab().waitUntilProgressBarForRowComplete(queueNumber);
    }

    @When("I click to the Query cell of the search queue $queueNumber")
    public void iClickToTheQueryCell(int queueNumber) {
        Pages.searchQueuesTab().clickToCell(queueNumber, SearchQueuesColon.QUERY);
        Pages.searchPage().waitForPageLoading(900);
    }
}
