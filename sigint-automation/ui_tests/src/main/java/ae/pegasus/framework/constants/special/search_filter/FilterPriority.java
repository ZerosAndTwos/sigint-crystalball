package ae.pegasus.framework.constants.special.search_filter;

public enum FilterPriority {
    ALL("All"),
    REGULAR("Regular"),
    HIGH("High"),
    URGENT("Urgent");

    private final String priorityName;

    FilterPriority(String priorityName) {
        this.priorityName = priorityName;
    }

    public String getPriorityName() {
        return priorityName;
    }
}
