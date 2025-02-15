package ae.pegasus.framework.steps.special.saved_searches;

import ae.pegasus.framework.assertion.Asserter;
import ae.pegasus.framework.utils.ParametersHelper;
import ae.pegasus.framework.utils.TimeUtils;
import ae.pegasus.framework.constants.special.saved_search.SavedSearchScheduleRepeatInterval;
import ae.pegasus.framework.constants.special.saved_search.SearchingCriteriaField;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.model.ExamplesTable;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.utils.AssertUtils;

import static ae.pegasus.framework.constants.special.saved_search.SavedSearchButton.SAVE_SEARCH;
import static ae.pegasus.framework.constants.special.saved_search.SavedSearchProperty.*;
import static ae.pegasus.framework.constants.special.saved_search.SearchingCriteriaField.*;

public class UIEditSavedSearchSteps {
    @Then("I should see Edit Saved Search page")
    public void iShouldSeePage() {
        Asserter.getAsserter().softAssertTrue(
                Pages.editSavedSearchPage().isPageDisplayed(),
                "Edit Saved Search page is displayed",
                "Edit Saved Search page is NOT displayed"
        );
    }

    private void checkSearchingCriteria(SearchingCriteriaField field, String...expectedValue) {
        AssertUtils.checkArrays(
                Pages.editSavedSearchPage().getSearchingCriteria(field),
                expectedValue,
                "Searching Criteria '" + field.getFieldName() +"' on the Edit Saved Search page");
    }

    @Then("I should see Query ($searchQuery) as Searching Criteria on the Edit Saved Search page")
    public void iShouldSeeSearchQuery(String searchQuery) {
        checkSearchingCriteria(QUERY, searchQuery);
    }

    @Then("I should see Source Type ($sourceType) as Searching Criteria on the Edit Saved Search page")
    public void iShouldSeeSourceType(ExamplesTable sourceType) {
        checkSearchingCriteria(SOURCE_TYPE, ParametersHelper.processExampleTable(sourceType));
    }

    @Then("I should see Data Source ($dataSource) as Searching Criteria on the Edit Saved Search page")
    public void iShouldSeeDataSource(ExamplesTable dataSource) {
        checkSearchingCriteria(DATA_SOURCE, ParametersHelper.processExampleTable(dataSource));
    }

    @Then("I should see Object Type ($objectType) as Searching Criteria on the Edit Saved Search page")
    public void iShouldSeeObjectType(ExamplesTable objectType) {
        checkSearchingCriteria(OBJECT_TYPE, ParametersHelper.processExampleTable(objectType));
    }

    @Then("I should see Record Type ($recordType) as Searching Criteria on the Edit Saved Search page")
    public void iShouldSeeRecordType(ExamplesTable recordType) {
        checkSearchingCriteria(RECORD_TYPE, ParametersHelper.processExampleTable(recordType));
    }

    @Given("I set Name to ($name) on the Edit Saved Search page")
    public void iSetName(String name) {
        Pages.editSavedSearchPage().setPropertyValue(NAME, name);
    }

    @Given("I set Description to ($description) on the Edit Saved Search page")
    public void iSetDescription(ExamplesTable description) {
        Pages.editSavedSearchPage().setPropertyMultiValues(
                DESCRIPTION,
                ParametersHelper.processExampleTable(description)
        );
    }

    @Given("I set Classification to ($classification) on the Edit Saved Search page")
    public void iSetClassification(String classification) {
        Pages.editSavedSearchPage().setPropertyValue(CLASSIFICATION, classification);
    }

    @Given("I set Organization Unit to ($organizationUnit) on the Edit Saved Search page")
    public void iSetOrganizationUnit(ExamplesTable organizationUnit) {
        Pages.editSavedSearchPage().setPropertyMultiValues(
                ORGANIZATION_UNIT,
                ParametersHelper.processExampleTable(organizationUnit)
        );
    }

    @Given("I set Files to ($files) on the Edit Saved Search page")
    public void iSetFiles(ExamplesTable files) {
        Pages.editSavedSearchPage().setPropertyMultiValues(
                FILES,
                ParametersHelper.processExampleTable(files)
        );
    }

    @Given("I setup Scheduled Search to start from ($fromDate) every ($repeatInterval) till ($endDate) on the Edit Saved Search page")
    public void iSetupScheduledSearch(String fromDate, String repeatInterval, String endDate){
        Pages.editSavedSearchPage().setupSchedule(
                TimeUtils.convertToLocalDateTime(fromDate),
                SavedSearchScheduleRepeatInterval.getEnumByName(repeatInterval),
                TimeUtils.convertToLocalDateTime(endDate)
        );
    }

    @Given("I save search on the Edit Saved Search page")
    public void iSaveSearch() {
        Pages.editSavedSearchPage().clickButton(SAVE_SEARCH);
    }

    @Given("I close Edit Saved Search page")
    public void iCloseSavedSearch() {
        Pages.editSavedSearchPage().closeDialog();
    }
}
