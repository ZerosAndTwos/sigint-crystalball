package ae.pegasus.framework.zapi;

import ae.pegasus.framework.app_context.properties.G4Properties;
import ae.pegasus.framework.app_context.properties.JiraProperties;
import ae.pegasus.framework.errors.NullReturnException;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.OperationsResults;
import ae.pegasus.framework.jira.JiraConnector;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.reporter.ReportParser;
import ae.pegasus.framework.reporter.ReportResults;
import ae.pegasus.framework.reporter.Step;
import ae.pegasus.framework.reporter.TestCase;
import ae.pegasus.framework.zapi.model.*;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ZAPIService {

    private ZAPI zapi = new ZAPI();
    private HashMap<String, Integer> issueIdMap = new HashMap<>();
    private JiraProperties connectionProperties = G4Properties.getJiraProperties();
    private Logger log = Logger.getLogger(ReportParser.class);
    private ReportResults reportResults;

    /**
     * Return report results
     *
     * @return ReportResults
     */
    public ReportResults getReportResults() {
        if (reportResults == null) {
            reportResults = new ReportParser().parseXmlReportFiles("target/allure-results");
        }
        for (TestCase testCase : reportResults.getFailed()) {
            testCase.setUrl(connectionProperties.getJiraServer() + "/browse/" +
                    getTestCaseKeyByTitle(testCase.getTitle()));
        }
        return reportResults;
    }

    /**
     * Create test cycle
     */
    private Cycle createCycle(String projectId, String versionId) {
        log.info("Creating test cycle in Zephyr");
        Cycle cycle = new Cycle()
                .setClonedCycleId("")
                .setName(connectionProperties.getCycleName())
                .setBuild("")
                .setEnvironment("")
                .setDescription("")
                .setStartDate(new Date())
                .setEndDate(new Date())
                .setProjectId(projectId)
                .setVersionId(versionId);

        OperationResult<Cycle> cycleOperationResult= zapi.postCycle(cycle);

        if (cycleOperationResult.isSuccess()) {
            cycle = cycleOperationResult.getEntity();
            log.info(cycle);
        } else {
            OperationsResults.logError(cycleOperationResult);
        }
        return cycle;
    }

    /**
     * Get current test cycle or create new one if cycle missing in Jira.
     *
     * @return test cycle
     */
    private Cycle getCycle() {
        log.info("Getting execution cycle...");
        Cycle cycle;
        JiraConnector connector = new JiraConnector();
        try {
            String projectId = connector.getProjectId(connectionProperties.getProject());
            String versionId = connector.getVersionId(projectId, connectionProperties.getVersion());

            G4Response response = zapi.getCycles(projectId, versionId);

            CyclesList cycles = JsonConverter.jsonToObject(response.getMessage(), CyclesList.class);
            Cycle foundcycle = cycles.getCycle(connectionProperties.getCycleName());
            if (foundcycle == null) {
                log.debug("Cycle is not found. Creating new one");
                cycle = createCycle(projectId, versionId)
                        .setProjectId(projectId)
                        .setVersionId(versionId);
            } else {
                cycle = foundcycle;
                log.debug("Cycle is found. ID=" + cycle.getId() + " Name=" + cycle.getName());
            }
        } catch (NullReturnException e) {
            log.warn(e.getMessage());
            log.info("Cannot get existing cycles as project and/or version id wasn't received." +
                    "Creating new cycle...");
            throw new AssertionError("Cycle cannot be created!");
        }
        return cycle;
    }

    /**
     * Create new Execution for Jira issue.
     *
     * @param cycle   cycle
     * @param issueId issueId
     * @return Execution
     */
    private Execution addTestToCycle(Cycle cycle, int issueId) {
        // Arrange
        Execution execution = new Execution()
                .setCycleId(cycle.getId())
                .setIssueId(String.valueOf(issueId))
                .setProjectId(cycle.getProjectId())
                .setVersionId(cycle.getVersionId());

        // Act
        log.debug("Add new execution of test with issueId: " + execution.getIssueId() + " to cycle");
        OperationResult<String> executionResult = zapi.postExecution(execution);

        execution = null;
        if (executionResult.isSuccess()) {
            String message = executionResult.getEntity();
            String json = message.substring(message.lastIndexOf("{"), message.indexOf("}") + 1);
            execution = JsonConverter.jsonToObject(json, Execution.class);
            log.debug(execution + " executionId = " + execution.getId());
        } else {
            OperationsResults.logError(executionResult);
        }
        return execution;
    }

    /**
     * Set execution result.
     *
     * @param execution Execution
     * @param result    result status of execution:
     *                  ZAPI.UNEXECUTED = "-1"
     *                  ZAPI.PASS = "1"
     *                  ZAPI.FAIL = "2"
     *                  ZAPI.WIP = "3"
     *                  ZAPI.BLOCKED = "4"
     */
    private void setExecutionResult(Execution execution, String result) {
        log.debug("Set execution result for: " + execution.getIssueKey() + ", result: " + result);

        OperationResult operationResult = zapi.putExecution(execution.getId(), result);
        if (!operationResult.isSuccess()) {
            OperationsResults.logError(operationResult);
        }
    }

    /**
     * Return test key.
     *
     * @param title Test key title (summary)
     * @return test key, ex: GQ-123, TEEL-1234
     */
    public String getTestCaseKeyByTitle(String title) {
        log.debug("Finding test case by it's title " + title);

        OperationResult<IssueList> operationResult = zapi.JQL(0, 1000, "key", String.format("summary~\"%s\"", title), IssueList.class);

        if (!operationResult.isSuccess()) {
            log.error("Was unable to complete request to JIRA");
            return null;
        }
        try {
            IssueList issueList = operationResult.getEntity();
            log.debug("Found issues: " + issueList.getIssues());
            return issueList.getIssues().get(0).getKey();
        } catch (IndexOutOfBoundsException e) {
            log.error("Cannot find test case by it's name " + title);
            return "";
        }
    }

    /**
     * Mapping test issue from Jira via API
     */
    private void getTestCasesFromProject(String projectKey) {
        // TODO batch load data
        log.info("Download tests from Zephyr...");
        OperationResult<IssueList> operationResult = zapi.JQL(0, 1000, "id,key,summary", "project=" + projectKey + " and issuetype = Test", IssueList.class);

        if (operationResult.isSuccess()) {
            IssueList issueList = operationResult.getEntity();
            log.info("Tests downloaded: " + issueList.getIssues().size());
            for (Issue issue : issueList.getIssues()) {
                issueIdMap.put(issue.getFields().getSummary(), issue.getId());
            }
        } else {
            OperationsResults.logError(operationResult);
        }
    }

    /**
     * Export auto-tests results run report to Zephyr (Jira).
     */
    public void reportToZephyr() {
        log.info("Reporting results to Zephyr...");
        getTestCasesFromProject("GQ");

        Cycle cycle = getCycle();
        Assert.assertTrue(cycle != null);
        getReportResults();
        List<TestCase> testCases = reportResults.getTestCases();
        log.debug("Add test results to cycle...");
        log.debug("Test result size: " + testCases.size());
        for (TestCase testCase : testCases) {
            log.debug("Scenario: " + testCase.getTitle() + ", status: " + testCase.getStatus());
            if (issueIdMap.containsKey(testCase.getTitle())) {
                // create test
                Execution execution = addTestToCycle(cycle, issueIdMap.get(testCase.getTitle()));
                for (Step step : testCase.getSteps()) {
                    log.debug("Step: " + step.getName() + ", status: " + step.getStatus());
                }

                // set test result
                switch (testCase.getStatus()) {
                    case "passed":
                        setExecutionResult(execution, ZAPI.PASS);
                        break;
                    case "failed":
                        setExecutionResult(execution, ZAPI.FAIL);
                        break;
                    case "broken":
                        setExecutionResult(execution, ZAPI.FAIL);
                    default:
                        setExecutionResult(execution, ZAPI.WIP);
                        break;
                }
            } else {
                log.warn("Test: '" + testCase.getTitle() + "', does not found on Zephyr");
            }
        }
    }
}
