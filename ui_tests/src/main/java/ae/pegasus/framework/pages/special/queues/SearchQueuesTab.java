package ae.pegasus.framework.pages.special.queues;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.assertion.DateTimeTolerance;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.queue.SearchQueueAction;
import ae.pegasus.framework.constants.queue.SearchQueueTab;
import ae.pegasus.framework.constants.queue.SearchQueuesColon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.hidden;

public class SearchQueuesTab extends QueuesBasePage {

    private final String QUEUE_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";

    private ElementsCollection getTableHeader() {
        return getSpecialPageBase().$$x(".//div[@class='pg-head-row-small']/div");
    }

    private int getColonIndex(SearchQueuesColon colon) {
        int index = 0;
        for (SelenideElement item : getTableHeader()) {
            index++;
            if (item.getText().trim().equalsIgnoreCase(colon.getColonName())) {
                return index;
            }
        }
        throw new IllegalArgumentException("Search Queues Colon with name '" + colon.getColonName() + "' was not found");
    }


    private SelenideElement getColon(SearchQueuesColon colon, SelenideElement tableRow) {
        return tableRow.$x("./div[" + getColonIndex(colon) + "]");
    }

    private String getColonValue(SearchQueuesColon colon, SelenideElement tableRow) {
        switch (colon) {
            case ACTIONS:
                throw new IllegalArgumentException("Search Queues Colon '" + colon.getColonName() + "' is not applicable for getValue");
            default:
                return getColon(colon, tableRow).getText().trim();
        }
    }

    private SelenideElement getAction(SelenideElement tableRow, SearchQueueAction button) {
        getColon(SearchQueuesColon.ACTIONS, tableRow).hover();
        return tableRow.$x(".//pg-btn[@pg-tooltip='" + button.getActionName() + "']");
    }

    private ElementsCollection getActions(SelenideElement tableRow) {
        return tableRow. $$x(".//pg-btn");
    }

    private ElementsCollection getTableRows() {
        return getSpecialPageBase().$$x(".//div[@class='pg-tbody']/div");
    }

    private String getVerifiedParameterDescription(SearchQueuesColon colon) {
        return "Value of " + colon.getColonName() + " on Search Queues page";
    }

    private void checkRowStringValue(SearchQueuesColon colon, SelenideElement tableRow, String expectedValue, boolean ignoreEmptyValue) {
        if (!(ignoreEmptyValue && expectedValue.isEmpty())) {
            Asserter.getAsserter().softAssertEquals(getColonValue(colon, tableRow),
                    expectedValue,
                    getVerifiedParameterDescription(colon));
        }
    }



    private SelenideElement getProgressBar(SelenideElement row) {
        return getColon(SearchQueuesColon.STATUS, row).$x(".//pg-progress-bar");
    }

    private SelenideElement getRow(int rowNumber) {
        if (rowNumber <= 0) {
            throw new IllegalArgumentException("Row number should be more 0 but it is '" + rowNumber + "'");
        }
        openQueuesTab(SearchQueueTab.SEARCH_QUEUE_MONITOR);
        ElementsCollection rows = getTableRows();
        if (rows.size() < rowNumber) {
            throw new IllegalStateException("Unable to process row '" + rowNumber + "' since only '" + rows.size() + "' was found");
        }
        return rows.get(rowNumber - 1);
    }

    /**
     * Checks if row has provided parameters' values and actions
     * @param rowNumber number of row to verify (<b>started from 1</b>)
     * @param queryExpectedValue expected value of {@link SearchQueuesColon#QUERY}
     * @param typeExpectedValue expected value of {@link SearchQueuesColon#TYPE}
     * @param statusExpectedValue expected value of {@link SearchQueuesColon#STATUS}
     * @param createdAtExpectedValue expected value of {@link SearchQueuesColon#CREATED_AT}
     * @param ignoreEmptyExpectedValues should empty values be ignored (i.e. not verify appropriated {@link SearchQueuesColon}
     * @param actionsToCheck which of actions defined in {@link SearchQueueAction} expected to be displayed (if any)
     */
    public void checkRowValuesAndActions(int rowNumber, String queryExpectedValue, String typeExpectedValue, String statusExpectedValue, LocalDateTime createdAtExpectedValue, boolean ignoreEmptyExpectedValues, SearchQueueAction...actionsToCheck) {
        SelenideElement row = getRow(rowNumber);
        checkRowStringValue(SearchQueuesColon.QUERY, row, queryExpectedValue, ignoreEmptyExpectedValues);
        checkRowStringValue(SearchQueuesColon.TYPE, row, typeExpectedValue, ignoreEmptyExpectedValues);
      // need to find a better solution
       // checkRowStringValue(SearchQueuesColon.STATUS, row, statusExpectedValue, ignoreEmptyExpectedValues);
        if (createdAtExpectedValue != null) {
            LocalDateTime createdAtActualValue = LocalDateTime.parse(getColonValue(SearchQueuesColon.CREATED_AT, row), DateTimeFormatter.ofPattern(QUEUE_DATE_TIME_FORMAT));
            Asserter.getAsserter().softAssertEqualsDateWithTolerance(createdAtActualValue, createdAtExpectedValue, new DateTimeTolerance(600, TimeUnit.MINUTES),
                    getVerifiedParameterDescription(SearchQueuesColon.CREATED_AT));
        } else {
            if (!ignoreEmptyExpectedValues) {
                throw new IllegalArgumentException(SearchQueuesColon.CREATED_AT.getColonName() + " is not expected to be empty");
            }
        }

        for (SearchQueueAction action : actionsToCheck) {
            Asserter.getAsserter().softAssertTrue(getAction(row, action).isDisplayed(),
                    "Action button '" + action.getActionName() + "' is displayed",
                    "Action button '" + action.getActionName() + "' is NOT displayed");
        }
        if (actionsToCheck.length > 0) {
            Asserter.getAsserter().softAssertEquals(
                    getActions(row).size(),
                    actionsToCheck.length,
                    "Number of action buttons");
        }
    }

    /**
     * Performs action for row
     * @param rowNumber number of row to perform action (<b>started from 1</b>)
     * @param actionToPerform which of actions defined in {@link SearchQueueAction} to be performed
     */
    public void performActionForRow(int rowNumber, SearchQueueAction actionToPerform) {
        SelenideElement row = getRow(rowNumber);
        getAction(row, actionToPerform).click();
    }

    /**
     * Performs click to the cell of the search queue table
     * @param rowNumber number of row where to click (<b>started from 1</b>)
     * @param colon name of colon defined in {@link SearchQueuesColon} where to click
     */
    public void clickToCell(int rowNumber, SearchQueuesColon colon) {
        SelenideElement row = getRow(rowNumber);
        getColon(colon, row).click();
    }

    /**
     * Waits until progress bar disappear in the provided row
     * @param rowNumber number of row where progress bar appeared (<b>started from 1</b>)
     */
    public void waitUntilProgressBarForRowComplete(int rowNumber) {
        SelenideElement row = getRow(rowNumber);
        while (getProgressBar(row).isDisplayed() || getColonValue(SearchQueuesColon.STATUS, row).equalsIgnoreCase("Submitted")) {
            getProgressBar(row).shouldBe(hidden);
            Selenide.sleep(100);
        }
    }
}
