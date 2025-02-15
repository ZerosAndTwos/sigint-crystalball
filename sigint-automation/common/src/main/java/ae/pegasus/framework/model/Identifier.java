package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import ae.pegasus.framework.data_for_entity.annotations.DataProvider;
import ae.pegasus.framework.data_for_entity.annotations.WithCollectionSize;
import ae.pegasus.framework.data_for_entity.annotations.WithDataDependencies;
import ae.pegasus.framework.data_for_entity.data_providers.profiler.DataSourceTypeProvider;
import ae.pegasus.framework.data_for_entity.data_providers.profiler.IdentifierTypeProvider;
import ae.pegasus.framework.data_for_entity.data_providers.profiler.IdentifierValueProvider;
import ae.pegasus.framework.data_for_entity.data_providers.profiler.ValidationStatusProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Identifier extends AbstractEntity {

    @DataIgnore
    private ProfilerJsonType jsonType = ProfilerJsonType.Identifier;
    private String name;
    @DataIgnore
    private Object properties;
    @WithCollectionSize(1)
    @DataProvider(DataSourceTypeProvider.class)
    private ArrayList<DataSourceType> sources = new ArrayList<>();
    @DataProvider(IdentifierTypeProvider.class)
    private IdentifierType type;
    @DataProvider(ValidationStatusProvider.class)
    private ValidationStatus validationStatus;
    @WithDataDependencies(provider = IdentifierValueProvider.class, fields = {"type"})
    private String value;
    @DataIgnore
    private String valueHighlight;
    @DataIgnore
    private List<String> targets;

    public ProfilerJsonType getJsonType() {
        return jsonType;
    }

    public void setJsonType(ProfilerJsonType jsonType) {
        this.jsonType = jsonType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object properties) {
        this.properties = properties;
    }

    public ArrayList<DataSourceType> getSources() {
        return sources;
    }

    public void setSources(ArrayList<DataSourceType> sources) {
        this.sources = sources;
    }

    public IdentifierType getType() {
        return type;
    }

    public void setType(IdentifierType type) {
        this.type = type;
    }

    public ValidationStatus getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(ValidationStatus validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueHighlight() {
        return valueHighlight;
    }

    public void setValueHighlight(String valueHighlight) {
        this.valueHighlight = valueHighlight;
    }

    public List<String> getTargets() {
        return targets;
    }

    public void setTargets(List<String> targets) {
        this.targets = targets;
    }
}
