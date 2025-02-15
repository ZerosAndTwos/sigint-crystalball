package data_for_entity.data_providers;

import org.apache.commons.lang.math.RandomUtils;

public class LongData implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return RandomUtils.nextLong();
    }
}
