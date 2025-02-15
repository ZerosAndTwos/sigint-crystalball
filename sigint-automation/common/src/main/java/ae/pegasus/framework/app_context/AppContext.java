package ae.pegasus.framework.app_context;

import ae.pegasus.framework.controllers.AuthService;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.DataSource;
import ae.pegasus.framework.model.Dictionary;
import ae.pegasus.framework.model.LoggedUser;
import ae.pegasus.framework.model.Permission;
import ae.pegasus.framework.services.DictionaryService;
import ae.pegasus.framework.services.PermissionService;

import java.util.List;
import java.util.Map;


public class AppContext {

    private static AppContext instance;
    private Map<String, String> countries;
    private LoggedUser loggedUser;
    private Dictionary dictionary;
    private Map<String, String> languages;
    private List<DataSource> dataSources;
    private List<Permission> permissions;

    private AppContext() {
    }

    public static AppContext get() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public LoggedUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Map<String, String> getLanguages() {
        if (languages == null) {
            languages = JsonConverter.loadJsonToStringMap("languages.json");
        }
        return languages;
    }

    public Map<String, String> getCountries() {
        if (countries == null) {
            countries = JsonConverter.loadJsonToStringMap("countries.json");
        }
        return countries;
    }

    public Dictionary getDictionary() {
        if (dictionary == null) {
            dictionary = DictionaryService.loadDictionary();
        }
        return dictionary;
    }

    public List<DataSource> getDataSources() {
        if (dataSources == null) {
            dataSources = new AuthService().dataSources().getEntity();
        }
        return dataSources;
    }

    public List<Permission> getPermissions() {
        if (permissions == null) {
            permissions = PermissionService.getPermissions();
        }
        return permissions;
    }
}

