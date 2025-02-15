package steps;

import conditions.Conditions;
import conditions.Verify;
import file_generator.FileGenerator;
import http.OperationResult;
import json.JsonConverter;
import model.*;
import model.entities.Entities;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import services.TargetService;
import utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

import static conditions.Conditions.isTrue;

@SuppressWarnings("unchecked")
public class APITargetSteps extends APISteps {

    private Logger log = Logger.getLogger(APITargetGroupSteps.class);
    private TargetService service = new TargetService();

    @When("I send create target $with targets group request")
    public void sendCreateRequest(String with) {
        Target target = getRandomTarget();
        if (with.toLowerCase().equals("with")) {
            ArrayList<TargetGroup> groups = new ArrayList<>();
            groups.add(Entities.getTargetGroups().random());
            target.setGroups(groups);
        }

        OperationResult<Target> operationResult = service.add(target);
        context.put("requestTarget", operationResult.getEntity());
    }

    @Then("Created target is correct")
    public void createdTargetCorrect() {
        Target contextTarget = context.get("requestTarget", Target.class);
        Target createdTarget = Entities.getTargets().getLatest();
        Assert.assertTrue(createdTarget.getId() != null);
        isEqualsTargets(createdTarget, contextTarget);
    }

    @When("I send get target details request")
    public void getTargetDetails() {
        Target createdTarget = Entities.getTargets().getLatest();
        OperationResult<Target> operationResult = service.view(createdTarget.getId());
        context.put("viewedTarget", operationResult.getEntity());
    }

    @Then("Viewed target is correct")
    public void viewedTargetIsCorrect() {
        Target createdTarget = Entities.getTargets().getLatest();

        Target viewedTarget = context.get("viewedTarget", Target.class);

        Verify.shouldBe(isTrue.element(isEqualsTargets(viewedTarget, createdTarget)));
    }

    private boolean isEqualsTargets(Target checkedTarget, Target etalonTarget) {
        return Verify.isTrue(Conditions.equals(checkedTarget.getDescription(), etalonTarget.getDescription())) &&
                Verify.isTrue(Conditions.equals(checkedTarget.getName(), etalonTarget.getName())) &&
                Verify.isTrue(Conditions.equals(checkedTarget.getKeywords(), etalonTarget.getKeywords())) &&
                Verify.isTrue(Conditions.equals(checkedTarget.getPhones(), etalonTarget.getPhones())) &&
                Verify.isTrue(Conditions.equals(checkedTarget.getLanguages(), etalonTarget.getLanguages())) &&
                Verify.isTrue(Conditions.equals(checkedTarget.getGroups(), etalonTarget.getGroups()));
    }

    private boolean isEqualstargets(Target checkedTarget, Target etalonTarget) {
        return Verify.isTrue(Conditions.equals(checkedTarget.getName(), etalonTarget.getName())) &&
                Verify.isTrue(Conditions.equals(checkedTarget.getGroups(), etalonTarget.getGroups())) &&
                Verify.isTrue(Conditions.equals(checkedTarget.getPhones(), etalonTarget.getPhones()));
    }

    @When("I send update target request")
    public void updateTargetRequest() {
        Target createdTarget = Entities.getTargets().getLatest();
        Target updatedTarget = createdTarget;
        updatedTarget.setName(RandomStringUtils.randomAlphabetic(10));
        updatedTarget.setDescription(RandomStringUtils.randomAlphabetic(20));
        log.info("Updated target: " + JsonConverter.toJsonString(updatedTarget));
        OperationResult<Target> operationResult = service.update(updatedTarget);
        context.put("updatedTarget", operationResult.getEntity());
    }

    @Then("Target updated correctly")
    public void targetUpdatedCorrectly() {
        Target updatedTarget = context.get("updatedTarget", Target.class);
        OperationResult<Target> operationResult = service.view(updatedTarget.getId());
        Verify.shouldBe(isTrue.element(isEqualsTargets(updatedTarget, operationResult.getEntity())));
    }

