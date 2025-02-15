package entity_fabric.data_providers;

import error_reporter.ErrorReporter;
import org.apache.log4j.Logger;

public class UserDefinedDataProvider implements EntityDataProvider {
    
    private Class<?> userClass;
    private Logger logger = Logger.getLogger(UserDefinedDataProvider.class);
    
    public UserDefinedDataProvider(Class<?> userClass) {
        this.userClass = userClass;
    }
    
    @Override
    public Object generate(int length) {
    
        try {
            return userClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            ErrorReporter.reportError(e);
            logger.warn("Cannot create instance of custom class:" + userClass);
            return null;
    
        }
    }
}
