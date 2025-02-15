package model;

import java.util.*;

@Deprecated
public enum InformationRequestTaskCategory {


    TARGET_DEVELOPMENT, ANALYSIS_OSINT, TALENT_DEVELOPMENT, OPERATIONAL_REQUIREMENTS;


    public static Object random() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }


    private static final List<InformationRequestTaskCategory> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static Random RANDOM = new Random();


}