    @When("I send delete target request")
    public void deleteTargetRequest() {
        Target createdTarget = Entities.getTargets().getLatest();
        service.remove(createdTarget);
        context.put("deletedTarget", createdTarget);
    }

    @Then("Target deleted correctly")
    public void targetDeletedCorrectly() {
        Target deletedTarget = context.get("deletedTarget", Target.class);
        OperationResult<Target> operationResult = service.view(deletedTarget.getId());
        Verify.shouldBe(isTrue.element(operationResult.getEntity().getName().contains("DELETED at")));
    }

    @When("I send upload targets XLS file request with $count random targets")
    public void targetGeneratedXls(String count) {
        List<Target> targets = getRandomTargets(Integer.valueOf(count));
        OperationResult<UploadResult> operationResult = service.upload(targets);
        context.put("targets", targets);
        context.put("uploadResult", operationResult.getEntity());
    }

    @When("I send upload targets request with XLS file containing $count targets with existing group request")
    public void uploadTargetsWithExistingGroup(String count) {
        List<TargetGroup> targetGroups = context.get("targetGroupList", List.class);
        List<Target> targets = getRandomTargets(Integer.valueOf(count));

        for (Target target : targets) {
            TargetGroup group = RandomGenerator.getRandomItemFromList(targetGroups);
            target.addGroup(group);
            Entities.getTargetGroups().addOrUpdateEntity(group);
        }

        OperationResult<UploadResult> operationResult = service.upload(targets);
        context.put("targets", targets);
        context.put("uploadResult", operationResult.getEntity());
    }

    @When("I send search targets by $criteria and value $value")
    public void targetSearchByCriteriaAndValue(String criteria, String value) {
        log.info("Start search targets by criteria: " + criteria + ", value: " + value);
        Target target = Entities.getTargets().getLatest();

        if (criteria.toLowerCase().equals("includedeleted")) {
            value = value.equals("random") ? String.valueOf(target.isDeleted()) : value;
        } else if (criteria.toLowerCase().equals("updatedafter")) {
            value = value.equals("random") ? String.valueOf(target.getCreatedAt().getTime() - 60000) : value;
        } else if (criteria.toLowerCase().equals("empty")) {
            log.debug("Search without filter..");
        } else {
            throw new AssertionError("Unknown filter type");
        }

        TargetFilter searchFilter = new TargetFilter().filterBy(criteria, value);
        OperationResult<List<Target>> operationResult = service.search(searchFilter);

        context.put("searchFilter", searchFilter);
        context.put("searchResult", operationResult.getEntity());
    }

    @Then("targets search result are correct")
    public void targetSearchResultAreCorrect() {
        log.info("Checking if search targets result is correct");
        TargetFilter searchFilter = context.get("searchFilter", TargetFilter.class);
        List<Target> searchResult = context.get("searchResult", List.class);

        if (searchResult.size() == 0) {
            log.warn("Search result can be incorrect. There are not records in it");
        } else {
            log.info("Search result size: " + searchResult.size());
        }
        for (Target target : searchResult) {
            Assert.assertTrue(String.format("Target:%s should not match to filter %s", target, JsonConverter.toJsonString(searchFilter)),
                    searchFilter.isAppliedToEntity(target));
        }
    }

    @Then("searched target $criteria search result list")
    public void uploadedTargetInList(String criteria) {
        log.info("Checking if Target entry " + criteria + " list");
        Target target = Entities.getTargets().getLatest();
        List<Target> list = context.get("searchResult", List.class);

        Boolean contains = false;
        for (Target entity : list) {
            contains = isEqualstargets(target, entity);
            if (contains) {
                Entities.getTargets().updateEntity(target, entity);
                break;
            }
        }

        if (criteria.toLowerCase().equals("in")) {
            Verify.shouldBe(isTrue.element(contains));
        } else if (criteria.toLowerCase().equals("not in")) {
            Verify.shouldNotBe(isTrue.element(contains));
        } else {
            throw new AssertionError("Incorrect argument passed to step");
        }
    }

