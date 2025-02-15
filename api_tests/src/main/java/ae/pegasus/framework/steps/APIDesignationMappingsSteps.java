package ae.pegasus.framework.steps;

import ae.pegasus.framework.error_reporter.ErrorReporter;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.services.DesignationMappingService;
import ae.pegasus.framework.services.DesignationService;
import ae.pegasus.framework.utils.RandomGenerator;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ae.pegasus.framework.ingestion.IngestionService.cleanImportDir;
import static ae.pegasus.framework.utils.StringUtils.stringEquals;
import static ae.pegasus.framework.utils.StringUtils.stripQuotes;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.*;

public class APIDesignationMappingsSteps extends APISteps {

  private static DesignationMappingService service = new DesignationMappingService();
  private static DesignationService designationService = new DesignationService();
  private static List<Designation> designationList;

  private static DesignationMapping getRandomDesignationMapping() {
    DesignationMapping designationMapping = objectInitializer.randomEntity(DesignationMapping.class);

    // init designations list
    if (designationList == null) {
      OperationResult<List<Designation>> operationResult = designationService.list();
      designationList = operationResult.getEntity();
    }
    // Assign random designation to the designation-mapping
    Designation randomDesignation = RandomGenerator.getRandomItemFromList((designationList));
    List<String> designations = Stream.of(randomDesignation.getName()).collect(Collectors.toList());
    designationMapping.setDesignations(designations);

    return designationMapping;
  }

  static List<DesignationMapping> getRandomDesignationMappings(int count) {
    return objectInitializer.randomEntities(DesignationMapping.class, count);
  }

  @When("I send delete designation-mapping request")
  public void deleteDesignationMapping() {
    DesignationMapping designationMapping = Entities.getDesignationMappings().getLatest();
    context.put("designationMapping", designationMapping);
    service.remove(designationMapping);
  }

  @When("I send create new random designation-mapping request")
  public void createRandomDesignationMapping() {
    DesignationMapping designationMapping = getRandomDesignationMapping();

    context.put("designationMapping", designationMapping);
    service.add(designationMapping);
  }

  @When("I send get list of designation-mappings request")
  public void getListOfDesignationMappings() {
    OperationResult<List<DesignationMapping>> operationResult = service.list();
    context.put("designationMappingList", operationResult.getEntity());
  }

  @Then("Designation-mappings list size is more than $size")
  @SuppressWarnings("unchecked")
  public void DesignationMappingsListSizeShouldbeMoreThan(String size) {
    int minSize = Integer.valueOf(size);
    List<DesignationMapping> designationMappings = context.get("designationMappingList", List.class);
    log.info("Designation-mappings list size: " + designationMappings.size());

    assertTrue("List size mismatch. Should be > " + minSize, designationMappings.size() > minSize);
  }

  @Then("Designation-mapping is $criteria list")
  @SuppressWarnings("unchecked")
  public void DesignationMappingShouldBeInList(String criteria) {
    DesignationMapping designationMapping = context.get("designationMapping", DesignationMapping.class);
    List<DesignationMapping> designationMappings = context.get("designationMappingList", List.class);

    boolean contained = false;
    for (DesignationMapping designationMappingFromList : designationMappings) {
      if (designationMappingFromList.getIdentifier().equals(designationMapping.getIdentifier())) {
        contained = true;
        break;
      }
    }

    if (criteria.equals("in")) {
      assertTrue("Designation-mapping " + designationMapping.getId() + " should be in list", contained);
    } else {
      assertFalse("Designation-mapping " + designationMapping.getId() + " should not be listed", contained);
    }
  }

  @When("I get random designation-mapping from list")
  @SuppressWarnings("unchecked")
  public void getRandomDesignationMappingFromList() {
    List<DesignationMapping> designationMappings = context.get("designationMappingList", List.class);
    DesignationMapping randomDesignationMapping = RandomGenerator.getRandomItemFromList((designationMappings));

    context.put("designationMapping", randomDesignationMapping);
  }

  @When("I send view designation-mapping request")
  public void viewDesignationMapping() {
    DesignationMapping designationMapping = context.get("designationMapping", DesignationMapping.class);

    OperationResult<DesignationMapping> operationResult = service.view(designationMapping.getId());
    context.put("designationMapping", operationResult.getEntity());
  }

