package ae.pegasus.framework.app_context.properties;

import ae.pegasus.framework.error_reporter.ErrorReporter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Any class that encapsulate Properties of application
 * should be extended from this abstract base class.
 * Holds abstract method loadPropertyFile. Implementation
 * should define java.io.InputStream
 * @version 1.0
 */
abstract class ApplicationProperty {
    
    private Properties prop = new Properties();
    
    ApplicationProperty(){
        try {
            prop.load(loadPropertyFile());
        } catch (IOException ex) {
            ErrorReporter.reportError(ex);
        }
    
    }
    
    Properties getProperty() {
        return prop;
    }
    
    /**
     * This method should be implemented by every sub-class.
     * InputStream loaded from property file is expected.
     * @return java.io.InputStream.
     */
    abstract InputStream loadPropertyFile();
}
