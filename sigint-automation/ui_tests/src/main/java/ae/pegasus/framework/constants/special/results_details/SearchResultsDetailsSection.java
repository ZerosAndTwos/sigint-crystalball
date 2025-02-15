package ae.pegasus.framework.constants.special.results_details;

public enum SearchResultsDetailsSection {
    RECORD_DETAILS("Record Details", ""),
    RECORD_METADATA("Record Metadata", "metadataSectionElement"),
    RECORD_ASSESSMENT("Record Assessment", "assessmentSectionElement"),
    ATTRIBUTES("Attributes", "attributesSectionElement"),
    FROM("Participants", "participantsSectionElement"),
    TO("Participants", "participantsSectionElement");

    private final String sectionName;
    private final String ref;

    SearchResultsDetailsSection(String sectionName, String ref) {
        this.sectionName = sectionName;
        this.ref = ref;
    }

    public String getSectionName() {
        return sectionName;
    }
    public String getref() {
        return ref;
    }
}
