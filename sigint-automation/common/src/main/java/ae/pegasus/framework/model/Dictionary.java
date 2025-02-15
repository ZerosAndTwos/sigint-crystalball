package ae.pegasus.framework.model;

import java.util.List;

public class Dictionary {

    private List<Source> sources;
    private List<SourceType> sourceTypes;

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public List<SourceType> getSourceTypes() {
        return sourceTypes;
    }

    public void setSourceTypes(List<SourceType> sourceTypes) {
        this.sourceTypes = sourceTypes;
    }

    public SourceType getBySourceType(String sType) {
        return sourceTypes.stream()
                .filter(sourceType -> sourceType.getDataSource() != null && sourceType.getDataSource().equals(sType))
                .findAny().orElse(null);
    }

    public SourceType getByRecordType(String rType) {
        return sourceTypes.stream()
                .filter(sourceType -> sourceType.getSubSource() != null && sourceType.getSubSource().equals(rType))
                .findAny().orElse(null);
    }

    public SourceType getSourceType(String sType, String rType) {
        return sourceTypes.stream()
                .filter(sourceType -> sourceType.getDataSource() != null && sourceType.getDataSource().equals(sType))
                .filter(sourceType -> sourceType.getSubSource() != null && sourceType.getSubSource().equals(rType))
                .findAny().orElse(null);
    }
}
