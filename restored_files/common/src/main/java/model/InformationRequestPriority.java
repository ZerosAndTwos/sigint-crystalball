package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Deprecated
public enum InformationRequestPriority {

    CRITICAL(2, 1), NORMAL(0, 5), HIGH(1,3);


    private static final List<InformationRequestPriority> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private Integer numberPriority;
    private Integer daysSwitch;

    InformationRequestPriority(int priority, int daysSwitch) {
        this.numberPriority = priority;
        this.daysSwitch = daysSwitch;
    }
    
    
    public Integer getNumber() {
        return numberPriority;
    }
    
    public Integer getDaysSwitch() {
        return daysSwitch;
    }

    public static InformationRequestPriority random() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
    
    public static InformationRequestPriority getByNumber(Integer numberPriority) {
        for (InformationRequestPriority priority: VALUES) {
            if (priority.getNumber().equals(numberPriority)) {
                return priority;
            }
        }
        return null;
    }
    
    

}


