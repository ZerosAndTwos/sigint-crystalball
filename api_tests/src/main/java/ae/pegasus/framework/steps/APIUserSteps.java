package ae.pegasus.framework.steps;

import ae.pegasus.framework.data_for_entity.data_providers.user.UserPasswordProvider;
import ae.pegasus.framework.error_reporter.ErrorReporter;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.TeamTitle;
import ae.pegasus.framework.model.User;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.services.UserService;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@SuppressWarnings("unchecked")
public class APIUserSteps extends APISteps {

    private Logger log = Logger.getLogger(APIUserSteps.class);
    private UserService service = new UserService();

    private static final int PASSWORD_LENGTH = 10;
    private static final int LOGIN_DELAY = 5000;

    private String generatePassword() {
        return new UserPasswordProvider().generate(PASSWORD_LENGTH);
    }

    @Then("Created user is correct")
    public void createdUserIsCorrect() {
        User createdUser = Entities.getUsers().getLatest();
        User requestUser = context.get("user", User.class);

        log.debug("requested: " + JsonConverter.toJsonString(requestUser));
        log.debug("created: " + JsonConverter.toJsonString(createdUser));

        Assert.assertEquals(createdUser.getName().toLowerCase(), requestUser.getName().toLowerCase());
        Assert.assertEquals(createdUser.getFullName(), requestUser.getFullName());
        Assert.assertEquals(createdUser.getLanguages(), requestUser.getLanguages());
        Assert.assertEquals(createdUser.getStaffId(), requestUser.getStaffId());
    }

    static User getRandomUser() {
        return User.newBuilder().newUser().build();
    }

    @When("I send delete user request")
    public void deleteUser() {
        User createdUser = Entities.getUsers().getLatest();
        service.remove(createdUser);
    }

    @When("I send get list of users")
    public void getListOfUsers() {
        OperationResult<List<User>> operationResult = service.search(null);
        context.put("userList", operationResult.getEntity());
    }

    @Then("Users list size more than $size")
    public void userListMoreThan(String size) {
        List<User> userList = context.get("userList", List.class);

        Assert.assertFalse("Users list is empty", userList.isEmpty());
        Assert.assertTrue(userList.size() > Integer.valueOf(size));
    }

    @When("I send update user request")
    public void updateUser() {
        User user = Entities.getUsers().getLatest();
        User updatedUser = getRandomUser();
        updatedUser.setId(user.getId());
        updatedUser.setNewPassword(updatedUser.getPassword());
        context.put("user", updatedUser);
        service.update(updatedUser);
    }

    @When("I send get current user request")
    public void getCurrentUserRequest() {
        service.me();
    }

    @When("I send create a new user")
    public void createNewUser() {
        User user = getRandomUser();

        TeamTitle teamTitle = appContext.getLoggedUser().getUser().getDefaultPermission()
                .getTeamTitles().stream()
                .findAny().orElse(null);
        assertNotNull(teamTitle);

        user = service.addOrgUnitWithTitles(user, teamTitle.getOrgUnitId(), teamTitle.getTitles());

        context.put("user", user);
        service.add(user);
    }

    @When("I change temporary password")
    public void changePasswordFirstTime() {
        User user = Entities.getUsers().getLatest();
        user.setNewPassword(generatePassword());

        service.changeTempPassword(user);
    }

    @When("I set wrong user password")
    public void setWrongUserPassword() {
        User user = Entities.getUsers().getLatest();

        String passwordToReplace = generatePassword();
        log.info(String.format("Setting wrong password %s for user %s", passwordToReplace, user.getName()));
        user.setPassword(passwordToReplace);
        Entities.getUsers().addOrUpdateEntity(user);
    }

    @When("I change user password to $newPassword")
    public void changeUserPassword(final String newPassword) {
        User user = Entities.getUsers().getLatest();

        String passwordToReplace = newPassword;
        if ("random".equals(newPassword.toLowerCase())) {
            passwordToReplace = generatePassword();
        }
        if ("username".equals(newPassword.toLowerCase())) {
            passwordToReplace = user.getName();
        }

        user.setNewPassword(passwordToReplace);
        context.put("user", user);
        service.changePassword(user);
    }

    @Then("User last logon datetime is correct")
    public void checkLogonDatetime() {
        User user = Entities.getUsers().getLatest();
        Date userLogonDatetime = user.getLastLoginDate();

        if (userLogonDatetime == null) {
            ErrorReporter.reportAndRaiseError("Logon datetime is not initialized");
        } else {
            long deltaInMillis = System.currentTimeMillis() - userLogonDatetime.getTime();
            if (deltaInMillis > LOGIN_DELAY) {
                log.error("Delta in millis: " + deltaInMillis);
                ErrorReporter.reportAndRaiseError("Incorrect logon datetime: " + userLogonDatetime);
            }
        }
    }
}
