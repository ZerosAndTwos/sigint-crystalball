package ae.pegasus.framework.steps;


import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.Designation;
import ae.pegasus.framework.model.DesignationFilter;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.services.DesignationService;
import ae.pegasus.framework.utils.RandomGenerator;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.List;

import static org.junit.Assert.*;

public class APIDesignationsSteps extends APISteps {

  private static DesignationService service = new DesignationService();

  static Designation getRandomDesignation() {
    return objectInitializer.randomEntity(Designation.class);
  }

  @When("I send delete designation request")
  public void deleteDesignation() {
    Designation designation = Entities.getDesignations().getLatest();
    context.put("designation", designation);
    service.remove(designation);
  }

  @When("I send create new random designation request")
  public void createRandomDesignation() {
    Designation designation = getRandomDesignation();
    context.put("designation", designation);
    service.add(designation);
  }

  @When("I send get list of designations request")
  public void getListOfDesignations() {
    OperationResult<List<Designation>> operationResult = service.list();
    context.put("designationList", operationResult.getEntity());
  }

  @Then("Designations list size is more than $size")
  public void DesignationsListSizeShouldbeMoreThan(String size) {
    int lessSize = Integer.valueOf(size);
    List<Designation> designations = context.get("designationList", List.class);
    log.info("Designations list size: " + designations.size());

    assertTrue(designations.size() > lessSize);
  }

  @Then("Designation is $criteria list")
  @SuppressWarnings("unchecked")
  public void DesignationShouldBeInList(String criteria) {
    Designation designation = context.get("designation", Designation.class);
    List<Designation> designations = context.get("designationList", List.class);

    boolean contained = false;
    for (Designation designationFromList : designations) {
      if (designationFromList.getName().equals(designation.getName())) {
        contained = true;
        break;
      }
    }

    if (criteria.equals("in")) {
      assertTrue(contained);
    } else {
      assertFalse(contained);
    }
  }

  @When("I get random designation from list")
  @SuppressWarnings("unchecked")
  public void getRandomDesignationFromList() {
    List<Designation> designations = context.get("designationList", List.class);
    Designation randomDesignation = RandomGenerator.getRandomItemFromList((designations));

    context.put("designation", randomDesignation);
  }

  @When("I send view designation request")
  public void viewDesignation() {
    Designation designation = context.get("designation", Designation.class);

    OperationResult<Designation> operationResult = service.view(designation.getId());
    context.put("designation", operationResult.getEntity());
  }

  @Then("Designation is correct")
  public void designationShouldBeCorrect() {
    Designation latestDesignation = Entities.getDesignations().getLatest();
    Designation designation = context.get("designation", Designation.class);

    assertTrue("Got null object from collection ",latestDesignation != null);
    assertTrue("Got null object from context ",designation != null);

    assertEquals(designation.getName(), latestDesignation.getName());
    context.put("designation", latestDesignation);
  }

  @When("I send update designation request")
  public void updateDesignation() {
    Designation designation = context.get("designation", Designation.class);

    Designation generatedDesignation = getRandomDesignation();
    generatedDesignation.setId(designation.getId());

    context.put("designation", generatedDesignation);
    service.update(generatedDesignation);
  }

  @When("I send search designations by $criteria with $value request")
  public void searchDesignations(String criteria, String value) {
    Designation designation = context.get("designation", Designation.class);

    switch (criteria.toLowerCase()) {
      case "name":
        value = value.equals("random") ? designation.getName() : value;
        break;
      case "updatedafter":
        value = value.equals("random") ? String.valueOf(designation.getCreatedAt().getTime() - 60000) : value;
        break;
      case "empty":
        log.debug("Search without filter..");
        break;
      default:
        throw new AssertionError("Unknown filter type");
    }

    DesignationFilter filter = new DesignationFilter().filterBy(criteria, value);
    OperationResult<List<Designation>> operationResult = service.search(filter);

    context.put("designationList", operationResult.getEntity());
    context.put("designationFilter", filter);
  }

  @Then("Designations search result is correct")
  public void designationsSearchResultShouldBeCorrect() {
    log.info("Checking if designations search result is correct");
    DesignationFilter searchFilter = context.get("designationFilter", DesignationFilter.class);
    List<Designation> searchResult = context.get("designationList", List.class);

    if (searchResult.size() == 0) {
      log.warn("Search result can be incorrect. There are not records in it");
    } else {
      log.info("Search result size: " + searchResult.size());
    }

    String msg = "Designation: %s should not match filter %s";
    for (Designation designation : searchResult) {
      assertTrue(
          String.format(msg, designation, JsonConverter.toJsonString(searchFilter)),
          searchFilter.isAppliedToEntity(designation));
    }
  }
}