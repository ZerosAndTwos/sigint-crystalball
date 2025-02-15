package ae.pegasus.framework.steps.special.search_filter;

import ae.pegasus.framework.utils.TimeUtils;
import ae.pegasus.framework.constants.controls.datetime_selector.DateRangePeriod;
import org.jbehave.core.annotations.Given;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.controls.datetime_selector.DateRangeCalendar.FROM;
import static ae.pegasus.framework.constants.controls.datetime_selector.DateRangeCalendar.TO;
import static ae.pegasus.framework.constants.controls.datetime_selector.DateRangePeriod.*;
import static ae.pegasus.framework.constants.special.search_filter.FilterSetting.EVENT_TIME;

public class UISearchFilterEventTimeSteps {

    @Given("I set date ($dateFrom) as Earliest Event Time on the Search Filter page")
    public void iSetDateFrom(String dateFrom) {
        Pages.searchFilterPage().setDateRange(EVENT_TIME, TimeUtils.convertToLocalDateTime(dateFrom),  TimeUtils.convertToLocalDateTime("10/12/2019"));
    }

    @Given("I set date ($dateFrom) as Earliest")
    public void iSetDateE(String dateFrom) {
        Pages.searchFilterPage().setSingleDateInPeriod(EVENT_TIME, FROM, TimeUtils.convertToLocalDateTime(dateFrom));
    }

    @Given("I set date ($dateTo) as Latest Event Time on the Search Filter page")
    public void iSetDateTo(String dateTo) {
        Pages.searchFilterPage().setSingleDateInPeriod(EVENT_TIME, TO, TimeUtils.convertToLocalDateTime(dateTo));
    }

    @Given("I set date range ($dateFrom) - ($dateTo) as Event Time Period on the Search Filter page")
    public void iSetDateFrom(String dateFrom, String dateTo) {
        Pages.searchFilterPage().setDateRange(EVENT_TIME, TimeUtils.convertToLocalDateTime(dateFrom),  TimeUtils.convertToLocalDateTime(dateTo));
    }

    private void setPeriodRange(DateRangePeriod period) {
        Pages.searchFilterPage().setDateRangePeriod(EVENT_TIME, period);
    }

    @Given("I set period range Last hour as Event Time Period on the Search Filter page")
    public void iSetPeriodLastHour() {
        setPeriodRange(LAST_HOUR);
    }

    @Given("I set period range Last 4 hours as Event Time Period on the Search Filter page")
    public void iSetPeriodLast4Hours() {
        setPeriodRange(LAST_4_HOURS);
    }

    @Given("I set period range Last 8 hours as Event Time Period on the Search Filter page")
    public void iSetPeriodLast8Hours() {
        setPeriodRange(LAST_8_HOURS);
    }

    @Given("I set period range Last 24 hours as Event Time Period on the Search Filter page")
    public void iSetPeriodLast24Hours() {
        setPeriodRange(LAST_24_HOURS);
    }

    @Given("I set period range Last 7 days as Event Time Period on the Search Filter page")
    public void iSetPeriodLast7Days() {
        setPeriodRange(LAST_7_DAYS);
    }

    @Given("I set period range Last 30 days as Event Time Period on the Search Filter page")
    public void iSetPeriodLast30Days() {
        setPeriodRange(LAST_30_DAYS);
    }

    @Given("I set period range Last 90 days as Event Time Period on the Search Filter page")
    public void iSetPeriodLast90Days() {
        setPeriodRange(LAST_90_DAYS);
    }
}
