package entity_fabric;

import error_reporter.ErrorReporter;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.List;

public class TeelaInstanceManager<T> {
    
    private T teelaInstance;
    private Logger logger = Logger.getLogger(TeelaInstanceManager.class);
    
    private void createInstance(Class<T> teelaClass) {
        logger.debug("Creating instance with type:"+teelaClass);
        try {
            T instance = teelaClass.newInstance();
            logger.debug("Instance created successfully");
            this.teelaInstance = instance;
        } catch (InstantiationException | IllegalAccessException e) {
            ErrorReporter.reportError(e);
            this.teelaInstance = null;
            
        }
    
    }
    
    public T fillOnlyRequiredFields() {
        logger.debug("Filling in required fields for teela instance:"+teelaInstance);
        if (teelaInstance == null) {
            logger.warn("Cannot fill mandatory fields for teela instance as it " +
                    "wasn't created");
            return teelaInstance;
        }
        FieldsManager fieldsManager = new FieldsManager(teelaInstance);
        List<Field> fields = fieldsManager.getInstanceRequiredFields();
        for (Field field:fields) {
            fieldsManager.fillField(field);
        }
        return teelaInstance;
    }
}

