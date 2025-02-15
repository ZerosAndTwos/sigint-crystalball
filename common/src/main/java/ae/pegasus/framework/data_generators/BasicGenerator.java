package ae.pegasus.framework.data_generators;


import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.List;

public class BasicGenerator {
    private static RandomDataGenerator rand;

    public static RandomDataGenerator getRand() {
        if (rand == null) {
            rand = new RandomDataGenerator();
        }
        return rand;
    }

    protected static int getRandomIndexFor(Object[] objectsArray) {
        return getRand().nextSecureInt(0, objectsArray.length - 1);
    }

    protected static int getRandomIndexFor(List<?> objectList) {
        return getRand().nextSecureInt(0, objectList.size() - 1);
    }
}
