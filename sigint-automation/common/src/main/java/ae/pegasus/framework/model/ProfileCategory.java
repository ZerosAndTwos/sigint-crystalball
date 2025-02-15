package ae.pegasus.framework.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ProfileCategory {

    Dangerous("Dangerous"),
    POI("Person of Interest");

    private final String displayName;

    ProfileCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    private static final List<ProfileCategory> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static Random RANDOM = new Random();

    public static ProfileCategory random() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
