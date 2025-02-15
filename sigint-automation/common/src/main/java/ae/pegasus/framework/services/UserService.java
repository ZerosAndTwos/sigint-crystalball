package ae.pegasus.framework.services;

import ae.pegasus.framework.data_for_entity.data_providers.user.UserPasswordProvider;
import ae.pegasus.framework.error_reporter.ErrorReporter;
import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.ChangePasswordRequest;
import ae.pegasus.framework.http.requests.UserRequest;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.utils.StringUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ae.pegasus.framework.json.JsonConverter.toJsonString;
import static ae.pegasus.framework.utils.RandomGenerator.getRandomItemFromList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserService implements EntityService<User> {

    private static final Logger log = Logger.getLogger(UserService.class);
    private static final UserRequest request = new UserRequest();
    private static final ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
    private static String defaultTeamId;
    private static Map<String, List<User>> permissionsUsersMap = new HashMap<>();
    private static OrganizationService organizationService = new OrganizationService();

    private static TitleService titleService = new TitleService();
    private static ResponsibilityService responsibilityService = new ResponsibilityService();

    private static final int PASSWORD_LENGTH = 10;

    public static String getOrCreateDefaultTeamId() {
        if (defaultTeamId == null) {
            defaultTeamId = organizationService.getOrCreateTeamByName("QE auto team").getId();
        }
        return defaultTeamId;
    }

    public static void setDefaultTeamId(String defaultTeamId) {
        UserService.defaultTeamId = defaultTeamId;
    }

    @Override
    public OperationResult<User> add(User entity) {
        log.info("Creating new user: " + toJsonString(entity));

        String adminPassword = appContext.getLoggedUser().getPassword();
        entity.setAdminPassword(adminPassword);

        G4Response response = g4HttpClient.sendRequest(request.add(entity));

        OperationResult<User> operationResult = new OperationResult<>(response, User.class);
        if (operationResult.isSuccess()) {
            User user = operationResult.getEntity();
            if (entity.getPassword() != null) {
                user.setPassword(entity.getPassword());
            }
            Entities.getUsers().addOrUpdateEntity(user);
            Entities.getOrganizations().addOrUpdateEntity(user);
            log.info("User is created:" + user.getName());
        } else {
            log.error("Can't create User " + entity.toString());
            log.error("Response:" + response.getMessage());
        }
        return operationResult;
    }

    @Override
    public OperationResult<RequestResult> remove(User entity) {
        String name = entity.getName() != null ? entity.getName() : entity.getFullName();
        log.info("Deleting user id:" + entity.getId() + " name:" + name);

        if (entity.getCreatedBy() == null) {
            ErrorReporter.raiseError("You are trying to delete system user: " + name);
        }

        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult<RequestResult> operationResult = new OperationResult<>(response, RequestResult.class);
        if (operationResult.isSuccess()) {
            Entities.getUsers().removeEntity(entity);
            Entities.getOrganizations().removeEntity(entity);

            permissionsUsersMap.values()
                    .forEach(users -> users
                            .removeIf(user -> Objects.equals(user.getName(), name)));
        }
        return operationResult;
    }

    @Override
    public OperationResult<List<User>> search(SearchFilter filter) {
        log.info("Search users by filter: " + filter);

        G4Response response = g4HttpClient.sendRequest(request.search(filter));

        OperationResult<User[]> operationResult = new OperationResult<>(response, User[].class);
        if (operationResult.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            return new OperationResult<>(response);
        }
    }

    @Override
    public OperationResult<List<User>> list() {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<User> update(User entity) {
        log.info("Update user id:" + entity.getId() + " name:" + entity.getName());

        String adminPassword = appContext.getLoggedUser().getPassword();
        entity.setAdminPassword(adminPassword);

        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<User> operationResult = new OperationResult<>(response, User.class);
        if (operationResult.isSuccess()) {
            User user = operationResult.getEntity();
            if (entity.getNewPassword() != null) {
                user.setPassword(entity.getNewPassword());
                user.setNewPassword(null);
            }
            Entities.getUsers().addOrUpdateEntity(user);
            Entities.getOrganizations().addOrUpdateEntity(user);
        }
        return operationResult;
    }

    @Override
    public OperationResult<User> view(String id) {
        throw new NotImplementedException("");
    }

    /**
     * Get current G4 user.
     * API: GET "/api/auth/users/me"
     *
     * @return current G4 user
     */
    public OperationResult<User> me() {
        log.info("Get current user...");

        G4Response response = g4HttpClient.sendRequest(request.me());

        OperationResult<User> operationResult = new OperationResult<>(response, User.class);
        if (operationResult.isSuccess()) {
            Entities.getUsers().addOrUpdateEntity(operationResult.getEntity());
            Entities.getOrganizations().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    public OperationResult<AuthResponseResult> changePassword(User user) {
        String adminPassword = appContext.getLoggedUser().getPassword();
        String id = appContext.getLoggedUser().getId();

        UserPassword.UserPasswordBuilder builder = new UserPassword.UserPasswordBuilder()
                .newPassword(user.getNewPassword());

        if (Objects.equals(id, user.getId())) {
            log.info(String.format("Changing password for currently logged in %s from %s to %s",
                    user.getName(), user.getPassword(), user.getNewPassword()));
            builder.oldPassword(user.getPassword());
        } else {
            log.info(String.format("Changing %s's password from %s to %s",
                    user.getName(), user.getPassword(), user.getNewPassword()));
            builder.adminPassword(adminPassword);
        }

        G4Response response = g4HttpClient.sendRequest(changePasswordRequest.updatePassword(builder.build(), user.getId()));
        return processPasswordResponse(user, response);
    }

    public OperationResult<AuthResponseResult> changeTempPassword(User user) {
        log.info(String.format("Setting first password for %s user: %s",
                user.getName(), user.getNewPassword()));

        UserPassword userPassword = new UserPassword.UserPasswordBuilder()
                .name(user.getName())
                .newPassword(user.getNewPassword())
                .oldPassword(user.getPassword())
                .build();

        G4Response response = g4HttpClient.sendRequest(changePasswordRequest.updateTempPassword(userPassword));
        return processPasswordResponse(user, response);
    }

    private static OperationResult<AuthResponseResult> processPasswordResponse(User user, G4Response response) {

        OperationResult<AuthResponseResult> operationResult =
                new OperationResult<>(response, AuthResponseResult.class);

        if (operationResult.isSuccess()) {
            user.setPassword(user.getNewPassword());
            user.setNewPassword(null);
            Entities.getUsers().addOrUpdateEntity(user);
            Entities.getOrganizations().addOrUpdateEntity(user);
        }
        return operationResult;
    }

    /**
     * Get report role for G4 User.
     *
     * @param user G4 user
     * @return 'Report role' for G4 user
     * <br>note: ADMIN user has 'approver' role
     */
    public String getReportRole(User user) {
        List<String> roles = user.getDefaultPermission().getActions();
        if (roles.contains("REPORT_UPDATE_APPROVER")) {
            return "approver";
        } else if (roles.contains("REPORT_UPDATE_ANALYST")) {
            return "analyst";
        } else if (roles.contains("REPORT_UPDATE")) {
            return "operator";
        } else {
            log.error("Can not get reportRole for current user");
            throw new AssertionError("Can not get reportRole for current user");
        }
    }

    public User getOrCreateUserWithAllPermissions() {
        User user = User.newBuilder()
                .newUser()
                .withAllPermission()
                .withAllClassification()
                .withAllSources()
                .build();

        return getOrCreateUserWithPermissions(user, null);
    }

    public User getOrCreateUserWithAllPermissionsExcept(String permString) {
        User user = User.newBuilder()
                .newUser()
                .withAllPermission()
                .exceptPermissions(StringUtils.splitToArray(permString))
                .withAllClassification()
                .withAllSources()
                .build();

        return getOrCreateUserWithPermissions(user, null);
    }

    public User getOrCreateUserWithPermissions(String permString) {
        User user = User.newBuilder()
                .newUser()
                .withPermission(StringUtils.splitToArray(permString))
                .withAllClassification()
                .withAllSources()
                .build();

        return getOrCreateUserWithPermissions(user, null);
    }

    public User getOrCreateUserWithPermissions(User user) {
        return getOrCreateUserWithPermissions(user, null);
    }

    public User getOrCreateUserWithPermissions(User entity, String orgUnitID) {
        String permissions = entity.getDefaultPermission().getActions().toString();
        if (orgUnitID == null || orgUnitID.isEmpty()) {
            orgUnitID = getOrCreateDefaultTeamId();
        }

        // find in user storage
        User userWithPermissions = getUserWithPermissions(entity, orgUnitID);
        if (userWithPermissions != null)
            return userWithPermissions;

        // add orgUnits with permissions
        addOrgUnit(entity, orgUnitID);

        // create user
        OperationResult<User> userOperationResult = add(entity);
        if (userOperationResult.isSuccess()) {
            userWithPermissions = userOperationResult.getEntity();

            // update user password
            userWithPermissions.setNewPassword(new UserPasswordProvider().generate(PASSWORD_LENGTH));
            OperationResult<AuthResponseResult> firstPasswordChangeResult = changeTempPassword(userWithPermissions);
            if (!firstPasswordChangeResult.isSuccess()) {
                log.error(firstPasswordChangeResult.getMessage());
                throw new AssertionError("Unable change password for new user: " + toJsonString(userWithPermissions));
            }

            // add user to storage
            List<User> users = permissionsUsersMap.get(permissions);
            if (users != null) {
                assertTrue("Unable add created user to user storage!", users.add(userWithPermissions));
            } else {
                users = Stream.of(userWithPermissions).collect(Collectors.toList());
            }
            permissionsUsersMap.put(permissions, users);
        } else {
            log.error("Unable create User: " + toJsonString(entity));
            throw new OperationResultError(userOperationResult);
        }
        return userWithPermissions;
    }

    public void setPermissions(User user, List<String> permissions) {
        Responsibility responsibility = responsibilityService.createWithPermissions(permissions);

        // get user title
        List<TeamTitle> teamTitles = user.getDefaultPermission().getTeamTitles();
        assertFalse("Current user without TeamTitles!", teamTitles.isEmpty());

        TeamTitle teamTitle = getRandomItemFromList(teamTitles);
        String titleID = getRandomItemFromList(teamTitle.getTitles());
        Title title = titleService.view(titleID).getEntity();

        // update user title with new responsibilities
        title.setResponsibilities(Collections.singletonList(responsibility.getId()));
        OperationResult<Title> titleOperationResult = titleService.update(title);
        if (!titleOperationResult.isSuccess())
            throw new AssertionError("Unable update Title: " + toJsonString(title));
    }

    public List<OperationResult> removeAll() {
        List<OperationResult> operationResults = new ArrayList<>();

        new ArrayList<>(Entities.getUsers().getEntities()).stream()
                .filter(user -> user.getCreatedBy() != null && user.getName().toLowerCase().startsWith("qe_"))
                .forEach(user -> operationResults.add(remove(user)));

        return operationResults;
    }

    public User getUserWithPermissions(User entity, String orgUnitID) {
        String permissions = entity.getDefaultPermission().getActions().toString();
        List<User> users = permissionsUsersMap.get(permissions);

        User createdUser = null;
        if (users != null && !users.isEmpty()) {
            users = users.stream()
                    .filter(user -> user.getDefaultPermission().getRecord().getClearances()
                            .containsAll(entity.getDefaultPermission().getRecord().getClearances()))
                    .filter(user -> user.getDefaultPermission().getTeamTitles().get(0).getOrgUnitId()
                            .equals(orgUnitID))
                    .collect(Collectors.toList());
            if (!users.isEmpty()) {
                createdUser = getRandomItemFromList(users);
                log.info("User already exist:" + createdUser.getName());
            }
        }
        return createdUser;
    }

    public User addOrgUnitWithTitles(User user, String orgUnitId, List<String> titleIDs) {
        user.getParentTeamIds().add(orgUnitId);
        user.getParentTeams().put(orgUnitId, Collections.singletonList("MEMBER"));

        TeamTitle teamTitle = new TeamTitle();
        teamTitle.setOrgUnitId(orgUnitId);
        teamTitle.setTitles(titleIDs);
        user.getDefaultPermission().getTeamTitles().add(teamTitle);

        return user;
    }

    public User addOrgUnit(User user, String orgUnitId) {
        List<String> permissions = user.getDefaultPermission().getActions();
        Responsibility responsibility = responsibilityService.createWithPermissions(permissions);
        Title title = titleService.createWithResponsibility(responsibility);

        return addOrgUnitWithTitles(user, orgUnitId, Collections.singletonList(title.getId()));
    }

    public User getOrCreateUserWithClassification(String classificationsString) {
        String[] classifications = StringUtils.splitToArray(classificationsString);

        User user = User.newBuilder()
                .newUser()
                .withClassification(classifications)
                .withAllPermission()
                .withAllSources()
                .build();

        return getOrCreateUserWithPermissions(user);
    }

    public User getOrCreateUserWithOrgUnits(String orgUnitsString) {
        String[] orgUnits = StringUtils.splitToArray(orgUnitsString);

        User user = User.newBuilder()
                .newUser()
                .withAllClassification()
                .withAllPermission()
                .withAllSources()
                .build();

        OrganizationService organizationService = new OrganizationService();
        for (String orgUnit : orgUnits) {
            Team team = organizationService.getOrCreateTeamByName(orgUnit);
            addOrgUnit(user, team.getId());
        }

        return getOrCreateUserWithPermissions(user);
    }
}
