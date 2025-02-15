package reporter;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportResults {

    private Logger log = Logger.getLogger(ReportResults.class);
    private HashMap<String, TestCase> testCases = new HashMap<>();

    public int getTotalRun(){
        return testCases.size();
    }

    public TestCase get(String testTitle) {
        return testCases.get(testTitle);
    }

    public TestCase add(TestCase testCase) {
        return testCases.put(testCase.getTitle(), testCase);
    }

    public TestCase update(TestCase testCase) {
        return testCases.put(testCase.getTitle(), testCase);
    }

    public TestCase remove(TestCase testCase) {
        return testCases.remove(testCase.getTitle());
    }

    public List<TestCase> getTestCases() {
        return new ArrayList<>(testCases.values());
    }

    private List<TestCase> getTestCasesByStatus(String status) {
        log.debug("Getting '" + status + "' tests list");
        List<TestCase> testList = new ArrayList<>();
        for (TestCase test : getTestCases()) {
            if (test.getStatus().equalsIgnoreCase(status)) {
                if (!testList.add(test)) {
                    log.warn("Test: " + test.getTitle() + ", does not added into '" + status + "' list");
                }
            }
        }
        return testList;
    }

    public List<TestCase> getPassed() {
        return getTestCasesByStatus("passed");
    }

    public List<TestCase> getFailed() {
        List<TestCase> failedTestCases = getTestCasesByStatus("failed");
        failedTestCases.addAll(getTestCasesByStatus("broken"));
        return failedTestCases;
    }

    public List<TestCase> getBrokened() {
        return getTestCasesByStatus("broken");
    }

    public List<TestCase> getSkipped() {
        return getTestCasesByStatus("pending");
    }

}
