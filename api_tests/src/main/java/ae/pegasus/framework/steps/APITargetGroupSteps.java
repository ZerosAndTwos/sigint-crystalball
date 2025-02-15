package ae.pegasus.framework.steps;

import ae.pegasus.framework.conditions.Conditions;
import ae.pegasus.framework.conditions.Verify;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.Profile;
import ae.pegasus.framework.model.ProfileAndTargetGroup;
import ae.pegasus.framework.model.TargetGroup;
import ae.pegasus.framework.model.TargetGroupSearchFilter;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.services.TargetGroupService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ae.pegasus.framework.conditions.Conditions.isTrue;
import static ae.pegasus.framework.utils.RandomGenerator.getRandomItemFromList;

@SuppressWarnings("unchecked")
public class APITargetGroupSteps extends APISteps {

    private static TargetGroupService service = new TargetGroupService();
    private static Logger log = Logger.getLogger(APITargetGroupSteps.class);

    @When("I send create target group request")
    public void sendCreateRequest() {
        TargetGroup group = getRandomTargetGroup();

        context.put("targetGroup", group);
        service.add(group);
    }

    @Then("Created target group is correct")
    public void targetGroupCorrect() {
        TargetGroup contextTargetGroup = context.get("targetGroup", TargetGroup.class);
        TargetGroup createdTargetGroup = Entities.getTargetGroups().getLatest();

        Verify.shouldBe(isTrue.element(equalsTargetGroups(contextTargetGroup, createdTargetGroup)));
    }

    /**
     * Verify two targetGroups
     *
     * @param expected checked targetGroup
     * @param actual   etalon targetGroup
     */
    static boolean equalsTargetGroups(TargetGroup expected, TargetGroup actual) {
        if (expected == null && actual == null) {
            return true;
        } else if (expected == null || actual == null) {
            log.error("At least of one of compared targetGroup is null" +
                    ", actual: " + JsonConverter.toJsonString(actual) +
                    ", expected: " + JsonConverter.toJsonString(expected));
            return false;
        }

        try {
            Verify.shouldBe(Conditions.equals(expected.getJsonType(), actual.getJsonType()));
            Verify.shouldBe(Conditions.equals(expected.getName(), actual.getName()));
            Verify.shouldBe(Conditions.equals(expected.getGroups(), actual.getGroups()));
            Verify.shouldBe(Conditions.equals(expected.getNoGroups(), actual.getNoGroups()));
            Verify.shouldBe(Conditions.equals(expected.getNoProfiles(), actual.getNoProfiles()));
            Verify.shouldBe(Conditions.equals(expected.getNoSavedSearches(), actual.getNoSavedSearches()));
            Verify.shouldBe(Conditions.equals(expected.getProfiles(), actual.getProfiles()));
            Verify.shouldBe(Conditions.equals(expected.getThreatScore(), actual.getThreatScore()));
            Verify.shouldBe(Conditions.equals(expected.isDeleted(), actual.isDeleted()));

            // Note: new targetGroups keep description in TargetGroupProperties
            Assert.assertTrue(expected.getDescription() == null ?
                    Objects.equals(expected.getProperties().getDescription(), actual.getProperties().getDescription()) :
                    Objects.equals(expected.getDescription(), actual.getProperties().getDescription()));
            Verify.shouldBe(Conditions.equals(
                    JsonConverter.toJsonString(expected.getParentChain()),
                    JsonConverter.toJsonString(actual.getParentChain())));
            Assert.assertTrue(equalsTargetGroups(expected.getParent(), actual.getParent()));
        } catch (Exception e) {
            log.error(e);
            return false;
        }

        return true;
    }

    static boolean equalsTargetGroups(List<TargetGroup> expected, List<TargetGroup> actual) {
        if (actual.isEmpty() && expected.isEmpty()) {
            return true;
        } else if (actual.isEmpty() || expected.isEmpty()) {
            log.error("At least of one of compared targetGroup lists is empty" +
                    ", actual: " + JsonConverter.toJsonString(actual) +
                    ", expected: " + JsonConverter.toJsonString(expected));
            return false;
        } else if (expected.size() != actual.size()) {
            log.error("TargetGroup collections have different size! " +
                    " ExpectedGroups size: " + expected.size() +
                    " actualGroups size: " + expected.size());
            return false;
        }

        TargetGroup[] expectedGroups = new TargetGroup[0];
        TargetGroup[] actualGroups = new TargetGroup[0];
        expectedGroups = expected.toArray(expectedGroups);
        actualGroups = actual.toArray(actualGroups);

        Arrays.sort(expectedGroups);
        Arrays.sort(actualGroups);

        for (int i = 0; i < expectedGroups.length; i++) {
            if (!equalsTargetGroups(expectedGroups[i], actualGroups[i])) {
                log.error("Target groups not equals" +
                        ", " + JsonConverter.toJsonString(expectedGroups[i]) +
                        ", " + JsonConverter.toJsonString(actualGroups[i]));
                return false;
            }
        }
        return true;
    }

