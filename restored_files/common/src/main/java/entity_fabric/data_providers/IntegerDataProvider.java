package entity_fabric.data_providers;

import org.apache.commons.lang.RandomStringUtils;

public class IntegerDataProvider implements EntityDataProvider{
    
    @Override
    public Object generate(int length) {
        return RandomStringUtils.randomNumeric(length);
    }
}
