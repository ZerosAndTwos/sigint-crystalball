package ae.pegasus.framework.constants.special.saved_search;

public enum SavedSearchScheduleRepeatInterval {
    HOUR("Hour"),
    DAY("Day"),
    WEEK("Week"),
    MONTH("Month"),
    YEAR("Year");

    private final String repeatIntervalName;

    SavedSearchScheduleRepeatInterval(String repeatIntervalName) {
        this.repeatIntervalName = repeatIntervalName;
    }

    public String getRepeatIntervalName() {
        return repeatIntervalName;
    }

    public static SavedSearchScheduleRepeatInterval getEnumByName(String intervalName) {
        for (SavedSearchScheduleRepeatInterval item : SavedSearchScheduleRepeatInterval.values()) {
            if (item.getRepeatIntervalName().equalsIgnoreCase(intervalName)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Saved Search Schedule repeat interval '" + intervalName + "' was not found");
    }
}
