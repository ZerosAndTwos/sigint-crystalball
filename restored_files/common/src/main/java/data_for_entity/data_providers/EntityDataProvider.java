package data_for_entity.data_providers;

import org.apache.log4j.Logger;

/**
 * Basic interface that is used to generate random data.
 */
public interface EntityDataProvider {

    Logger log = Logger.getLogger(EntityDataProvider.class);
    
    Object generate(int length);
}
