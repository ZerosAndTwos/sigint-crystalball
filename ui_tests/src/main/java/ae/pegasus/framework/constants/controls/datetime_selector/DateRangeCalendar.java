package ae.pegasus.framework.constants.controls.datetime_selector;

public enum DateRangeCalendar {
    FROM("left"),
    TO("right");

    private final String position;

    DateRangeCalendar(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