    @When("I send get target group details request")
    public void getTargetGroupRequest() {
        TargetGroup createdTargetGroup = Entities.getTargetGroups().getLatest();
        OperationResult<TargetGroup> operationResult = service.view(createdTargetGroup.getId());
        context.put("targetGroup", operationResult.getEntity());
    }

    @Then("Viewed target group is correct")
    public void viewedTargetGroupIsCorrect() {
        TargetGroup createdTarget = Entities.getTargetGroups().getLatest();
        TargetGroup viewedTargetGroup = context.get("targetGroup", TargetGroup.class);
        equalsTargetGroups(viewedTargetGroup, createdTarget);
    }

    @When("I send get list of target groups request")
    public void getListOfTargetGroupsRequest() {
        TargetGroupSearchFilter filter = new TargetGroupSearchFilter();
        filter.setSortField("name");

        List<TargetGroup> targetGroups = new ArrayList<>();
        OperationResult<List<TargetGroup>> operationResult;
        do {
            operationResult = service.getTopLevelGroups(filter);
            targetGroups.addAll(operationResult.getEntity());
            filter.setPage(filter.getPage() + 1);
        } while (operationResult.getEntity().size() == filter.getPageSize());

        context.put("targetGroupList", targetGroups);
    }

    @When("I get random target group from targetGroup list")
    public void getTargetGroupFrolList() {
        List<TargetGroup> targetGroups = context.get("targetGroupList", List.class);
        context.put("targetGroup", getRandomItemFromList(targetGroups));
    }

    @Then("Created target group $criteria list")
    public void targetGroupsContainNewTargetGroup(String criteria) {
        TargetGroup targetGroup = Entities.getTargetGroups().getLatest();
        List<TargetGroup> targetGroups = context.get("targetGroupList", List.class);

        TargetGroup foundGroups = targetGroups.stream()
                .filter(group -> Objects.equals(group.getId(), targetGroup.getId()))
                .findFirst().orElse(null);

        switch (criteria.toLowerCase()) {
            case "in":
                Assert.assertNotNull(
                        "Target group not founded in response. TargetGroup:" + JsonConverter.toJsonString(targetGroup),
                        foundGroups);
                break;
            case "not in":
                Assert.assertNull(
                        "Target group founded in response. TargetGroup:" + JsonConverter.toJsonString(targetGroup),
                        foundGroups);
                break;
            default:
                throw new AssertionError("Incorrect argument passed to step");
        }
    }

    @When("I send delete target group request")
    public void deleteTargetGroupRequest() {
        TargetGroup targetGroup = Entities.getTargetGroups().getLatest();
        context.put("targetGroup", targetGroup);
        service.remove(targetGroup);
    }

    @When("I send update target group request")
    public void updateTargetGroupRequest() {
        TargetGroup createdTargetGroup = Entities.getTargetGroups().getLatest();
        TargetGroup updatedTargetGroup = updateTargetGroup(createdTargetGroup);
        OperationResult<TargetGroup> operationResult = service.update(updatedTargetGroup);
        context.put("updatedTargetGroup", operationResult.getEntity());
    }

    @Then("Target group updated correctly")
    public void targetGroupUpdatedCorrectly() {
        TargetGroup updatedTargetGroup = context.get("updatedTargetGroup", TargetGroup.class);
        TargetGroup responseTargetGroup = Entities.getTargetGroups().getLatest();
        equalsTargetGroups(responseTargetGroup, updatedTargetGroup);
    }

    @Then("existing group is listed in list only once")
    public void groupNotDuplicated() {
        List<TargetGroup> targetGroups = Entities.getTargets().getLatest().getGroups();
        List<TargetGroup> groups = context.get("targetGroupList", List.class);
        for (TargetGroup uploadedGroup : targetGroups) {
            int count = 0;
            for (TargetGroup group : groups) {
                if (uploadedGroup.getName().equals(group.getName())) {
                    Entities.getTargetGroups().updateEntity(uploadedGroup, group);
                    count += 1;
                }
            }
            Assert.assertTrue(count == 1);
        }
    }

