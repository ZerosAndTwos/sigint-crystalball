package ae.pegasus.framework.model;

import java.util.*;

public enum Classification {

    OUO("OUO", null, "OFFICIAL USE ONLY"),
    C("C", "OUO", "CONFIDENTIAL"),
    S("S", "C", "SECRET"),
    TS("TS", "S", "TOP SECRET"),
    TS_CIO("TS-CIO", "TS", "TOP SECRET-CIO"),
    TS_OS("TS-OS", "TS", "TOP SECRET-OS"),
    TS_SCI("TS-SCI", "TS", "TOP SECRET-SCI");

    private final String name;
    private final String parentName;
    private final String displayName;

    Classification(String name, String parentName, String displayName) {
        this.name = name;
        this.parentName = parentName;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public String getParentName() {
        return parentName;
    }

    public String getDisplayName() {
        return displayName;
    }

    private static final List<Classification> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static Random RANDOM = new Random();

    public static Classification random() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public boolean hasAccess(Classification classification) {
        Set<String> allowed = new HashSet<>();
        Classification c = Classification.this;
        do {
            allowed.add(c.name);
            c = (c.getParentName() == null) ? null : Classification.valueOf(c.parentName);
        } while (c != null);

        return allowed.contains(classification.name);
    }
}
