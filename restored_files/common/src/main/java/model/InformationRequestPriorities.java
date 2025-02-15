package model;

import data_for_entity.data_providers.EntityDataProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by dm on 4/15/16.
 */
public enum InformationRequestPriorities implements EntityDataProvider {

    CRITICAL(2, 1), NORMAL(0, 5), HIGH(1,3);


    private static final List<InformationRequestPriorities> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private Priority priority;

    InformationRequestPriorities(int priority, int daysSwitch) {
        this.priority = new Priority(priority, daysSwitch);
    }
    
    
    @Override
    public Object generate(int length) {
        return VALUES.get(RANDOM.nextInt(SIZE)).getPrioritiesMap();
    }
    
    

    private Priority getPrioritiesMap() {
        return this.priority;
    }

}


class Priority {

    private int priority;
    private int daysSwitch;


    Priority(int priority, int daysSwitch){
        this.priority = priority;
        this.daysSwitch = daysSwitch;
    }

    int getDaysSwitch() {
        return daysSwitch;
    }

    void setDaysSwitch(int daysSwitch) {
        this.daysSwitch = daysSwitch;
    }

    int getPriority() {
        return priority;
    }

    void setPriority(int priority) {
        this.priority = priority;
    }

}
