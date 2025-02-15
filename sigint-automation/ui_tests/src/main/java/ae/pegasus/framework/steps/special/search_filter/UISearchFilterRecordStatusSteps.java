package ae.pegasus.framework.steps.special.search_filter;

import ae.pegasus.framework.constants.special.search_filter.FilterRecordStatus;
import org.jbehave.core.annotations.Given;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.special.search_filter.FilterSetting.RECORD_STATUS;

public class UISearchFilterRecordStatusSteps {

    private void setRecordStatus(FilterRecordStatus objectType) {
        Pages.searchFilterPage().setSingleValueSetting(RECORD_STATUS, objectType.getRecordStatus());
    }

    @Given("I set Record Status to All on the Search Filter page")
    public void iSetRecordStatusToAll() {
        setRecordStatus(FilterRecordStatus.ALL);
    }

    @Given("I set Record Status to Unprocessed on the Search Filter page")
    public void iSetRecordStatusToUnprocessed() {
        setRecordStatus(FilterRecordStatus.UNPROCESSED);
    }

    @Given("I set Record Status to Reported on the Search Filter page")
    public void iSetRecordStatusToReported() {
        setRecordStatus(FilterRecordStatus.REPORTED);
    }

    @Given("I set Record Status to Reviewed on the Search Filter page")
    public void iSetRecordStatusToReviewed() {
        setRecordStatus(FilterRecordStatus.REVIEWED);
    }
}