    @When("I send upload updated target request")
    public void uploadUpdatedTarget() {
        List<Target> targets = context.get("targets", List.class);
        for (Target target : targets) {
            target.setName(RandomStringUtils.randomAlphabetic(10));
            target.setDescription(RandomStringUtils.randomAlphabetic(20));
        }
        service.upload(targets);
        context.put("targets", targets);
    }

    @Given("generate XLS with $count target")
    public void generateTargets(String count) {
        List<Target> targets = getRandomTargets(Integer.valueOf(count));
        G4File file = new FileGenerator(Target.class).write(targets);

        context.put("targetsXLS", file);
    }

    @When("I send get groups list of new target request")
    public void getTargetGroupsOfNewTarget() {
        Target target = Entities.getTargets().getLatest();
        List<TargetGroup> targetGroups = service.getTargetGroups(target.getId()).getEntity();
        context.put("targetGroupList", targetGroups);
    }

    @When("I send upload new $count targets with new group request")
    public void uploadTargetWithNewGroup(String count) {
        List<Target> targets = getRandomTargets(Integer.valueOf(count));

        for (Target target : targets) {
            TargetGroup targetGroup = objectInitializer.randomEntity(TargetGroup.class);
            target.addGroup(targetGroup);
            Entities.getTargetGroups().addOrUpdateEntity(targetGroup);
        }

        OperationResult<UploadResult> operationResult = service.upload(targets);
        context.put("targets", targets);
        context.put("uploadResult", operationResult.getEntity());
    }

    @Then("Upload result of $count targets is successful")
    public void checkUploadTargetsResults(String count) {
        log.info("Checking targets upload result");
        UploadResult result = context.get("uploadResult", UploadResult.class);

        int numEntries = Integer.valueOf(count);
        if ((result.getRowsAdded() + result.getRowsUpdated()) == numEntries || result.getRowsFailed() == 0) {
            List<Target> targets = context.get("targets", ArrayList.class);
            for (Target target : targets) {
                Entities.getTargets().addOrUpdateEntity(target);
            }
        } else {
            log.error("Entry upload result is not correct!");
            log.error("Errors:" + result.getErrors());
            throw new AssertionError("Entry upload result is not correct! Errors: " + result.getErrors());
        }
    }

    @Given("$count targets with phones generated and added")
    public void targetWithPhonesExist(String count) {
        int targetsCount = Integer.valueOf(count);
        List<Target> targets = getRandomTargets(targetsCount);
        OperationResult operationResult = service.upload(targets);
        Verify.shouldBe(Conditions.isTrue.element(operationResult.isSuccess()));

        GenerationMatrix generationMatrix = new GenerationMatrix(targets);
        context.put("generationMatrix", generationMatrix);
        context.put("targets", targets);
    }

    private List<Target> getRandomTargets(int count) {
        return objectInitializer.randomEntities(Target.class, count);
    }

    static Target getRandomTarget() {
        return objectInitializer.randomEntity(Target.class);
    }

    @When("I send get list of targets request")
    public void getTargetList() {
        OperationResult<List<Target>> operationResult = service.list();
        context.put("targetList", operationResult.getEntity());
    }

    @Then("Target list size more than $size")
    public void targetListShouldBeMoreThan(String size) {
        List<Target> targets = context.get("targetList", List.class);

        Assert.assertTrue(targets.size() > Integer.valueOf(size));
    }

    @When("I get random target from targets list")
    public void getRandomTargetFromTargetList() {
        List<Target> targets = context.get("targets", List.class);
        Target target = RandomGenerator.getRandomItemFromList(targets);

        Entities.getTargets().addOrUpdateEntity(target);
        context.put("target", target);
    }
}
