package model;

import json.JsonCoverter;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.type.MapType;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class AppContext {

   
    private Map<String, Object> runContext = new HashMap<>();
    private Map<String, String> countries;
    private User loggedUser;
    private static Logger log = Logger.getLogger(AppContext.class);
    

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    private static Map<String,String > loadJsonToMap(String filename) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream entityList = classloader.getResourceAsStream(filename);
        MapType mapType = JsonCoverter.constructMapTypeToValue(String.class);
        try {
            Map<String, String> entities = JsonCoverter.mapper.readValue(entityList, mapType);
            return entities;
        } catch (IOException e) {
            log.error("Cannot load list of entities");
            throw new AssertionError();
        }
    }

    public Map<String, String> getLanguages() {
        if (languages == null) {
            setLanguages(loadJsonToMap("languages.json"));
        }
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

    public Map<String, String> getCountries() {
        if (countries == null) {
            setCountries(loadJsonToMap("countries.json"));
        }
        return countries;
    }

    public void setCountries(Map<String, String> countries) {
        this.countries = countries;
    }

    private Map<String, String> languages;

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    private Dictionary dictionary;
    
    }


    public <T>void put(String key, T object) {
        this.runContext.put(key, object);
    }

    public <T>T get(String key, Class<T> userClass) {
        Object object;
        try {
            object = runContext.get(key);
        }catch (NullPointerException e) {
            throw new AssertionError(String.format("Object with key %s doesn't exist!", key));
        }
        return userClass.cast(object);
    }
}

