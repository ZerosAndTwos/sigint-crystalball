package ae.pegasus.framework.steps;

import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.Role;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.services.RoleService;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.List;
import java.util.Objects;


public class APIRoleSteps extends APISteps {

    private Logger log = Logger.getLogger(APIRoleSteps.class);
    private RoleService service = new RoleService();

    @When("I send create a new role request")
    public void createRoleRequest() {
        Role role = getRandomRole();
        context.put("role", role);
        service.add(role);
    }

    @Then("Role is correct")
    public void createdRoleIsCorrect() {
        Role createdRole = Entities.getRoles().getLatest();
        Role requestRole = context.get("role", Role.class);

        Assert.assertEquals(createdRole.getName().toUpperCase(), requestRole.getName().toUpperCase());
        Assert.assertEquals(createdRole.getDisplayName(), requestRole.getDisplayName());
        Assert.assertEquals(createdRole.getPermissions(), requestRole.getPermissions());
    }

    static Role getRandomRole() {
        return objectInitializer.randomEntity(Role.class);
    }

    @When("I send delete Role request")
    public void deleteLatestRole() {
        Role role = Entities.getRoles().getLatest();
        service.remove(role);
    }

    @When("I send get list of Roles request")
    public void viewSource() {
        OperationResult<List<Role>> operationResult = service.list();
        context.put("roles", operationResult.getEntity());
    }

    @Then("Role is deleted")
    public void roleIsDeleted() {
        List<Role> roleList = context.get("roles", List.class);
        Role role = context.get("role", Role.class);

        for (Role entity : roleList) {
            if (Objects.equals(role.getName(), entity.getName())) {
                throw new AssertionError("Role wasn't removed, name: " + role.getName());
            }
        }
    }

    @When("I send update role request")
    public void updateRole() {
        Role role = Entities.getRoles().getLatest();
        Role updatedRole = getRandomRole();
        updatedRole.setName(role.getName());
        context.put("role", updatedRole);
        service.update(updatedRole);
    }
}
