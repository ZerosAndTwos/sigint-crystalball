package ae.pegasus.framework.steps.special.search_filter;

import ae.pegasus.framework.utils.ParametersHelper;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.model.ExamplesTable;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.special.search_filter.FilterSetting.*;

public class UISearchFilterSetupSteps {

    @Given("I set Data Source to ($settingsValues) on the Search Filter page")
    public void iSetDataSource(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(DATA_SOURCE, ParametersHelper.processExampleTable(settingsValues));
    }

    @Given("I set Record Type to ($settingsValues) on the Search Filter page")
    public void iSetRecordType(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(RECORD_TYPE, ParametersHelper.processExampleTable(settingsValues));
    }

    @Given("I set Status Type to ($settingsValues) on the Search Filter page")
    public void iSetStatusType(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(STATUS, ParametersHelper.processExampleTable(settingsValues));
    }

    @Given("I set File or Case Type to ($settingsValues) on the Search Filter page")
    public void iSetFileCaseName(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(FILECASE, ParametersHelper.processExampleTable(settingsValues));
    }

    @Given("I set Data Sub-Source to ($settingsValues) on the Search Filter page")
    public void iSetDataSubSource(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(DATA_SUB_SOURCE, ParametersHelper.processExampleTable(settingsValues));
    }

    @Given("I select Include Spam on the Search Filter page")
    public void iSelectIncludeSpam() {
        Pages.searchFilterPage().setNoValueSetting(INCLUDE_SPAM);
    }

    @Given("I deselect Include Spam on the Search Filter page")
    public void iDeselectIncludeSpam() {
        Pages.searchFilterPage().clearSetting(INCLUDE_SPAM);
    }

    @Given("I set Target to ($settingsValues) on the Search Filter page")
    public void iSetTarget(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(TARGET, ParametersHelper.processExampleTable(settingsValues));
    }

    @Given("I set File to ($settingsValues) on the Search Filter page")
    public void iSetFile(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(FILE, ParametersHelper.processExampleTable(settingsValues));
    }

    @Given("I select Include File Children on the Search Filter page")
    public void iSelectIncludeFileChildren() {
        Pages.searchFilterPage().setNoValueSetting(INCLUDE_FILE_CHILDREN);
    }

    @Given("I deselect Include File Children on the Search Filter page")
    public void iDeselectIncludeFileChildren() {
        Pages.searchFilterPage().clearSetting(INCLUDE_FILE_CHILDREN);
    }

    @Given("I select Only Unassigned Records on the Search Filter page")
    public void iSelectOnlyUnassignedRecords() {
        Pages.searchFilterPage().setNoValueSetting(ONLY_UNASSIGNED_RECORDS);
    }

    @Given("I deselect Only Unassigned Records on the Search Filter page")
    public void iDeselectOnlyUnassignedRecords() {
        Pages.searchFilterPage().clearSetting(ONLY_UNASSIGNED_RECORDS);
    }

    @Given("I set Owner to ($settingsValues) on the Search Filter page")
    public void iSetOwner(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(OWNER, ParametersHelper.processExampleTable(settingsValues));
    }

    @Given("I set Team to ($settingsValues) on the Search Filter page")
    public void iSetTeam(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(TEAM, ParametersHelper.processExampleTable(settingsValues));
    }

    @Given("I set Designation to ($settingsValues) on the Search Filter page")
    public void iSetDesignation(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(DESIGNATION, ParametersHelper.processExampleTable(settingsValues));
    }

    @Given("I set Tags to ($settingsValues) on the Search Filter page")
    public void iSetTags(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(TAGS, ParametersHelper.processExampleTable(settingsValues));
    }
}