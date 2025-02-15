package entity_fabric.data_providers;

import org.apache.commons.lang.RandomStringUtils;

public class StringDataProvider implements EntityDataProvider {
    
    
    @Override
    public Object generate(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
}
