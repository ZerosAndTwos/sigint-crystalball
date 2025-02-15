package app_context;

import java.util.HashMap;
import java.util.Map;

public class RunContext {
    
    private static RunContext instance;
    private Map<String, Object> context = new HashMap<>();
    
    
    private RunContext(){}
    
    public static RunContext get() {
        if (instance == null) {
            instance = new RunContext();
        }
        return  instance;
    }
    
    
    public <T>void put(String key, T object) {
        this.context.put(key, object);
    }
    
    public <T>T get(String key, Class<T> userClass) {
        Object object;
        try {
            object = context.get(key);
        }catch (NullPointerException e) {
            throw new AssertionError(String.format("Object with key %s doesn't exist!", key));
        }
        return userClass.cast(object);
    }
}
