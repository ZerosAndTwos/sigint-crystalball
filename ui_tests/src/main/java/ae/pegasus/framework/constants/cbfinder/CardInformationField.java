package ae.pegasus.framework.constants.cbfinder;

public enum CardInformationField {
    CREATED_ON("Created on:"),
    CREATED_BY("Created by:"),
    CLASSIFICATION("Classification:"),
    REQUEST_NUMBER("Request Number:"),
    FILE_NAME("File Name:"),
    ORGANIZATION_UNITS("Organization Units:"),
    SUBJECT("Subject:"),
    SOURCES("Sources:");

    private final String fieldName;

    CardInformationField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

}