  @Then("Designation-mapping is correct")
  public void designationMappingShouldBeCorrect() {
    DesignationMapping latestDesignationMapping = Entities.getDesignationMappings().getLatest();
    DesignationMapping designationMapping = context.get("designationMapping", DesignationMapping.class);

    assertTrue("Got null object from collection ",latestDesignationMapping != null);
    assertTrue("Got null object from context ",designationMapping != null);

    assertThat(designationMapping.getIdentifier(), is(equalToIgnoringCase(latestDesignationMapping.getIdentifier())));
    assertEquals("type mismatch", designationMapping.getType(), latestDesignationMapping.getType());
    assertEquals("designations mismatch", designationMapping.getDesignations(), latestDesignationMapping.getDesignations());
    assertEquals("spam flag mismatch", designationMapping.isSpam(), latestDesignationMapping.isSpam());

    context.put("designationMapping", latestDesignationMapping);
  }

  @When("I send update designation-mapping request")
  public void updateDesignationMapping() {
    DesignationMapping designationMapping = context.get("designationMapping", DesignationMapping.class);

    DesignationMapping generatedDesignationMapping = getRandomDesignationMapping();
    generatedDesignationMapping.setId(designationMapping.getId());

    context.put("designationMapping", generatedDesignationMapping);
    service.update(generatedDesignationMapping);
  }

  private void searchDMByParameters(String criteria, String value, String spam) {
    DesignationMapping designationMapping = context.get("designationMapping", DesignationMapping.class);

    boolean getValueFromEntity = stringEquals("random", value);

    switch (criteria.trim().toLowerCase()) {
      case "identifier":
        value = getValueFromEntity ? designationMapping.getIdentifier() : value;
        break;
      case "type":
        value = getValueFromEntity ? String.valueOf(designationMapping.getType()) : value;
        break;
      case "designation":
        value = getValueFromEntity ? String.valueOf(designationMapping.getDesignations().get(0)) : value;
        break;
      case "spam":
        value = getValueFromEntity ? String.valueOf(designationMapping.isSpam()) : value;
        break;
      case "updatedafter":
        value = getValueFromEntity ? String.valueOf(designationMapping.getCreatedAt().getTime() - 60000) : value;
        break;
      case "empty":
        log.debug("Search without filter..");
        break;
    }

    DesignationMappingFilter filter = new DesignationMappingFilter();
    if (spam != null) {
      filter.setSpam(Boolean.parseBoolean(spam));
    }
    filter = filter.filterBy(criteria, value);
    OperationResult<List<DesignationMapping>> operationResult = service.search(filter);

    context.put("designationMappingList", operationResult.getEntity());
    context.put("designationMappingFilter", filter);
  }

  @When("I send search designation-mappings by $criteria with $value request, with spam flag: $spam")
  public void searchDesignationMappingsWithSpam(String criteria, String value, String spam) {
    searchDMByParameters(criteria, value, spam);
  }

  @When("I send search designation-mappings by $criteria with $value request")
  public void searchDesignationMappings(String criteria, String value) {
    searchDMByParameters(criteria, value, null);
  }

  @Then("Designation-mappings search result is correct")
  @SuppressWarnings("unchecked")
  public void designationMappingsSearchResultShouldBeCorrect() {
    log.info("Checking if search designation-mappings result is correct");
    DesignationMappingFilter searchFilter = context.get("designationMappingFilter", DesignationMappingFilter.class);
    List<DesignationMapping> searchResult = context.get("designationMappingList", List.class);

    if (searchResult.size() == 0) {
      log.warn("Search result can be incorrect. Found 0 records");
    } else {
      log.info("Search result size: " + searchResult.size());
    }

    String msg = "Designation-mapping: %s should not match filter %s";
    for (DesignationMapping designationMapping : searchResult) {
      assertTrue(
          String.format(msg, designationMapping, JsonConverter.toJsonString(searchFilter)),
          searchFilter.isAppliedToEntity(designationMapping));
    }
  }

  @Given("I generate $count random designation-mappings")
  public void generateDesignationMappingsList(final String count) {
    int size = Integer.valueOf(count);
    List<DesignationMapping> designationMappings = getRandomDesignationMappings(size);

    for (DesignationMapping designationMapping : designationMappings) {
      designationMapping.setDesignations(new ArrayList<>(Collections.singletonList("Import Designation")));
    }

    context.put("designationMappingList", designationMappings);
  }

