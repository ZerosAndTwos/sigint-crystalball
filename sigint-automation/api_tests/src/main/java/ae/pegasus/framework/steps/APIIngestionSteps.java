package ae.pegasus.framework.steps;

import ae.pegasus.framework.ingestion.IngestionService;
import ae.pegasus.framework.ingestion.docker.DockerDataGenerator;
import ae.pegasus.framework.ingestion.docker.IDockerAdapter;
import ae.pegasus.framework.ingestion.docker.adapters.*;
import ae.pegasus.framework.model.G4File;
import ae.pegasus.framework.model.Source;
import ae.pegasus.framework.utils.RandomGenerator;
import org.jbehave.core.annotations.Given;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static ae.pegasus.framework.ingestion.IngestionService.INJECTIONS_FILE;
import static org.junit.Assert.assertNotNull;

public class APIIngestionSteps extends APISteps {

    @Given("I pick random file from ingestion files list")
    @SuppressWarnings("unchecked")
    public void getRandomIngestionFile() {
        List<File> files = context.get("g4files", List.class);

        File randomFile = RandomGenerator.getRandomItemFromList((files));
        context.put("g4file", new G4File(randomFile.getPath()));
    }

    @Given("$sType - $rType files with $rCount records are generated")
    public void generateIngestionFiles(String sType, String rType, String rCount) {

        IDockerAdapter dockerAdapter = null;
        String sTypeUpper = sType.toUpperCase();
        String rTypeUpper = rType.toUpperCase();
        switch (sTypeUpper) {
            case "S":
                switch (rTypeUpper) {
                    case "SMS":
                        dockerAdapter = new SSMSDockerAdapter();
                        break;
                    case "CDR":
                        dockerAdapter = new SCDRDockerAdapter();
                        break;
                    case "VLR":
                        dockerAdapter = new SVLRDockerAdapter();
                        break;
                    case "CELL":
                        dockerAdapter = new SCELLDockerAdapter();
                        break;
                    case "VOICE":
                        dockerAdapter = new SVoiceDockerAdapter();
                        break;
                    case "FAX":
                        dockerAdapter = new SFaxDockerAdapter();
                        break;
                    case "EMAIL":
                        dockerAdapter = new SEmailDockerAdapter();
                        break;
                    case "VOIP":
                        dockerAdapter = new SVoipDockerAdapter();
                        break;
                }
                break;
            case "T":
                switch (rTypeUpper) {
                    case "SMS":
                        dockerAdapter = new TSMSDockerAdapter();
                        break;
                    case "VOICE":
                        dockerAdapter = new TVoiceDockerAdapter();
                        break;
                }
                break;
            case "F":
                dockerAdapter = new FSMSDockerAdapter();
                break;
            case "PHONEBOOK":
                dockerAdapter = new PhonebookDockerAdapter();
                break;
            case "O":
                switch (rTypeUpper) {
                    case "SMS":
                        dockerAdapter = new OSMSDockerAdapter();
                        break;
                }
                break;
            case "DU":
                switch (rTypeUpper) {
                    case "SMS":
                        dockerAdapter = new DUSMSDockerAdapter();
                        break;
                    case "VLR":
                        dockerAdapter = new DUVLRDockerAdapter();
                        break;
                    case "IMSITOSUBCONSUMER":
                        dockerAdapter = new DUIMSISubConsumerDockerAdapter();
                        break;
                    case "IMSITOSUBMOBILE":
                        dockerAdapter = new DUIMSISubMobileDockerAdapter();
                        break;
                    case "IMSITOSUBENTERPRISE":
                        dockerAdapter = new DUIMSISubEnterpriseDockerAdapter();
                        break;
                    case "NLDVLR":
                        dockerAdapter = new DUNLDVLRDockerAdapter();
                        break;
                    case "NLDHO":
                        dockerAdapter = new DUNLDHODockerAdapter();
                        break;
                    case "NLD4GLUR":
                        dockerAdapter = new DUNLD4glurDockerAdapter();
                        break;
                }
                break;
            case "E":
                switch (rTypeUpper) {
                    case "CDR":
                        dockerAdapter = new EtisalatCDRDockerAdapter();
                        break;
                    case "SUBSCRIBER1":
                        dockerAdapter = new EtisalatSubscriber1DockerAdapter();
                        break;
                    case "SUBSCRIBER2":
                        dockerAdapter = new EtisalatSubscriber2DockerAdapter();
                        break;
                    case "CELLTOWER1":
                        dockerAdapter = new EtisalatCellTower1DockerAdapter();
                        break;
                    case "CELLTOWER2":
                        dockerAdapter = new EtisalatCellTower2DockerAdapter();
                        break;
                    case "SMS2":
                        dockerAdapter = new EtisalatSMS2DockerAdapter();
                        break;
                    case "SMS3":
                        dockerAdapter = new EtisalatSMS3DockerAdapter();
                        break;
                    case "SMS4":
                        dockerAdapter = new EtisalatSMS4DockerAdapter();
                        break;
                    case "NLD_AIF":
                        dockerAdapter = new EtisalatNLDAIFDockerAdapter();
                        break;
                    case "NLD_IUCS":
                        dockerAdapter = new EtisalatNLDIUCSDockerAdapter();
                        break;
                    case "NLD_IUPS":
                        dockerAdapter = new EtisalatNLDIUPSDockerAdapter();
                        break;
                }
                break;
            default:
                log.error("Unknown source type: " + sType);
                break;
        }
        assertNotNull("Can't assign docker adapter", dockerAdapter);

        IngestionService ingestionService = new IngestionService(new DockerDataGenerator(dockerAdapter));
        if (!IngestionService.pathExists(INJECTIONS_FILE)) {
            IngestionService.cleanIngestionDir();
        } else {
            log.info("Predefined data detected. Skipping ingestion directory cleanup...");
        }
        List<File> files = ingestionService.getGenerator().generateIngestionFiles(rCount);
        IngestionService.removePath(INJECTIONS_FILE);

        context.put("g4files", files);
    }

    @Given("I create remote path for ingestion")
    public void createRemotePath() {
        Source source = context.get("source", Source.class);
        SimpleDateFormat currentDate = new SimpleDateFormat("/yyyy/MM/dd/HH_mm_ss.SSS/");
        currentDate.setTimeZone(TimeZone.getTimeZone("GMT"));
        String path = "/" + source.getType() + "/" + source.getName() + currentDate.format(new Date());

        log.info("Remote path: " + path);
        context.put("remotePath", path);
    }

    @Given("I clean up ingestion directory")
    public void cleanupIngestionDir() {
        IngestionService.cleanIngestionDir();
    }
}
