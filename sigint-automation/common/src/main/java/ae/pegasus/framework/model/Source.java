package ae.pegasus.framework.model;


import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import ae.pegasus.framework.data_for_entity.annotations.DataProvider;
import ae.pegasus.framework.data_for_entity.annotations.WithDataDependencies;
import ae.pegasus.framework.data_for_entity.data_providers.coordinates.CoordinateProvider;
import ae.pegasus.framework.data_for_entity.data_providers.country_info.CountryProvider;
import ae.pegasus.framework.data_for_entity.data_providers.record.RecordTypeProvider;
import ae.pegasus.framework.data_for_entity.data_providers.source.SourceNameProvider;
import ae.pegasus.framework.data_for_entity.data_providers.source.SourceTypeProvider;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class Source extends BaseEntity {

    @DataProvider(SourceTypeProvider.class)
    private String type;
    @WithDataDependencies(provider = RecordTypeProvider.class, fields = {"type"})
    private String recordType;
    @WithDataDependencies(provider = SourceNameProvider.class, fields = {"type", "recordType"})
    private String name;
    @DataProvider(CountryProvider.class)
    private String location;
    @DataIgnore
    private boolean deleted = false;
    @DataProvider(CoordinateProvider.class)
    private double latitude;
    @DataProvider(CoordinateProvider.class)
    private double longitude;
    @DataIgnore
    private Long version;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
