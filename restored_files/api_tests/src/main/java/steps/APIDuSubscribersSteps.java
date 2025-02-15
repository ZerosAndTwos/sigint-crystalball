package steps;

import model.DuSubscriberEntry;
import model.phonebook.DuSubscribersUploadResult;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import services.DuSubscribersService;

public class APIDuSubscribersSteps extends APISteps {

    private Logger log = Logger.getLogger(APIPhonebookSteps.class);
    private DuSubscribersService service = new DuSubscribersService();


    @When("I send upload DuSubscribersEntry request with all fields")
    public void sendUploadDuSubscribersEntryRequest() {
        DuSubscriberEntry duSubscriberEntry = new DuSubscriberEntry().generate();

        int responseCode = service.add(duSubscriberEntry);

        context.put("code", responseCode);
        context.put("duSubscriberEntry", duSubscriberEntry);
    }

    @Then("DuSubscribersEntry upload result is successful")
    public void checkDuSubscribersUploadResults() {
        log.info("check DuSubscribersEntry upload result");
        DuSubscribersUploadResult uploadResult = context.get("uploadResult", DuSubscribersUploadResult.class);

        if (uploadResult.getNumEntries() != 1 && uploadResult.getFailedEntries() > 0) {
            log.error("DuSubscribersEntry upload result is not correct!");
            throw new AssertionError("DuSubscribersEntry upload result is not correct!");
        }
    }


}
