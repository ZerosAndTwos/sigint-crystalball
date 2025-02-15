package services;

import app_context.AppContext;
import org.apache.log4j.Logger;

public class ContextService {
    
    private static Logger logger = Logger.getLogger(ContextService.class);
    
    
    public static String getToken() {
        String token=null;
    
        try {
            token = AppContext.get().getLoggedUser().getToken().getValue();
        } catch (NullPointerException e) {
            logger.error("Error occurred trying to get token. User is not logged in");
        }
        return token;
    }
}
