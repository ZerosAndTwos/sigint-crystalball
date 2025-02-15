package app_context;

import model.Dictionary;

public class ExecuteEnvironment {
    
    private static ExecuteEnvironment instance;
    private Dictionary dictionary;
    
    
    public static  ExecuteEnvironment get() {
        if (instance == null) {
            instance = new ExecuteEnvironment();
        }
        return instance;
    }
    
    private ExecuteEnvironment() {}
    
    
}
