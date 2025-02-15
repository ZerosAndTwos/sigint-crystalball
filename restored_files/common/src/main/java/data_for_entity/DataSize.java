package data_for_entity;

import data_for_entity.annotations.WithDataSize;
import data_for_entity.data_providers.EntityDataProvider;
import org.apache.log4j.Logger;

/**
 * Class represents options captured from {@link WithDataSize}
 */
public class DataSize {
        
    private Logger logger = Logger.getLogger(DataSize.class);
    private WithDataSize dataSize;
    
    
    DataSize(WithDataSize dataSize) {
        this.dataSize = dataSize;
    }
    
    
    
    /**
     * Gets option length from {@link WithDataSize}
     * @return set value for option length or default value.
     */
    int getDataLength() {
        int dataLength;
        if (dataSize!=null) {
            dataLength = dataSize.length();
            } else {
                dataLength = defaultLength("length");
        }
        if (dataLength == 0) {
            logger.debug("using default data length from Data Provider interface");
            dataLength = EntityDataProvider.dataLength;
        }
        return dataLength;
    }
    
    /**
     * Gets option collectionSize from {@link WithDataSize}
     * @return set value for collection size option or default value.
     */
    int getCollectionSize() {
        int collectionSize;
        if (dataSize!=null) {
            collectionSize = dataSize.collectionSize();
        } else {
            collectionSize = defaultLength("collectionSize");
        }
        if (collectionSize == 0) {
            logger.debug("using default collection size from Data Provider interface");
            collectionSize = EntityDataProvider.collectionLength;
        }
        return collectionSize;
        
    }
    
    
    /**
     * internal method to get length attribute from {@link WithDataSize}
     * @param field name of field to get length value
     * @return value of default length.
     */
    private int defaultLength(String field) {
        int defaultLength =0;
        try {
            defaultLength = Helpers.getAnnotationDefault(WithDataSize.class, field);
        } catch (Exception e) {
            logger.debug("Error occurred getting default value from WithDataOptions annotation");
        }
        return defaultLength;
    }
    
    
}
