package steps;

import data_for_entity.RandomEntities;
import http.OperationResult;
import model.User;
import services.GroupService;
import services.UserService;

public class UserSteps {

    private static GroupService groupService = new GroupService();
    private static RandomEntities randomEntities = new RandomEntities();
    private static UserService userService = new UserService();

    @Deprecated
    public static User createUserWithPermissions(String...permissions) {
        //TODO implement it
        User newUser = randomEntities.randomEntity(User.class);
        OperationResult<User> userOperationResult = userService.add(newUser);
        return userOperationResult.getEntity();
    }
}
