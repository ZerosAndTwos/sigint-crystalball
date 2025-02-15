package ae.pegasus.framework.steps;

import ae.pegasus.framework.conditions.Conditions;
import ae.pegasus.framework.conditions.Verify;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.Group;
import ae.pegasus.framework.model.RequestResult;
import ae.pegasus.framework.model.Role;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.services.GroupService;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class APIUserGroupSteps extends APISteps {

    private static final Logger log = Logger.getLogger(APIUserGroupSteps.class);
    private static final GroupService service = new GroupService();

    @When("I send create a new group without any roles")
    public void createGroupRequest() {
        Group group = getRandomUserGroup();
        context.put("group", group);
        service.add(group);
    }

    @Then("CreatedOrUpdated group is correct")
    public void createdGroupIsCorrect() {
        Group createdGroup = Entities.getGroups().getLatest();
        Group requestGroup = context.get("group", Group.class);

        Verify.shouldBe(Conditions.equals(createdGroup, requestGroup));
    }

    @When("I send add a created role to group request")
    public void addRoleToGroupRequest() {
        List<String> roles = new ArrayList<>();
        roles.add(Entities.getRoles().getLatest().getName());
        Group group = Entities.getGroups().getLatest().setRoles(roles);
        context.put("group", group);
        service.update(group);

    }

    @Then("Updated group is correct")
    public void updatedGroupIsCorrect() {
        Group createdGroup = Entities.getGroups().getLatest();
        Group updatedGroup = context.get("group", Group.class);

        Verify.shouldBe(Conditions.equals(updatedGroup, createdGroup));
    }

    static Group getRandomUserGroup() {
        return objectInitializer.randomEntity(Group.class);
    }

    @When("I send delete group request")
    public void deleteGroup() {
        Group group = Entities.getGroups().getLatest();
        OperationResult<RequestResult> operationResult = service.remove(group);
        context.put("requestResult", operationResult.getEntity());
    }

    @When("I send get list of users group")
    public void getListOfGroups() {
        OperationResult<List<Group>> operationResult = service.list();
        Group[] groups = (Group[]) operationResult.getEntity().toArray();
        context.put("groupList", operationResult.getEntity());
    }

    @Then("Users group list size more than $size")
    public void groupListIsNotEmpty(String size) {
        List<Group> groupList = context.get("groupList", List.class);

        Assert.assertFalse(groupList.size() > Integer.valueOf(size));
    }

    @When("delete all groups without roles and users")
    public void deleteAllOldGroups() {
        List<Group> groupList = context.get("groupList", List.class);
        for (Group group : groupList) {
            if (group.getRoles().isEmpty() && group.getUsers() == null) {
                OperationResult<RequestResult> operationResult = service.remove(group);
                Assert.assertEquals("success", operationResult.getEntity().getMessage());
            }
        }
    }

    @Then("delete from groups phantom roles")
    public void deleteFromGroupsPhantomRoles() {

        List<Group> groupList = context.get("groupList", List.class);
        List<Role> roleList = context.get("roles", List.class);

        for (Group group : groupList) {

            List<String> groupRoles = group.getRoles();
            boolean updated = false;

            Iterator<String> iterator = groupRoles.iterator();
            while (iterator.hasNext()) {
                boolean exist = false;
                String groupRoleName = iterator.next();

                for (Role role : roleList) {
                    // if role exist
                    if (Objects.equals(groupRoleName, role.getName())) {
                        exist = true;
                        break;
                    }
                }

                if (!exist) {
                    iterator.remove();
                    updated = true;
                }
            }
            if (updated) {
                OperationResult<Group> operationResult = service.update(group);

                Assert.assertTrue(operationResult.isSuccess());
            }
        }
    }


    @When("I send update user group request")
    public void updateUserGroup() {
        Group group = Entities.getGroups().getLatest();
        Group updatedGroup = getRandomUserGroup();
        updatedGroup.setId(group.getId());

        OperationResult<Group> operationResult = service.update(updatedGroup);

        context.put("group", operationResult.getEntity());
    }
}