  @Given("I add the same designation-mapping with $designation designation to the list")
  @SuppressWarnings("unchecked")
  public void addDesignationMappingWithDesignations(final String designation) {
    List<DesignationMapping> designationMappings = context.get("designationMappingList", List.class);

    DesignationMapping newDesignation = designationMappings.get(0).copy();
    newDesignation.setDesignations(new ArrayList<>(Collections.singletonList(stripQuotes(designation))));
    designationMappings.add(newDesignation);

    context.put("designationMappingList", designationMappings);
  }

  @Given("I add $param designation-mapping to the list")
  @SuppressWarnings("unchecked")
  public void addBrokenDesignationMapping(final String param) {
    List<DesignationMapping> designationMappings = context.get("designationMappingList", List.class);

    DesignationMapping newDesignation = getRandomDesignationMapping();
    Designation randomDesignation = APIDesignationsSteps.getRandomDesignation();
    context.put("designation", randomDesignation);

    if (param.equalsIgnoreCase("broken")) {
      randomDesignation.setName("");
    }

    newDesignation.setDesignations(new ArrayList<>(Collections.singletonList(randomDesignation.getName())));
    designationMappings.add(newDesignation);

    context.put("designationMappingList", designationMappings);
  }

  @Given("I write designation-mappings to CSV $criteria header")
  @SuppressWarnings("unchecked")
  public void writeDesignationMappingsToCSV(final String criteria) {
    boolean withHeader = true;
    if (criteria.equalsIgnoreCase("without")) {
      withHeader = false;
    }

    cleanImportDir();
    List<DesignationMapping> designationMappings = context.get("designationMappingList", List.class);
    G4File g4File = service.createCSVFile(designationMappings, withHeader);

    context.put("g4file", g4File);
  }

  @When("I send import designation-mappings request")
  public void importDesignationMappings() {
    G4File csvFile = context.get("g4file", G4File.class);
    csvFile.setMediaTypeByFileExtension();
    OperationResult<ImportResult> result = service.upload(csvFile);

    context.put("importResult", result.getEntity());
  }

  @Then("Imported $countImported designation-mappings, modified $countModified")
  public void numberOfImportedDMIsCorrect(final String countImported, final String countModified) {
    Integer imported = Integer.valueOf(countImported);
    Integer modified = Integer.valueOf(countModified);
    ImportResult importResult = context.get("importResult", ImportResult.class);

    assertEquals("Incorrect number of imported designation-mappings", imported, importResult.getImportedRows());
    assertEquals("Incorrect number of modified designation-mappings", modified, importResult.getModifiedObjects());
  }

  @Then("I delete designation-mappings")
  @SuppressWarnings("unchecked")
  public void deleteImportedWhitelists() {
    List<DesignationMapping> designationMappings = context.get("designationMappingList", List.class);
    List<String> errors = new ArrayList<>();
    String error = "Found %d designation-mappings for identifier %s";

    for (DesignationMapping importedDesignationMapping : designationMappings) {
      String identifier = importedDesignationMapping.getIdentifier();
      DesignationMappingFilter filter = new DesignationMappingFilter().filterBy("identifier", identifier);

      List<DesignationMapping> foundDesignationMappings = service.search(filter).getEntity();
      if (foundDesignationMappings.size() > 1) {
        errors.add(String.format(error, foundDesignationMappings.size(), identifier));
      }
      for (DesignationMapping foundDesignationMapping : foundDesignationMappings) {
        service.remove(foundDesignationMapping);
      }
    }
    if (errors.size() > 0) {
      ErrorReporter.reportAndRaiseError(String.join(".\n", errors));
    }
  }

  @Given("I add $size designation-mappings to injections file")
  @SuppressWarnings("unchecked")
  public void addWhitelistsToInjectionsFile(String size) {
    Integer count = Integer.valueOf(size);
    List<DesignationMapping> designationMappings = context.get("designationMappingList", List.class);

    List<DesignationMapping> subItems = designationMappings.subList(0, count);
    service.injectDesignationMappings(subItems);
    context.put("designationMappingList", subItems);
  }
}