    static TargetGroup getRandomTargetGroup() {
        return objectInitializer.randomEntity(TargetGroup.class);
    }

    private TargetGroup updateTargetGroup(TargetGroup targetGroup) {
        targetGroup.setName(RandomStringUtils.randomAlphabetic(10));
        targetGroup.setDescription(RandomStringUtils.randomAlphabetic(20));
        return targetGroup;
    }

    @Then("Target group list size more than $size")
    public void targetGroupListMoreThan(String size) {
        List<TargetGroup> groups = context.get("targetGroupList", List.class);

        Assert.assertTrue(groups.size() > Integer.valueOf(size));
    }

    @When("I send create new child target group request")
    public void creatNewChildTargetGroup() {
        TargetGroup targetGroup = Entities.getTargetGroups().getLatest();
        TargetGroup childGroup = getRandomTargetGroup();
        childGroup.setParent(targetGroup);
        TargetGroup.ParentChain parentChain = new TargetGroup.ParentChain(targetGroup.getId(), targetGroup.getName());
        childGroup.getParentChain().add(parentChain);

        context.put("parentTargetGroup", targetGroup);
        context.put("childTargetGroup", childGroup);

        service.createChildGroup(targetGroup.getId(), childGroup);
    }

    @Then("created nested target group is correct")
    public void createdNestedTargetGroupShouldBeCorrect() {
        TargetGroup createdChildGroup = Entities.getTargetGroups().getLatest();
        TargetGroup childTargetGroup = context.get("childTargetGroup", TargetGroup.class);
        TargetGroup parentTargetGroup = context.get("parentTargetGroup", TargetGroup.class);

        Verify.shouldBe(isTrue.element(equalsTargetGroups(createdChildGroup, childTargetGroup)));
        Verify.shouldBe(isTrue.element(equalsTargetGroups(createdChildGroup.getParent(), parentTargetGroup)));

        Verify.shouldBe(isTrue.element(createdChildGroup.getParentChain()
                .stream().anyMatch(parentChain -> parentChain.getId().equals(parentTargetGroup.getId()))));
    }

    @When("I send get content of parent target group")
    public void getNestedTargetGroupDetails() {
        TargetGroup nestedGroup = Entities.getTargetGroups().getLatest();

        OperationResult<ProfileAndTargetGroup[]> operationResult = service.getContent(nestedGroup.getParent().getId());

        context.put("targetGroupContent", operationResult.getEntity());
    }

    @Then("Target group content contains created nested group")
    public void targetGroupContentShouldHaveNestedGroup() {
        ProfileAndTargetGroup[] profileAndGroups = context.get("targetGroupContent", ProfileAndTargetGroup[].class);
        TargetGroup targetGroup = Entities.getTargetGroups().getLatest();

        boolean matched = Arrays.stream(profileAndGroups).anyMatch(p -> p.getId().equals(targetGroup.getId()));
        Verify.shouldBe(isTrue.element(matched));
    }

    @Then("Target group content contains created profile")
    public void targetGroupContentShouldHaveProfile() {
        ProfileAndTargetGroup[] profileAndGroups = context.get("targetGroupContent", ProfileAndTargetGroup[].class);
        Profile profile = Entities.getProfiles().getLatest();

        boolean matched = Arrays.stream(profileAndGroups).anyMatch(p -> p.getId().equals(profile.getId()));
        Verify.shouldBe(isTrue.element(matched));
    }

    @When("I search target group members by query:$name")
    public void searchProfileByName(String name) {
        TargetGroupSearchFilter filter = new TargetGroupSearchFilter();
        filter.setQuery(name).setSortField("name");

        OperationResult<List<ProfileAndTargetGroup>> operationResult = service.searchTargetGroupMembers(filter);
        context.put("operationResult", operationResult);
    }

    @When("I delete all empty groups")
    public void deleteAllEmptyGroup() {
        getListOfTargetGroupsRequest();
        List<TargetGroup> groups = context.get("targetGroupList", List.class);

        groups.stream()
                .filter(targetGroup -> (long) (targetGroup.getNoGroups() + targetGroup.getNoProfiles() + targetGroup.getNoSavedSearches()) == 0)
                .forEach(service::remove);
    }
}
