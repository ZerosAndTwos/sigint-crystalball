package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    private LocationType type;

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public class LocationProperties {
        private String radius_units;

        public String getRadius_units() {
            return radius_units;
        }

        public void setRadius_units(String radius_units) {
            this.radius_units = radius_units;
        }
    }

    public enum LocationType {
        Circle, Polygon
    }
}
