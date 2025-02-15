package ae.pegasus.framework.constants.controls.datetime_selector;

public enum DateRangePeriod {
    LAST_HOUR("Last hour"),
    LAST_4_HOURS("Last 4 hours"),
    LAST_8_HOURS("Last 8 hours"),
    LAST_24_HOURS("Last 24 hours"),
    LAST_7_DAYS("Last 7 days"),
    LAST_30_DAYS("Last 30 days"),
    LAST_90_DAYS("Last 90 days");


    private final String periodName;

    DateRangePeriod(String periodName) {
        this.periodName = periodName;
    }

    public String getPeriodName() {
        return periodName;
    }
}
