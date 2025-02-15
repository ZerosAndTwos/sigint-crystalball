package ae.pegasus.framework.constants.special.search_filter;

public enum FilterObjectType {

    ALL("All"),
    ENTITY("Entity"),
    EVENT("Event");

    private final String objectName;

    FilterObjectType(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectName() {
        return objectName;
    }
}
