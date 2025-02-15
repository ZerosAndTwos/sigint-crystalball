package ae.pegasus.framework.steps;


import ae.pegasus.framework.controllers.APILogin;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.model.information_managment.Link;
import ae.pegasus.framework.model.information_managment.NextOwners;
import ae.pegasus.framework.model.information_managment.OrgUnit;
import ae.pegasus.framework.model.information_managment.PossibleActions;
import ae.pegasus.framework.model.information_managment.rfa.RequestForApprove;
import ae.pegasus.framework.services.RequestForApproveService;
import ae.pegasus.framework.services.SearchService;
import org.apache.commons.lang3.RandomStringUtils;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class APIRequestForApproveSteps extends APISteps {

    public static RequestForApproveService serviceRequestForApprove = new RequestForApproveService();
    private static APIReportSteps serviceReport = new APIReportSteps();

    @When("I send generate RFA number request")
    public void sendGenerateRFANumberRequest() {
        Result rfaNo = new Result();
        OperationResult<Result> operationResult = serviceRequestForApprove.generateNumber();
        rfaNo.setResult(operationResult.getEntity().getResult());
        context.put("rfaNo", rfaNo);
    }

    @When("I save logged user")
    public void saveLoggedUser() {
        LoggedUser loggedUser = appContext.getLoggedUser();
        context.put("loggedUser", loggedUser);
    }

    @When("I get allowed RFA actions")
    public void sendGetAllowedMRActions() {
        String imId = Entities.getRequestForApproves().getLatest() == null ? "" : Entities.getRequestForApproves().getLatest().getId();

        if (imId.equals("") && context.get("reportID", String.class) != null) {
            imId = context.get("reportID", String.class);
        }

        OperationResult<List<PossibleActions>> operationResult = serviceRequestForApprove.possibleaction(imId);
        List<PossibleActions> possibleActions = operationResult.getEntity();
        context.put("possibleActions", possibleActions);
    }

    @When("I send $state a RFA request")
    public void sendMoveToStateRequest(String state) {
        switch (state) {
            case "Take Ownership":
            case "Assign":
            case "Send for Approval":
            case "Re-assign":
            case "Unassign":
                submit(state);
                break;
            case "Approve":
            case "Reject":
                approve(state);
                break;
            case "Save as Draft":
                saveAsDraft(state);
                break;
            case "View":
                view();
                break;
            case "Delete":
                delete();
                break;
            case "Save":
                edit(state);
                break;
            case "Cancel":
                break;
            case "Return to Author":
                break;
            default:
                log.error("State is not found");
        }
    }

    public void saveAsDraft(String state) {
        RequestForApprove requestForApprove = new RequestForApprove();
        Result rfaNo = context.get("rfaNo", Result.class);
        List<SearchRecord> entities = context.get("searchEntities", List.class);

        if (state.equals("Save as Draft")) {
            serviceRequestForApprove.buildRFA(requestForApprove, rfaNo, entities);
            String actionId = serviceReport.getRequestAdress(state);
            context.put("requestForApprove", requestForApprove);
            sleep(120000); //FIXME
            OperationResult<RequestForApprove> operationResult = serviceRequestForApprove.add(requestForApprove, actionId);
            RequestForApprove reportResult = operationResult.getEntity();
            context.put("reportID", reportResult.getId());

        } else if (state.equals("Save")) {
            RequestForApprove report = Entities.getRequestForApproves().getLatest();
            String actionId = serviceReport.getRequestAdress(state);
            report.setSubject("qe_" + RandomStringUtils.randomAlphabetic(10));
            OperationResult<RequestForApprove> operationResult = serviceRequestForApprove.add(report, actionId);
            RequestForApprove reportResult = operationResult.getEntity();
            context.put("expectedReport", reportResult);
        }
    }

    private void submit(String state) {
        RequestForApprove lastreport = Entities.getRequestForApproves().getLatest();
        RequestForApprove requestForApprove = context.get("requestForApprove", RequestForApprove.class);
        String actionId = serviceReport.getRequestAdress(state);
        List<NextOwners> nextOwnersList = context.get("nextOwner", List.class);
        NextOwners nextOwner = nextOwnersList.get(0);
        List<NextOwners> nextOwners = new ArrayList<>();
        nextOwners.add(nextOwner);

        if (requestForApprove == null || lastreport == null) {
            sleep(120000); //FIXME
            requestForApprove.setNextOwners(nextOwners);
            requestForApprove.setComment("comment");
            OperationResult<RequestForApprove> operationResult = serviceRequestForApprove.add(requestForApprove, actionId);
            context.put("reportID", operationResult.getEntity().getId());
            context.put("requestForApprove", requestForApprove);
        } else {
            lastreport.setNextOwners(nextOwners);
            lastreport.setComment("comment");
            serviceRequestForApprove.add(lastreport, actionId);
        }
    }

    private void approve(String state) {
        RequestForApprove lastreport = Entities.getRequestForApproves().getLatest();
        String actionId = serviceReport.getRequestAdress(state);
        lastreport.setComment("comment");
        serviceRequestForApprove.add(lastreport, actionId);
    }

    @When("I send get owner a RFA in $state request")
    public void sendGetOwnerReportRequest(String state) {
        RequestForApprove lastreport = Entities.getRequestForApproves().getLatest();
        RequestForApprove requestForApprove = new RequestForApprove();
        String actionId = serviceReport.getRequestAdress(state);
        OperationResult<List<NextOwners>> nextOwner;
        if (lastreport == null) {
            List<SearchRecord> entities = context.get("searchEntities", List.class);
            Result rfaNo = context.get("rfaNo", Result.class);
            serviceRequestForApprove.buildRFA(requestForApprove, rfaNo, entities);
            context.put("requestForApprove", requestForApprove);
        }
        if (lastreport != null) {
            nextOwner = serviceRequestForApprove.possibleOwner(lastreport, actionId);
        } else {
            nextOwner = serviceRequestForApprove.possibleOwner(requestForApprove, actionId);
        }
        context.put("nextOwner", nextOwner.getEntity());
    }

    @When("I send Send for approval a RFA request")
    public void sendSendRFIRequest() {
        RequestForApprove RFA = context.get("requestForApprove", RequestForApprove.class);
        List<OrgUnit> currentOrgUnits = RFA.getOrgUnits();
        List<NextOwners> nextOwners = new ArrayList<>();
//        serviceRequestForApprove.setNextOwnersTeam(currentOrgUnits, nextOwners);
        RFA.setNextOwners(nextOwners);
        sleep(60000); //FIXME
        serviceRequestForApprove.sendForApprove(RFA);
    }

    public void view() {
        String id = context.get("reportID", String.class);
        serviceRequestForApprove.view(id);
    }

    public void delete() {
        RequestForApprove lastRFA = Entities.getRequestForApproves().getLatest();
        serviceRequestForApprove.remove(lastRFA);
    }

    public void edit(String state) {
        RequestForApprove requestForApprove = new RequestForApprove();
        RequestForApprove lastRFA = Entities.getRequestForApproves().getLatest();
        String actionId = serviceReport.getRequestAdress(state);
        lastRFA.setDescription("QE Auto" + RandomStringUtils.randomAlphabetic(5));
        lastRFA.setSubject("QE Auto" + RandomStringUtils.randomAlphabetic(5));
        context.put("requestForApprove", requestForApprove);
        serviceRequestForApprove.add(lastRFA, actionId);
    }

    @When("I send take ownership a RFA request")
    public void sendTakeOwnershipRFARequest() {
        RequestForApprove lastRFA = Entities.getRequestForApproves().getLatest();
        serviceRequestForApprove.takeOwnership(lastRFA);
    }

    @When("I send remove ownership a RFA request")
    public void sendRemoveOwnershipRFARequest() {
        RequestForApprove lastRFA = Entities.getRequestForApproves().getLatest();
        lastRFA.setComment("Auto QE " + RandomStringUtils.random(5));
        serviceRequestForApprove.removeOwnership(lastRFA);
    }

    @When("I send reject a RFA request")
    public void sendRejectRFARequest() {
        RequestForApprove lastRFA = Entities.getRequestForApproves().getLatest();
        lastRFA.setComment("Auto QE " + RandomStringUtils.random(5));
        serviceRequestForApprove.reject(lastRFA);
    }

    @When("I send approve a RFA request")
    public void sendApproveRFARequest() {
        RequestForApprove lastRFA = Entities.getRequestForApproves().getLatest();
        lastRFA.setComment("Auto QE " + RandomStringUtils.random(5));
        serviceRequestForApprove.approve(lastRFA);
    }

    @When("I send search for accessed audio request")
    public void sendGotAccessAudioRequest() {
        RequestForApprove lastRFA = Entities.getRequestForApproves().getLatest();

        CBSearchFilter filter = getIdLink(lastRFA);

        SearchService serviceSearch = new SearchService();
        OperationResult<List<SearchEntity>> operationResult = serviceSearch.search(filter);
        List<SearchEntity> searchEntities = operationResult.getEntity();
        assertNotNull(searchEntities);
        context.put("searchEntities", searchEntities);

    }

    private CBSearchFilter getIdLink(RequestForApprove lastRFA) {
        List<String> ids = new ArrayList<>();
        for (Link requestForApprove : lastRFA.getLinks()) {
            String request = requestForApprove.getLinkId();
            ids.add(request);
        }
        String id = ids.toString()
                .replace(",", "|")
                .replaceAll("[\\[\\]]", "");
        CBSearchFilter filter = context.get("cbSearchFilter", CBSearchFilter.class);
        filter.setQuery("id:(" + id + ")");
        return filter;
    }

    @When("I as login first user")
    public void loginAsFirstUser() {
        User loggedUser = context.get("loggedUser", LoggedUser.class).getUser();
        String email = loggedUser.getName();
        String password = loggedUser.getPassword();
        APILogin login = new APILogin();
        login.signInAsUser(email, password);
        checkResultSuccess();
    }

    @Then("Audio content is available")
    public void audioIsAvailable() {
        List<SearchRecord> searchRecord = context.get("searchEntities", List.class);
        for (SearchRecord searchEntity : searchRecord) {
            ArrayList<String> ids = (ArrayList<String>) searchEntity.getAttributes().get("UPLOAD_M4A_FILE_ID");
            assertNotNull(ids);
        }
    }

    @Then("User able access to audio")
    public void userAccessToAudio() {
        List<SearchRecord> searchEntities = context.get("searchEntities", List.class);
        for (SearchRecord searchRecord : searchEntities) {
            String id = searchRecord.getAttributes().get("UPLOAD_M4A_FILE_ID")
                    .toString()
                    .replaceAll("[\\[\\]]", "");
            serviceRequestForApprove.getAudioContent(id);
        }
    }

    @Then("RFA is created")
    public void rfaIsCreated() {
        RequestForApprove lastRFA = Entities.getRequestForApproves().getLatest();
        checkRFA(lastRFA);
    }

    @Then("RFA is $state and $stateType")
    public void checkRFAState(String state, String stateType) {
        RequestForApprove lastRFA = Entities.getRequestForApproves().getLatest();
        RequestForApprove createdRFA = context.get("requestForApprove", RequestForApprove.class);
        checkRFA(lastRFA);
        assertEquals(lastRFA.getClassification(), createdRFA.getClassification());
        assertEquals(lastRFA.getState(), state);
        assertEquals(lastRFA.getStateType(), stateType);
    }

    private void checkRFA(RequestForApprove lastRFA) {
        RequestForApprove createdRFA = context.get("requestForApprove", RequestForApprove.class);
        assertEquals(lastRFA.getClassification(), createdRFA.getClassification());
        assertEquals(lastRFA.getDescription(), createdRFA.getDescription());
        assertEquals(lastRFA.getSubject(), createdRFA.getSubject());
        assertEquals(lastRFA.getObjectType(), "RFA");
    }

}
