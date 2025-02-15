package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.G4File;
import ae.pegasus.framework.model.PegasusMediaType;

public class TargetMigrationRequest extends HttpRequest {

    private static final String URI = "/api/profiler/upload";

    public TargetMigrationRequest() {
        super(URI);
    }

    public TargetMigrationRequest uploadTargets(G4File file) {
        setURI(URI);
        setHttpMethod(HttpMethod.POST);
        addBodyFile("file", file, PegasusMediaType.PEGASUS_XLSX);
        addBodyString("filename", "import-data.xlsx");
        return this;
    }
}
