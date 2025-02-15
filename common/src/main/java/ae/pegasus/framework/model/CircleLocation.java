package ae.pegasus.framework.model;

import java.util.List;

public class CircleLocation extends Location {

    private List<Double> coordinates;
    private Double radius;
    private LocationProperties properties;

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public LocationProperties getProperties() {
        return properties;
    }

    public void setProperties(LocationProperties properties) {
        this.properties = properties;
    }
}
