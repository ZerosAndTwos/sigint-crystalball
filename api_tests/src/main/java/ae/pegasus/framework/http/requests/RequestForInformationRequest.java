package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.information_managment.rfi.RequestForInformation;
import ae.pegasus.framework.model.information_managment.rfi.RequestForInformationPayload;

public class RequestForInformationRequest extends HttpRequest {

    private final static String URI = "/api/reports/workflows/";

    public RequestForInformationRequest() {
        super(URI);
    }

    public RequestForInformationRequest generateNumber() {
        this
                .setURI(URI + "2/generate-number/")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(null);
        return this;
    }

    public RequestForInformationRequest add(RequestForInformation requestForInformation, String actionId) {
        RequestForInformationPayload requestForInformationPayload = new RequestForInformationPayload();
        requestForInformationPayload.setData(requestForInformation);
        this
                .setURI(URI + "2/perform-action/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformationPayload);
        return this;
    }

    public RequestForInformationRequest view(String id) {
        this
                .setURI(URI + "2/model/" + id + "?userAction=true")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public RequestForInformationRequest submit(RequestForInformation requestForInformation, String actionId) {
        RequestForInformationPayload requestForInformationPayload = new RequestForInformationPayload();
        requestForInformationPayload.setData(requestForInformation);
        this
                .setURI(URI + "2/perform-action/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformationPayload);
        return this;
    }

    public RequestForInformationRequest remove(RequestForInformation requestForInformation, String actionId) {
        RequestForInformationPayload requestForInformationPayload = new RequestForInformationPayload();
        requestForInformationPayload.setData(requestForInformation);
        this
                .setURI(URI + "1/perform-action/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformationPayload);
        return this;
    }

    public RequestForInformationRequest cancel(RequestForInformation requestForInformation, String actionId) {
        RequestForInformationPayload requestForInformationPayload = new RequestForInformationPayload();
        requestForInformationPayload.setData(requestForInformation);
        this
                .setURI(URI + "2/perform-action/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformationPayload);
        return this;
    }

    public RequestForInformationRequest approve(RequestForInformation requestForInformation, String actionId) {
        RequestForInformationPayload requestForInformationPayload = new RequestForInformationPayload();
        requestForInformationPayload.setData(requestForInformation);
        this
                .setURI(URI + "2/perform-action/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformationPayload);
        return this;
    }

    public RequestForInformationRequest send(RequestForInformation requestForInformation, String actionId) {
        RequestForInformationPayload requestForInformationPayload = new RequestForInformationPayload();
        requestForInformationPayload.setData(requestForInformation);
        this
                .setURI(URI + "2/perform-action/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformationPayload);
        return this;
    }

    public RequestForInformationRequest possibleOwnersMembers(RequestForInformation requestForInformation, String actionId) {
        this
                .setURI(URI + "2/possible-owners/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformation);
        return this;
    }

    public RequestForInformationRequest possibleOwnersTeams(RequestForInformation requestForInformation) {
        this
                .setURI(URI + "2/possible-owners/1-3")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformation);
        return this;
    }

    public RequestForInformationRequest takeOwnership(RequestForInformation requestForInformation, String actionId) {
        RequestForInformationPayload requestForInformationPayload = new RequestForInformationPayload();
        requestForInformationPayload.setData(requestForInformation);
        this
                .setURI(URI + "2/perform-action/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformationPayload);
        return this;
    }

    public RequestForInformationRequest endorse(RequestForInformation requestForInformation, String actionId) {
        RequestForInformationPayload requestForInformationPayload = new RequestForInformationPayload();
        requestForInformationPayload.setData(requestForInformation);
        this
                .setURI(URI + "2/perform-action/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformationPayload);
        return this;
    }

    public RequestForInformationRequest possibleOwner(RequestForInformation requestForInformationPayload, String actionId) {
        this
                .setURI(URI + "2/possible-owners/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformationPayload);
        return this;
    }

    public RequestForInformationRequest submitTookOwnership(RequestForInformation requestForInformation) {
        RequestForInformationPayload requestForInformationPayload = new RequestForInformationPayload();
        requestForInformationPayload.setData(requestForInformation);
        this
                .setURI(URI + "2/perform-action/12")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformationPayload);
        return this;
    }

    public RequestForInformationRequest unassign(RequestForInformation requestForInformation, String actionId) {
        RequestForInformationPayload requestForInformationPayload = new RequestForInformationPayload();
        requestForInformationPayload.setData(requestForInformation);
        this
                .setURI(URI + "2/perform-action/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformationPayload);
        return this;
    }

    public RequestForInformationRequest endorseAndSendForApprovalRequest(RequestForInformation requestForInformation) {
        RequestForInformationPayload requestForInformationPayload = new RequestForInformationPayload();
        requestForInformationPayload.setData(requestForInformation);
        this
                .setURI(URI + "2/perform-action/6")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(requestForInformationPayload);
        return this;
    }

    public RequestForInformationRequest allowedactions(String id) {
        this
                .setURI(URI + "2/allowed-actions/" + id)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }
}
