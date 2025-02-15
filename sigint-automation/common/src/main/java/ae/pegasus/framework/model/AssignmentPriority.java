package ae.pegasus.framework.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum AssignmentPriority {

    REGULAR, HIGH, URGENT;

    private static final List<AssignmentPriority> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static Random RANDOM = new Random();

    public static AssignmentPriority random() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
