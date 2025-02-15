package ae.pegasus.framework.model;

import java.util.List;

public class PolygonLocation extends Location {

    private List<List<String>> coordinates;

    public List<List<String>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<String>> coordinates) {
        this.coordinates = coordinates;
    }

}
