package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.information_managment.masterReport.MasterReport;
import ae.pegasus.framework.model.information_managment.masterReport.MasterReportPayload;

public class MasterReportRequest extends HttpRequest {

    private final static String URI = "/api/reports/workflows/7/";

    public MasterReportRequest() {
        super(URI);
    }


    public MasterReportRequest generateNumber() {
        this
                .setURI(URI + "generate-number/")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(null);
        return this;
    }

    public MasterReportRequest allowedactions(String id) {
        this
                .setURI(URI + "allowed-actions/" + id)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public MasterReportRequest lookupOrgUnits() {
        this
                .setURI(URI + "lookups/")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public MasterReportRequest findFilesCases() {
        this
                .setURI("/api/file-system/files/root?actionFilter=view&page=0&pageSize=50&parentChain=true&sortKey=name&sortOrder=ASC&type=File&type=Case/")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public MasterReportRequest add(MasterReport masterReport, String actionId) {
        MasterReportPayload reportPayload = new MasterReportPayload();
        reportPayload.setData(masterReport);
        this
                .setURI(URI + "perform-action/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(reportPayload);
        return this;
    }

    public MasterReportRequest addNoPayload(MasterReport masterReport, String actionId) {
        this
                .setURI(URI + "perform-action/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(masterReport);
        return this;
    }

    public MasterReportRequest possibleOwners(MasterReport masterReport, String actionId) {
        this
                .setURI(URI + "possible-owners/" + actionId)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(masterReport);
        return this;
    }

    public MasterReportRequest view(String id) {
        this
                .setURI(URI + "model/" + id + "?userAction=true")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public MasterReportRequest remove(MasterReport masterReport) {
        MasterReportPayload reportPayload = new MasterReportPayload();
        reportPayload.setData(masterReport);
        this
                .setURI(URI + "perform-action/1-0")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(reportPayload);
        return this;
    }
}
