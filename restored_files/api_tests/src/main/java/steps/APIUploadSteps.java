package steps;

import model.Target;
import model.UploadResult;
import org.jbehave.core.annotations.Then;

import java.util.ArrayList;
import java.util.List;

public class APIUploadSteps extends APISteps {

    @Then("Upload result of $count entries is successful")
    public void checkUploadResults(String count) {
        log.info("Checking DuSubscriberEntry upload result");
        UploadResult uploadResult = context.get("uploadResult", UploadResult.class);

        int numEntries = Integer.valueOf(count);
        if (uploadResult.getNumEntries() != numEntries && uploadResult.getFailedEntries() > 0) {
            log.error("Entry upload result is not correct!");
            throw new AssertionError("Entry upload result is not correct!");
        }
    }

    @Then("Upload result of $count targets is successful")
    public void checkUploadTargetsResults(String count) {
        log.info("Checking DuSubscriberEntry upload result");
        UploadResult result = context.get("uploadResult", UploadResult.class);

        int numEntries = Integer.valueOf(count);
        if ((result.getRowsAdded() + result.getRowsUpdated()) == numEntries && result.getRowsFailed() == 0) {
            List<Target> targets = context.get("uploadedTargets", ArrayList.class);
            for (Target target : targets) {
                context.entities().getTargets().addOrUpdateEntity(target);
            }
        } else {
            log.error("Entry upload result is not correct!");
            log.error("Errors:" + result.getErrors());
            throw new AssertionError("Entry upload result is not correct! Errors: " + result.getErrors());
        }
    }
}
