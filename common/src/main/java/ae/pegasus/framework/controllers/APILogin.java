package ae.pegasus.framework.controllers;

import ae.pegasus.framework.app_context.AppContext;
import ae.pegasus.framework.http.G4HttpClient;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.OperationsResults;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.services.OrganizationService;
import ae.pegasus.framework.services.ResponsibilityService;
import ae.pegasus.framework.services.TitleService;
import ae.pegasus.framework.services.UserService;
import ae.pegasus.framework.utils.StringUtils;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static ae.pegasus.framework.json.JsonConverter.jsonToObjectsList;
import static ae.pegasus.framework.json.JsonConverter.toJsonString;
import static org.junit.Assert.assertTrue;

public class APILogin {

    private static AuthService signService = new AuthService();
    private static UserService userService = new UserService();
    private static OrganizationService organizationService = new OrganizationService();
    private static AppContext context = AppContext.get();
    private static Logger log = Logger.getLogger(APILogin.class);
    private static final String ADMIN_ROLE = "admin";

    public static User getUserByRole(String role) {
        User searchedUser = null;
        for (User user : Entities.getUsers()) {
            if (user.getRoles() != null && user.getRoles().contains(role.toUpperCase())) {
                searchedUser = user;
                break;
            }
        }
        return searchedUser;
    }

    /**
     * Sign in as user and add LoggedUser in AppContext.
     *
     * @param role predefined Role of User for sign in.
     */
    public OperationResult<Token> signInAsUser(String role) {
        User user = getUserByRole(role);
        return signInAsUser(user);
    }

    /**
     * Sign in as user and add LoggedUser in AppContext.
     *
     * @param user User for sign in.
     */
    public OperationResult<Token> signInAsUser(User user) {
        log.info("Signing in as user: " + user.getName() + ", password:" + user.getPassword());

        OperationResult<Token> operationResult = signService.signIn(user.getName(), user.getPassword());
        if (operationResult.isSuccess()) {
            Token token = operationResult.getEntity();
            context.setLoggedUser(new LoggedUser(user));
            G4HttpClient.setCookie(Token.tokenCookieProperty, token.getValue());

            //update user
            OperationResult<User> meResult = new UserService().me();
            User me = meResult.getEntity();
            me.setPassword(user.getPassword());

            context.setLoggedUser(new LoggedUser(me));
        }
        return operationResult;
    }

    public OperationResult<Token> signInAsUser(String UserName, String Password) {
        log.info("Signing in as user: " + UserName);

        OperationResult<Token> operationResult = signService.signIn(UserName, Password);
        if (operationResult.isSuccess()) {
            Token token = operationResult.getEntity();
            //  context.setLoggedUser(new LoggedUser(UserName , Password));
            G4HttpClient.setCookie(Token.tokenCookieProperty, token.getValue());

            //update user
            OperationResult<User> meResult = new UserService().me();
            User me = meResult.getEntity();
            me.setPassword(Password);

            context.setLoggedUser(new LoggedUser(me));
        }
        return operationResult;
    }

    /**
     * Sign out as user
     *
     * @return {@link OperationResult<RequestResult>}
     */
    public OperationResult<RequestResult> signOut() {
        log.info("Signing out");

        OperationResult<RequestResult> operationResult = signService.signOut();
        if (operationResult.isSuccess()) {
            context.setLoggedUser(null);
        }

        return operationResult;
    }

    @Given("I sign in as user with permissions $permissions")
    public void signInWithPermissions(String permString) {
        signInAsUser(ADMIN_ROLE);

        User user = userService.getOrCreateUserWithPermissions(permString);
        Assert.assertNotNull(user);

        signInAsUser(user);
    }

    @Given("I sign in as user with all permissions except: $permissions")
    public void signInAsUserWithAllPermissionsExcept(String permString) {
        signInAsUser(ADMIN_ROLE);

        User user = userService.getOrCreateUserWithAllPermissionsExcept(permString);
        Assert.assertNotNull(user);

        signInAsUser(user);
    }

    @Given("I sign in as user with all permissions")
    public void signInAsUserWithAllPermissions() {
        signInAsUser(ADMIN_ROLE);

        User user = userService.getOrCreateUserWithAllPermissions();
        Assert.assertNotNull(user);

        signInAsUser(user);
    }

    @Given("Set new permissions: $permissions to current user and relogin")
    public void setNewPermissionToCurrentUserAndRelogin(String permString) {
        List<String> permissions = StringUtils.splitToList(permString);

        User currentUser = AppContext.get().getLoggedUser().getUser();
        signInAsUser(ADMIN_ROLE);
        userService.setPermissions(currentUser, permissions);
        signInAsUser(currentUser);
    }

    @Given("I sign in as user with classifications: $value")
    public void singinAsUserWithClassification(String value) {
        signInAsUser(ADMIN_ROLE);
        User createdUser = userService.getOrCreateUserWithClassification(value);
        signInAsUser(createdUser);
    }

    @Given("I sign in as user with orgUnits: $orgUnitsString")
    public void signInAsUserWithOrgUnits(String orgUnitsString) {
        signInAsUser(ADMIN_ROLE);
        User createdUser = userService.getOrCreateUserWithOrgUnits(orgUnitsString);
        signInAsUser(createdUser);
    }


    @When("CleanUp users")
    public void searchUsersAndTeamsInDefaultOrgUnit() {
        String defaultTeamId = UserService.getOrCreateDefaultTeamId();

        OrganizationFilter filter = new OrganizationFilter().filterBy("parentorgid", defaultTeamId);
        OperationResult<List<Organization>> operationResult = organizationService.search(filter);
        List<Organization> organizationList = operationResult.getEntity();

        List<User> users = new ArrayList<>(
                jsonToObjectsList(
                        toJsonString(organizationList),
                        User[].class
                )
        );

        List<OperationResult> operationResults = new ArrayList<>();
        users.stream()
                .filter(user -> user.getFullName().contains("qe_"))
                .forEach(organization -> operationResults.add(userService.remove(organization)));
        // log errors
        operationResults.stream()
                .filter(result -> !result.isSuccess())
                .forEach(OperationsResults::logError);
    }


    @AfterStories
    public void afterStoryTearDown() {
        log.info("TearDown for temp users, titles & responsibilities");
        assertTrue("Unable login as admin user!", signInAsUser(ADMIN_ROLE).isSuccess());

        List<OperationResult> operationResults;

        operationResults = new UserService().removeAll();
        for (OperationResult result : operationResults) {
            if (!result.isSuccess()) {
                log.error("Unable to delete User. Code:" + result.getCode() + ", Response:" + result.getMessage());
            }
        }

        operationResults = new TitleService().removeAll();
        for (OperationResult result : operationResults) {
            if (!result.isSuccess()) {
                log.error("Unable to delete Title. Code:" + result.getCode() + ", Response:" + result.getMessage());
            }
        }

        operationResults = new ResponsibilityService().removeAll();
        for (OperationResult result : operationResults) {
            if (!result.isSuccess()) {
                log.error("Unable to delete Responsibility. Code:" + result.getCode() + ", Response:" + result.getMessage());
            }
        }
    }
}
