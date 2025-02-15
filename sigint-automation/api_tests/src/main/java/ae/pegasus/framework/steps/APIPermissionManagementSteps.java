package ae.pegasus.framework.steps;

import ae.pegasus.framework.conditions.Conditions;
import ae.pegasus.framework.conditions.Verify;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.Permission;
import ae.pegasus.framework.model.Responsibility;
import ae.pegasus.framework.model.Title;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.services.PermissionService;
import ae.pegasus.framework.services.ResponsibilityService;
import ae.pegasus.framework.services.TitleService;
import ae.pegasus.framework.utils.RandomGenerator;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class APIPermissionManagementSteps extends APISteps {

    private static PermissionService permissionService = new PermissionService();
    private static ResponsibilityService responsibilityService = new ResponsibilityService();
    private static TitleService titleService = new TitleService();

    @When("I send get list of permission request")
    public void getListOfPermission() {
        OperationResult<List<Permission>> operationResult = permissionService.list();
        context.put("permissionList", operationResult.getEntity());
    }

    @Then("Permission list size more than $size")
    public void permissionListSizeShouldBeMoreThan(String size) {
        List<Permission> permissions = context.get("permissionList", List.class);
        assertTrue(permissions.size() > Integer.valueOf(size));
    }

    @When("I send get list of responsibilities request")
    public void getListOfResponsibilities() {
        OperationResult<List<Responsibility>> operationResult = responsibilityService.list();
        context.put("responsibilityList", operationResult.getEntity());
    }

    @Then("Responsibilities list size more than $size")
    public void responsibilityListSizeShouldBeMoreThan(String size) {
        List<Responsibility> responsibilities = context.get("responsibilityList", List.class);
        assertTrue(responsibilities.size() > Integer.valueOf(size));
    }

    @When("I send get list of titles request")
    public void getListOfTitles() {
        OperationResult<List<Title>> operationResult = titleService.list();
        context.put("titleList", operationResult.getEntity());
    }

    @Then("Titles list size more than $size")
    public void titleListSizeShouldBeMoreThan(String size) {
        List<Title> titles = context.get("titleList", List.class);
        assertTrue(titles.size() > Integer.valueOf(size));
    }

    @When("I send create a new responsibility request")
    public void createNewResponsibility() {
        Responsibility responsibility = createRandomResponsibility();
        context.put("responsibility", responsibility);

        responsibilityService.add(responsibility);
    }

    private static Responsibility createRandomResponsibility() {
        Responsibility responsibility = objectInitializer.randomEntity(Responsibility.class);

        int maxPermissionsNumber = 10;
        List<String> permissionNames = getRandomListOfPermissionNames(maxPermissionsNumber);
        responsibility.setPermissions(permissionNames);

        return responsibility;
    }

    private static List<String> getRandomListOfPermissionNames(Integer maxNumber) {
        List<Permission> permissions = context.get("permissionList", List.class);
        if (permissions == null || permissions.isEmpty()) {
            permissions = permissionService.list().getEntity();
        }

        // get random collection of permission
        int minNumber = 1;
        List<Permission> randomPermissionList = RandomGenerator
                .getRandomItemsFromList(permissions, RandomGenerator.generateRandomInteger(minNumber, maxNumber));

        List<String> permissionNames = new ArrayList<>();
        randomPermissionList = randomPermissionList.stream()
                .peek((permission) -> permissionNames.add(permission.getName()))
                .collect(Collectors.toList());

        return permissionNames;
    }

    @Then("Responsibility is correct")
    public void createdResponsibilityShoudBeCorrect() {
        Responsibility responsibility = context.get("responsibility", Responsibility.class);
        Responsibility createdResponsibility = Entities.getResponsibilities().getLatest();

        assertEquals(createdResponsibility.getDisplayName(), responsibility.getDisplayName());
        assertFalse(createdResponsibility.getIsDeleted());
        assertEquals(createdResponsibility.getDescription(), responsibility.getDescription());
        Verify.shouldBe(Conditions.equals(createdResponsibility.getPermissions(), responsibility.getPermissions()));
    }

    @When("I send delete responsibility request")
    public void deleteResponsibility() {
        Responsibility responsibility = Entities.getResponsibilities().getLatest();
        context.put("responsibility", responsibility);

        responsibilityService.remove(responsibility);
    }

    @Then("Responsibility is $criteria the list")
    public void checkResponsibilityInList(String criteria) {
        List<Responsibility> responsibilities = context.get("responsibilityList", List.class);
        Responsibility responsibility = context.get("responsibility", Responsibility.class);

        boolean matched = responsibilities.stream()
                .anyMatch((r -> r.getDisplayName().equals(responsibility.getDisplayName())));

        if (criteria.equals("in")) {
            assertTrue(matched);
        } else {
            assertFalse(matched);
        }
    }

    @When("I send update responsibility request")
    public void updateResponsibility() {
        Responsibility responsibility = Entities.getResponsibilities().getLatest();

        Responsibility updatedResponsibility = createRandomResponsibility();
        updatedResponsibility.setId(responsibility.getId());
        context.put("responsibility", updatedResponsibility);

        responsibilityService.update(updatedResponsibility);
    }

    @When("I send get responsibility details request")
    public void getResponsobilityDetails() {
        Responsibility responsibility = Entities.getResponsibilities().getLatest();
        context.put("responsibility", responsibility);

        responsibilityService.view(responsibility.getId());
    }

    @When("I send create a new title request")
    public void createTitle() {
        Title title = createRandomTitle();
        context.put("title", title);

        titleService.add(title);
    }

    private static Title createRandomTitle() {
        int maxResponsibilitiesNumber = 5;

        Title title = objectInitializer.randomEntity(Title.class);
        title.setResponsibilities(getRandomListOfResponsibilitiesIDs(maxResponsibilitiesNumber));

        return title;
    }

    private static List<String> getRandomListOfResponsibilitiesIDs(Integer maxNumber) {
        List<Responsibility> responsibilities = context.get("responsibilityList", List.class);
        if (responsibilities == null || responsibilities.isEmpty()) {
            responsibilities = responsibilityService.list().getEntity();
        }

        // get random collection of responsibilities
        List<Responsibility> randomItemsFromList = RandomGenerator.getRandomItemsFromList(responsibilities, maxNumber);

        List<String> responsibilitiesIDs = new ArrayList<>();
        randomItemsFromList = randomItemsFromList.stream()
                .peek((responsibility) -> responsibilitiesIDs.add(responsibility.getId()))
                .collect(Collectors.toList());

        return responsibilitiesIDs;
    }

    @Then("Title is correct")
    public void titleShouldBeCorrect() {
        Title title = context.get("title", Title.class);
        Title createdTitle = Entities.getTitles().getLatest();

        assertEquals(title.getDescription(), createdTitle.getDescription());
        assertEquals(title.getDisplayName(), createdTitle.getDisplayName());
        assertFalse(createdTitle.getIsDeleted());
        Verify.shouldBe(Conditions.equals(title.getResponsibilities(), createdTitle.getResponsibilities()));
    }

    @When("I send delete title request")
    public void deleteTitle() {
        Title title = Entities.getTitles().getLatest();
        context.put("title", title);

        titleService.remove(title);
    }

    @When("I send get title details request")
    public void getTitleDetails() {
        Title title = Entities.getTitles().getLatest();
        context.put("title", title);

        titleService.view(title.getId());
    }

    @Then("Title is $criteria the list")
    public void checkTitleInList(String criteria) {
        List<Title> titles = context.get("titleList", List.class);
        Title title = context.get("title", Title.class);

        boolean matched = titles.stream()
                .anyMatch((r -> r.getId().equals(title.getId())));

        if (criteria.equals("in")) {
            assertTrue(matched);
        } else {
            assertFalse(matched);
        }
    }

    @When("I send update title request")
    public void updateTitle() {
        Title title = Entities.getTitles().getLatest();

        Title updatedTitle = createRandomTitle();
        updatedTitle.setId(title.getId());
        context.put("title", updatedTitle);

        titleService.update(updatedTitle);
    }
}
