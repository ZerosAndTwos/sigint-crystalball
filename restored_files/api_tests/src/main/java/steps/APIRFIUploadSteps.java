package steps;
/**
import app_context.entities.Entities;
import conditions.Conditions;
import conditions.Verify;
import model.FileAttachment;
import model.InformationRequest;
import model.LoggedUser;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import services.RFIService;

public class APIRFIUploadSteps extends APISteps {

    private static Logger log = Logger.getLogger(APIRFIUploadSteps.class);
    private static RFIService service = new RFIService();
    
    @When("I send create RFI request $withApproved approved copy and $withCopy original document")
    public void createRFI(String withApproved, String withCopy) {
        log.info("Starting step of creating new RFI");
        InformationRequest RFI = getRandomRFI();
        if (withApproved.toLowerCase().equals("with")){
            RFI.setApprovedCopy(new FileAttachment(("approved")));
        }
        if (withCopy.toLowerCase().equals("with")) {
            RFI.setOriginalDocument(new FileAttachment("original"));
        }
        sendRFI(RFI);
    }

    private void sendRFI(InformationRequest RFI) {
        int response = service.add(RFI);
        context.put("code", response);
        context.put("requestRFI", RFI);
        if (response == 200) {
            rfiCorrect();
        }
    }

    private void rfiCorrect() {
        log.info("Verifying if RFI is correct");
        InformationRequest etalonRFI;
        InformationRequest createdRFI;
        etalonRFI = context.get("requestRFI", InformationRequest.class);;
        createdRFI = Entities.getRFIs().getLatest();
        checkRFIs(etalonRFI, createdRFI);
    }

    private void checkRFIs(InformationRequest etalon, InformationRequest created) {
        Assert.assertEquals(etalon.getSubject(), created.getSubject());
        Assert.assertEquals(etalon.getDescription(), created.getDescription());
        Assert.assertEquals(etalon.getDistributionList(), created.getDistributionList());
        Assert.assertEquals(etalon.getCreatedDate(), created.getCreatedDate());
        Assert.assertEquals(etalon.getPriority(), created.getPriority());
        Assert.assertEquals(etalon.getDueDate(), created.getDueDate());
        Assert.assertEquals(etalon.getExternalRequestNumber(), created.getExternalRequestNumber());
        Assert.assertEquals(etalon.getGoals(), created.getGoals());
        Assert.assertEquals(etalon.getRequestSource(), created.getRequestSource());
        Assert.assertEquals(etalon.getTaskCategories(), created.getTaskCategories());
        Assert.assertEquals(etalon.getState(), created.getState());
        Assert.assertTrue(created.getInternalRequestNumber() != null);
        if (etalon.getApprovedCopy() != null) {
            Assert.assertEquals(etalon.getApprovedCopy().getFilename(),
                    created.getApprovedCopy().getFilename());
        }
        if (etalon.getOriginalDocument() != null) {
            Assert.assertEquals(etalon.getOriginalDocument().getFilename(),
                    created.getOriginalDocument().getFilename());
        }
    }

    @When("I update created RFI")
    public void updateCreatedRFI() {
        InformationRequest RFI = Entities.getRFIs().getLatest();
        InformationRequest newRFI = getRandomRFI();
        int response = service.add(newRFI);
        context.put("code", response);
        context.put("requestRFI", newRFI);
    }

    @Then("RFI is updated")
    public void rfiIsUpdated() {
        rfiCorrect();
    }

    @When("I get details of created RFI")
    public void rfiDetailsView() {
        log.info("Starting step of getting details of RFI...");
        InformationRequest createdRFI = Entities.getRFIs().getLatest();
        InformationRequest requestRFIView = service.view(createdRFI.getId());
        log.debug("received RFI from response:"+requestRFIView);
        context.put("requestRFIView", requestRFIView);
    }

    @Then("RFI details get via details are correct")
    public void RFIDetailsViewCorrect() {
        log.info("Comparing two RFIs...");
        InformationRequest createdRFI = Entities.getRFIs().getLatest();
        InformationRequest requestRFIView = context.get("requestRFIView", InformationRequest.class);
        Assert.assertTrue(createdRFI.equals(requestRFIView));
    }

    @When("I create new RFI in status $status")
    public void createRFIInStatus(String status) {
        InformationRequest RFI = getRandomRFI();
        RFI.setState(status.toUpperCase());
        sendRFI(RFI);
    }

    @When("I delete created RFI")
    public void deleteRFI() {
        InformationRequest RFI = Entities.getRFIs().getLatest();
        int response = service.remove(RFI);
        context.put("code",  response);
        context.put("rfi", RFI);
    }

    @When("I cancel RFI")
    public void cancelRFI() {
        InformationRequest RFI = Entities.getRFIs().getLatest();
        int response = service.cancel(RFI);
        context.put("code", response);
    }

    @When("I take ownership of RFI")
    public void assignRFI() {
        InformationRequest RFI = Entities.getRFIs().getLatest();
        int response = service.assign(RFI);
        context.put("code", response);
    }

    @Then("RFI has status Assigned and assigned to current user")
    public void checkAssignedRFI(){
        InformationRequest RFI = Entities.getRFIs().getLatest();
        LoggedUser currentUser = appContext.getLoggedUser();

        Verify.shouldBe(Conditions.equals(RFI.getState(), "ASSIGNED"));
        Verify.shouldBe(Conditions.equals(RFI.getAssignedTo(), currentUser.getId()));
    }
    
    
    static InformationRequest getRandomRFI() {
        return objectInitializer.randomEntity(InformationRequest.class);
    }
}

 **/
