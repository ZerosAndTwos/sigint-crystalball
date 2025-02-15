package ae.pegasus.framework.failure_strategy;

import ae.pegasus.framework.zapi.ZAPIService;
import ae.pegasus.framework.jira.JiraService;
import org.apache.log4j.Logger;
import ae.pegasus.framework.reporter.ReportResults;
import ae.pegasus.framework.reporter.TestCase;

import java.util.List;

public class Statistic {
    
    private ZAPIService service = new ZAPIService();
    private JiraService jiraService = new JiraService();
    private Logger log = Logger.getLogger(Statistic.class);
    
    
    public ReportResults getResults() {
        return service.getReportResults();
    }
    
    public Boolean hasFailuresWithoutBugs() {
        log.debug("Checking if there are failures without bugs during the run");
        List<TestCase> testCaseList = service.getReportResults().getFailed();
            for (TestCase testCase:testCaseList) {
                if (!jiraService.hasOpenedBugs(testCase.getTitle())) {
                    log.debug("Found failure without opened bug for test case:/n" +
                            " "+testCase.getTitle());
                    return true;
                }
            }
            log.debug("No failures without opened bugs");
            return false;
        }
    
    public Boolean hasOpenedBug(String testCaseTitle) {
        try {
            return jiraService.hasOpenedBugs(testCaseTitle);
        } catch (Exception e) {
            log.warn("Error during jira connection. Unable to check opened bugs on the issue: " + testCaseTitle);
            log.error(e.getMessage(), e);
            return false;
        }
    }
    
}
