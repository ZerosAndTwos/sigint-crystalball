package steps;

import static org.junit.Assert.assertNotNull;

import ingestion.IngestionService;
import ingestion.docker.DockerDataGenerator;
import ingestion.docker.IDockerAdapter;
import ingestion.docker.adapters.tdata.FSMSDockerAdapter;
import ingestion.docker.adapters.tdata.SSMSDockerAdapter;
import ingestion.docker.adapters.tdata.TSMSDockerAdapter;
import model.G4File;
import model.SourceType;
import org.jbehave.core.annotations.Given;

public class APIDataGeneratorSteps extends APISteps {

    @Given("$sType - $rType data file with $rCount records was generated")
    public void generateEntityList(String sType, String rType, String rCount) {

        SourceType sourceType = SourceType.valueOf(sType);

        IDockerAdapter dockerAdapter = null;
        switch (sourceType) {
            case Strategic:
                dockerAdapter = new SSMSDockerAdapter();
                break;
            case Tactical:
                dockerAdapter = new TSMSDockerAdapter();
                break;
            case F:
                dockerAdapter = new FSMSDockerAdapter();
                break;
            default:
                log.error("Unknown source type: " + sType);
                break;
        }
        assertNotNull("Can't assign proper ingestion.docker adapter", dockerAdapter);

        IngestionService ingestionService = new IngestionService(new DockerDataGenerator(dockerAdapter));
        IngestionService.cleanIngestionDir();
        G4File file = ingestionService.getGenerator().generateIngestionFile(rCount);
        context.put("g4file", file);
    }

    @Given("$sType - $rType data file is renamed to make filename unique")
    public void renameIngestionFile(String sType, String rType) {
        G4File sourceFile = context.get("g4file", G4File.class);
        G4File renamed = IngestionService.renameFile(sourceFile, String.format("%s_%s", sType, rType));

        context.put("g4file", renamed);
    }
}
