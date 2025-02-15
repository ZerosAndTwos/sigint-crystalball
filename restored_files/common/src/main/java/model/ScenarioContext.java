package model;

public class ScenarioContext {

    private String ScenarioTitle;
    private static ScenarioContext instance;

    public static ScenarioContext get() {
        if (instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }

    public String getScenarioTitle() {
        return ScenarioTitle;
    }

    public void setScenarioTitle(String scenarioTitle) {
        ScenarioTitle = scenarioTitle;
    }
}
