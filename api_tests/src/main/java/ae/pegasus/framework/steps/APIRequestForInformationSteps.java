package ae.pegasus.framework.steps;

import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.Result;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.model.information_managment.NextOwners;
import ae.pegasus.framework.model.information_managment.OrgUnit;
import ae.pegasus.framework.model.information_managment.PossibleActions;
import ae.pegasus.framework.model.information_managment.rfi.RequestForInformation;
import ae.pegasus.framework.services.RequestForInformationService;
import org.apache.commons.lang3.RandomStringUtils;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class APIRequestForInformationSteps extends APISteps {

    public static RequestForInformationService serviceRequestForInformation = new RequestForInformationService();

    @When("I send generate RFI number request")
    public void sendGenerateRFINumberRequest() {
        Result rfiNo = new Result();
        OperationResult<Result> operationResult = serviceRequestForInformation.generateNumber();
        rfiNo.setResult(operationResult.getEntity().getResult());

        RequestForInformation rfiClean = Entities.getRequestForInformations().getLatest();
        if (rfiClean != null) {
            Entities.getRequestForInformations().removeEntity(rfiClean);
        }

        context.put("rfiNo", rfiNo);
    }

    @When("I get allowed RFI actions")
    public void sendGetAllowedRFIActions() {
        String imId = Entities.getRequestForInformations().getLatest() == null ? "" : Entities.getRequestForInformations().getLatest().getId();
        OperationResult<List<PossibleActions>> operationResult = serviceRequestForInformation.allowedactions(imId);
        List<PossibleActions> possibleActions = operationResult.getEntity();
        context.put("possibleActions", possibleActions);
    }

    @When("I send $state a $type RFI request")
    public void sendCreateRFIRequest(String state, String type) {

        switch (state) {
            case "Save as Draft":
                saveAsDraftRFI(state, type);
                break;
            case "Delete":
                deleteRFI(state);
                break;
            case "Submit for Approval":
                submitRFI(state);
                break;
            case "Cancel":
                cancelRFI(state);
                break;
            case "Take Ownership":
                takeOwnershipRFI(state);
                break;
            case "Submit for Review":
                submitRFI(state);
                break;
            case "Send":
                sendRFI(state);
                break;
            case "Approve":
                compliteRFI(state);
                break;
            case "Endorse":
                endorseRFI(state);
            case "Unassign":
                unassignRFI(state);
            case "Save":
                editRFI(state);
        }
    }

    private void saveAsDraftRFI(String state, String type) {
        RequestForInformation requestForInformation = new RequestForInformation();
            Result rfitNo = context.get("rfiNo", Result.class);
            serviceRequestForInformation.buildRFI(requestForInformation, rfitNo, type);
            context.put("requestForInformation", requestForInformation);
            String actionId = getRequestAdress(state);
            OperationResult<RequestForInformation> operationResult = serviceRequestForInformation.add(requestForInformation, actionId);
            RequestForInformation reportID = operationResult.getEntity();
            context.put("reportID", reportID.getId());
        }


    @When("I send view a RFI request")
    public void sendViewRFIRequest() {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        String id = lastRFI.getId();
        serviceRequestForInformation.view(id);
    }

    private void deleteRFI(String state) {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        String actionId = getRequestAdress(state);
        serviceRequestForInformation.remove(lastRFI, actionId);
    }

    @When("I send get owner a RFI in $state request")
    public void sendGetOwnerRFIRequest(String state) {
        RequestForInformation lastreport = Entities.getRequestForInformations().getLatest();
        String actionId = getRequestAdress(state);

        OperationResult<List<NextOwners>> nextOwner = serviceRequestForInformation.possibleOwner(lastreport, actionId);
        context.put("nextOwner", nextOwner.getEntity());
    }

    @When("I send get owner a report in $state request")
    public void sendGetOwnerReportRequest(String state) {
        RequestForInformation lastreport = Entities.getRequestForInformations().getLatest();
        String actionId = getRequestAdress(state);

        OperationResult<List<NextOwners>> nextOwner = serviceRequestForInformation.possibleOwner(lastreport, actionId);
        context.put("nextOwner", nextOwner.getEntity());
    }

    public void submitRFI(String state) {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        List<NextOwners> nextOwners = context.get("nextOwner", List.class);
        lastRFI.setNextOwners(nextOwners);

        String actionId = getRequestAdress(state);
        lastRFI.setComment("QE_auto " + RandomStringUtils.randomAlphabetic(5));
        serviceRequestForInformation.submit(lastRFI, actionId);
    }

    @When("I send complete took ownership a RFI request")
    public void sendSubmitTookOwnershipRFIRequest() {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        lastRFI.setComment("QE_auto " + RandomStringUtils.randomAlphabetic(5));
        serviceRequestForInformation.submitTookOwnership(lastRFI);
    }

    public void compliteRFI(String state) {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        List<NextOwners> nextOwners = context.get("nextOwner", List.class);
        lastRFI.setNextOwners(nextOwners);

        String actionId = getRequestAdress(state);
        serviceRequestForInformation.approve(lastRFI, actionId);
    }

    private void cancelRFI(String state) {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        lastRFI.setComment("QE_auto " + RandomStringUtils.randomAlphabetic(5));
        String actionId = getRequestAdress(state);
        serviceRequestForInformation.cancel(lastRFI, actionId);
    }

    private void sendRFI(String state) {
        RequestForInformation RFI = Entities.getRequestForInformations().getLatest();
        List<OrgUnit> currentOrgUnits = RFI.getOrgUnits();
        List<NextOwners> nextOwners = new ArrayList<>();
        String actionId = getRequestAdress(state);
//        serviceRequestForInformation.setNextOwnersTeam(currentOrgUnits, nextOwners);
        RFI.setNextOwners(nextOwners);
        serviceRequestForInformation.send(RFI, actionId);
    }

    private void takeOwnershipRFI(String state) {
        RequestForInformation report = Entities.getRequestForInformations().getLatest();
        List<NextOwners> nextOwners = context.get("nextOwner", List.class);
        report.setNextOwners(nextOwners);
        String actionId = getRequestAdress(state);
        serviceRequestForInformation.takeOwnership(report, actionId);
    }

    private void unassignRFI(String state) {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        String actionId = getRequestAdress(state);

        List<NextOwners> nextOwners = context.get("nextOwner", List.class);
        lastRFI.setNextOwners(nextOwners);
        lastRFI.setComment("QE_auto " + RandomStringUtils.randomAlphabetic(10));
        serviceRequestForInformation.unassign(lastRFI, actionId);
    }

    private void editRFI(String state) {
        RequestForInformation createdRFI = Entities.getRequestForInformations().getLatest();
        String actionId = getRequestAdress(state);
//        createdRFI.setSubject("QE_auto " + RandomStringUtils.randomAlphabetic(5));
//        createdRFI.setRequired("QE_auto " + RandomStringUtils.randomAlphabetic(5));
        createdRFI.setSubject("1");
        createdRFI.setRequired("1");
        context.put("requestForInformation", createdRFI);
        serviceRequestForInformation.update(createdRFI, actionId);
    }

    private void endorseRFI(String state) {
        RequestForInformation report = Entities.getRequestForInformations().getLatest();
        List<NextOwners> nextOwners = context.get("nextOwner", List.class);
        report.setNextOwners(nextOwners);
        String actionId = getRequestAdress(state);
        serviceRequestForInformation.endorse(report, actionId);
    }

    @Then("RFI is correct")
    public void rfiIsCreated() {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        checkRFI(lastRFI);
    }

    @Then("RFI is submitted")
    public void rfiIsSubmitted() {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        checkAwaitingApproval(lastRFI);
    }

    @Then("RFI is cancelled")
    public void rfiIsCancelled() {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        checkRFI(lastRFI);
        assertEquals(lastRFI.getState(), "Cancelled");
        assertEquals(lastRFI.getStateId(), "7");
        assertEquals(lastRFI.getStateType(), "FINAL");
        assertEquals(lastRFI.getWfId(), "2");
    }

    @Then("RFI is ownershipped")
    public void rfiIsOwnershipped() {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        checkRFI(lastRFI);
        assertEquals(lastRFI.getState(), "Under Assignment");
        assertEquals(lastRFI.getStateId(), "4");
        assertEquals(lastRFI.getStateType(), "IN_PROGRESS");
        assertEquals(lastRFI.getWfId(), "2");
    }

    @Then("RFI is completed")
    public void rfiIsComplited() {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        checkRFI(lastRFI);
        assertEquals(lastRFI.getState(), "Completed");
        assertEquals(lastRFI.getStateId(), "5");
        assertEquals(lastRFI.getStateType(), "FINAL");
        assertEquals(lastRFI.getWfId(), "2");
    }

    @Then("RFI is Awaiting Assignment")
    public void rfiIsAwaitingAssignment() {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        checkRFI(lastRFI);
        assertEquals(lastRFI.getState(), "Awaiting Assignment");
        assertEquals(lastRFI.getStateId(), "3");
        assertEquals(lastRFI.getStateType(), "INITIAL");
        assertEquals(lastRFI.getWfId(), "2");
    }

    @Then("RFI is unassigned")
    public void rfiIsUnassigned() {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        checkRFI(lastRFI);
        checkAwaitingAssignment(lastRFI);
    }

    @Then("RFI is sent")
    public void rfiIsSent() {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        checkRFI(lastRFI);
        checkAwaitingAssignment(lastRFI);
    }

    @Then("RFI is approved")
    public void rfiIsApproved() {
        RequestForInformation lastRFI = Entities.getRequestForInformations().getLatest();
        checkRFI(lastRFI);
        checkAwaitingAssignment(lastRFI);
    }

    private void checkAwaitingAssignment(RequestForInformation lastRFI) {
        assertEquals(lastRFI.getState(), "Awaiting Assignment");
        assertEquals(lastRFI.getStateId(), "3");
        assertEquals(lastRFI.getStateType(), "INITIAL");
        assertEquals(lastRFI.getWfId(), "2");
    }


    private void checkAwaitingApproval(RequestForInformation lastRFI) {
        assertEquals(lastRFI.getState(), "Awaiting Approval");
        assertEquals(lastRFI.getStateId(), "2");
        assertEquals(lastRFI.getStateType(), "INITIAL");
        assertEquals(lastRFI.getWfId(), "2");
    }

    private void checkRFI(RequestForInformation lastRFI) {
        RequestForInformation createdRFI = context.get("requestForInformation", RequestForInformation.class);
        assertEquals(lastRFI.getClassification(), createdRFI.getClassification());
        assertEquals(lastRFI.getManualNo(), createdRFI.getManualNo());
        assertEquals(lastRFI.getRequired(), createdRFI.getRequired());
        assertEquals(lastRFI.getObjectType(), "RFI");
    }

    private String getRequestAdress(String state) {
        List<PossibleActions> possibleActions = context.get("possibleActions", List.class);
        PossibleActions possibleAction = possibleActions.stream()
                .filter(w -> state.equals(w.getActionName()))
                .findAny()
                .orElse(null);
        return possibleAction.getId();
    }
}