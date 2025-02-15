package ae.pegasus.framework.steps;

import ae.pegasus.framework.app_context.AppContext;
import ae.pegasus.framework.app_context.RunContext;
import ae.pegasus.framework.data_for_entity.RandomEntities;
import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.OperationsResults;
import ae.pegasus.framework.utils.DateHelper;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static ae.pegasus.framework.utils.StringUtils.prettyPrint;
import static ae.pegasus.framework.utils.StringUtils.stripQuotes;
import static org.junit.Assert.assertTrue;

public abstract class APISteps {

    static RunContext context = RunContext.get();
    static AppContext appContext = AppContext.get();
    static RandomEntities objectInitializer = new RandomEntities();
    static Logger log = Logger.getLogger(APISteps.class);

    @Then("I got response code $expected")
    public void checkResponseCode(String expected) {
        OperationResult result = OperationsResults.getResult();
        log.info("Response code: " + result.getCode());
        assertTrue(
            "Incorrect return code!\n" + prettyPrint(result.getMessage()),
            Integer.valueOf(expected) == result.getCode()
        );
    }

    @Then("Message contains $text")
    public void checkMessageContainsText(String text) {
        OperationResult result = OperationsResults.getResult();
        log.info("Response message: " + prettyPrint(result.getMessage()));
        assertTrue(
            text + " not found in response:\n" + prettyPrint(result.getMessage()),
            result.getMessage().contains(stripQuotes(text))
        );
    }
    
    @Then("Request is successful")
    public void checkResultSuccess() {
        OperationResult result = OperationsResults.getResult();
        if (!result.isSuccess()) {
            throw new OperationResultError(result);
        }
    }
    
    @Then("Request is unsuccessful")
    public void checkResultFail() {
        OperationResult result = OperationsResults.getResult();
        if (result.isSuccess()) {
            throw new OperationResultError(result);
        }
    }

    @When("I wait for $count seconds")
    public void waitSeveralseconds(String count) {
        int delay = Integer.valueOf(count);

        log.info("Waiting for " + delay + " seconds...");
        DateHelper.waitTime(delay);
    }

    public void sleep(int i) {
        //FIXME
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Given("Clear context")
    public void cleanUpContext() {
        context.clear();
    }
}
