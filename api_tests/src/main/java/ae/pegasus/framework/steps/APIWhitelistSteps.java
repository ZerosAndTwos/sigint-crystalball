package ae.pegasus.framework.steps;


import ae.pegasus.framework.error_reporter.ErrorReporter;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.G4File;
import ae.pegasus.framework.model.ImportResult;
import ae.pegasus.framework.model.Whitelist;
import ae.pegasus.framework.model.WhitelistFilter;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.services.WhitelistService;
import ae.pegasus.framework.utils.RandomGenerator;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.ArrayList;
import java.util.List;

import static ae.pegasus.framework.ingestion.IngestionService.cleanImportDir;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.*;

public class APIWhitelistSteps extends APISteps {

    private static WhitelistService service = new WhitelistService();

    private static Whitelist getRandomWhitelist() {
        return objectInitializer.randomEntity(Whitelist.class);
    }

    static List<Whitelist> getRandomWhitelists(int count) {
        return objectInitializer.randomEntities(Whitelist.class, count);
    }

    @When("I send get list of whitelist entries request")
    public void getListOfWhitelistEntries() {
        OperationResult<List<Whitelist>> operationResult = service.list();
        context.put("whitelistEntitiesList", operationResult.getEntity());
    }

    @Then("Whitelists list size is more than $count")
    @SuppressWarnings("unchecked")
    public void whitelistsListShouldBeMoreThen(final String count) {
        int minSize = Integer.valueOf(count);
        List<Whitelist> whitelists = context.get("whitelistEntitiesList", List.class);
        log.info("Whitelists list size: " + whitelists.size());

        assertTrue("List size mismatch. Should be > " + minSize, whitelists.size() > minSize);
    }

    @When("I send create new whitelist entry request")
    public void createWhitelistEntry() {
        Whitelist whitelist = getRandomWhitelist();
        context.put("whitelist", whitelist);
        service.add(whitelist);
    }

    @Then("Whitelist entry is $criteria list")
    @SuppressWarnings("unchecked")
    public void whitelistEntryInCommonList(String criteria) {
        Whitelist whitelist = context.get("whitelist", Whitelist.class);
        List<Whitelist> whitelists = context.get("whitelistEntitiesList", List.class);
        boolean contained = false;
        for (Whitelist elem : whitelists) {
            if (elem.getId().equals(whitelist.getId())) {
                contained = true;
                break;
            }
        }
        if (criteria.equals("in")) {
            assertTrue("Whitelist " + whitelist.getId() + " should be in list", contained);
        } else {
            assertFalse("Whitelist " + whitelist.getId() + " should not be listed", contained);
        }
    }


    @Then("Whitelist entry is correct")
    public void whitelistEntryValidation() {
        Whitelist fromContext = context.get("whitelist", Whitelist.class);
        Whitelist latest = Entities.getWhitelists().getLatest();

        assertThat(fromContext.getIdentifier(), is(equalToIgnoringCase(latest.getIdentifier())));
        assertEquals("type mismatch", fromContext.getType(), latest.getType());
        assertEquals("description mismatch", fromContext.getDescription(), latest.getDescription());

        context.put("whitelist", latest);
    }

    @When("I get random whitelist entry from list")
    @SuppressWarnings("unchecked")
    public void selectRandomWhitelistEntry() {
        List<Whitelist> whitelists = context.get("whitelistEntitiesList", List.class);
        Whitelist whitelist = RandomGenerator.getRandomItemFromList((whitelists));

        assertNotNull(whitelist);
        context.put("whitelist", whitelist);
    }

    @When("I send view whitelist entry request")
    public void viewWhitelistEntry() {
        Whitelist whitelist = context.get("whitelist", Whitelist.class);

        OperationResult<Whitelist> operationResult = service.view(whitelist.getId());
        assertTrue(operationResult.getEntity() != null);

        context.put("whitelist", operationResult.getEntity());
    }

    @When("I send update whitelist entry request")
    public void updateWhitelistEntryRequest() {
        Whitelist whitelist = context.get("whitelist", Whitelist.class);

        Whitelist generatedWhitelist = getRandomWhitelist();
        generatedWhitelist.setId(whitelist.getId());

        context.put("whitelist", generatedWhitelist);
        service.update(generatedWhitelist);
    }

    @When("I send delete whitelist entry request")
    public void deleteWhitelistEntryRequest() {
        Whitelist whitelist = Entities.getWhitelists().getLatest();
        service.remove(whitelist);

        context.put("whitelist", whitelist);
    }

