package ae.pegasus.framework.steps;

import ae.pegasus.framework.app_context.properties.G4Properties;
import ae.pegasus.framework.zapi.ZAPIService;
import org.apache.log4j.Logger;

public class GlobalSteps {

    static Logger log = Logger.getLogger(GlobalSteps.class);

//    @AfterStories
    public void reportResults() {
        ZAPIService service = new ZAPIService();
        Boolean shouldReport = G4Properties.getRunProperties().shouldReport();
        try {
            if (shouldReport) {
                service.reportToZephyr();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
