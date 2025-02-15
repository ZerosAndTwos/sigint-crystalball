package zapi.model;

public class TestResult {

    private String scenario;
    private String result;

    public String getResult() {
        return result;
    }

    public TestResult setResult(String result) {
        this.result = result;
        return this;
    }

    public String getScenario() {
        return scenario;
    }

    public TestResult setScenario(String scenario) {
        this.scenario = scenario;
        return this;
    }
}