    @When("I send search whitelists by $criteria with $value request")
    public void searchWhitelists(String criteria, String value) {
        Whitelist whitelist = context.get("whitelist", Whitelist.class);

        switch (criteria.toLowerCase()) {
            case "identifier":
                value = value.equals("random") ? whitelist.getIdentifier() : value;
                break;
            case "description":
                value = value.equals("random") ? whitelist.getDescription() : value;
                break;
            case "type":
                value = value.equals("random") ? String.valueOf(whitelist.getType()) : value;
                break;
            case "updatedafter":
                value = value.equals("random") ? String.valueOf(whitelist.getCreatedAt().getTime() - 60000) : value;
                break;
            case "empty":
                log.debug("Search without filter..");
                break;
            default:
                throw new AssertionError("Unknown filter type");
        }

        WhitelistFilter filter = new WhitelistFilter().filterBy(criteria, value);
        OperationResult<List<Whitelist>> operationResult = service.search(filter);

        context.put("whitelistEntitiesList", operationResult.getEntity());
        context.put("whitelistFilter", filter);
    }

    @Then("Whitelists search result is correct")
    @SuppressWarnings("unchecked")
    public void whitelistsSearchResultShouldBeCorrect() {
        log.info("Checking if search targets result is correct");
        WhitelistFilter searchFilter = context.get("whitelistFilter", WhitelistFilter.class);
        List<Whitelist> searchResult = context.get("whitelistEntitiesList", List.class);

        if (searchResult.size() == 0) {
            log.warn("Search result can be incorrect. There are not records in it");
        } else {
            log.info("Search result size: " + searchResult.size());
        }

        String msg = "Whitelist: %s should not match to filter %s";
        for (Whitelist whitelist : searchResult) {
            assertTrue(
                String.format(msg, whitelist, JsonConverter.toJsonString(searchFilter)),
                searchFilter.isAppliedToEntity(whitelist)
            );
        }
    }

    @Given("I generate $count random whitelists")
    public void generateWhitelistsList(final String count) {
        int size = Integer.valueOf(count);
        List<Whitelist> whitelists = getRandomWhitelists(size);

        context.put("whitelistEntitiesList", whitelists);
    }

    @Given("I write whitelists to CSV $criteria header")
    @SuppressWarnings("unchecked")
    public void writeWhitelistsToCSV(final String criteria) {
        boolean withHeader = true;
        if (criteria.equalsIgnoreCase("without")) {
            withHeader = false;
        }

        cleanImportDir();
        List<Whitelist> whitelists = context.get("whitelistEntitiesList", List.class);
        G4File g4File = service.createCSVFile(whitelists, withHeader);

        context.put("g4file", g4File);
    }

    @When("I send import whitelists request")
    public void importWhitelists() {
        G4File csvFile = context.get("g4file", G4File.class);
        csvFile.setMediaTypeByFileExtension();
        OperationResult<ImportResult> result = service.upload(csvFile);

        context.put("importResult", result.getEntity());
    }

    @Then("Imported $countImport whitelists, modified $countModified")
    public void numberOfImportedWhitelistsIsCorrect(final String countImport, final String countModified) {
        Integer imported = Integer.valueOf(countImport);
        Integer modified = Integer.valueOf(countModified);
        ImportResult importResult = context.get("importResult", ImportResult.class);

        assertEquals("Incorrect number of imported whitelists", imported, importResult.getImportedRows());
        assertEquals("Incorrect number of modified whitelists", modified, importResult.getModifiedObjects());
    }

    @Then("I delete whitelists")
    @SuppressWarnings("unchecked")
    public void deleteImportedDesignationMappings() {
        List<Whitelist> whitelists = context.get("whitelistEntitiesList", List.class);
        List<String> errors = new ArrayList<>();
        String error = "Found %d whitelists for identifier %s";

        for (Whitelist importedWhitelist : whitelists) {
            String identifier = importedWhitelist.getIdentifier();
            WhitelistFilter filter = new WhitelistFilter().filterBy("identifier", identifier);

            List<Whitelist> foundWhitelists = service.search(filter).getEntity();
            if (foundWhitelists.size() != 1) {
                errors.add(String.format(error, foundWhitelists.size(), identifier));
            }
            for (Whitelist foundWhitelist : foundWhitelists) {
                service.remove(foundWhitelist);
            }
        }
        if (errors.size() > 0) {
            ErrorReporter.reportAndRaiseError(String.join(".\n", errors));
        }
    }

    @Given("I add $size whitelists to injections file")
    @SuppressWarnings("unchecked")
    public void addWhitelistsToInjectionsFile(String size) {
        Integer count = Integer.valueOf(size);
        List<Whitelist> whitelists = context.get("whitelistEntitiesList", List.class);

        List<Whitelist> subItems = whitelists.subList(0, count);
        service.injectWhitelists(subItems);
        context.put("whitelistEntitiesList", subItems);
    }
}
