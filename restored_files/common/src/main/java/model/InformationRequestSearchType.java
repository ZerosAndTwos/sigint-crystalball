package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Deprecated
public enum InformationRequestSearchType{

    ST, S;

    private static final List<InformationRequestSearchType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static Random RANDOM = new Random();
    
    
    public static Object random() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
    
}
