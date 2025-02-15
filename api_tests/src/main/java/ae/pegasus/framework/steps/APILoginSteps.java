package ae.pegasus.framework.steps;

import ae.pegasus.framework.Searchexe;
import ae.pegasus.framework.controllers.APILogin;
import ae.pegasus.framework.data_for_entity.data_providers.user.UserPasswordProvider;
import ae.pegasus.framework.model.User;
import ae.pegasus.framework.model.entities.Entities;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;


public class APILoginSteps extends APISteps {

    private static APILogin login = new APILogin();

    @Given("I sign in as $role user")
    public void signInGlobal(String role) {
        login.signInAsUser(role);
        checkResultSuccess();
    }

    public void signInGlobal() {
        login.signInAsUser(Searchexe.username ,Searchexe.password );
        checkResultSuccess();
    }
    @When("I sent sign in request as $role user with correct credentials")
    public void signInasCorrectUser(String role) {
        login.signInAsUser(role);
    }

    @When("I sent sign in request as $role user with incorrect credentials")
    public void signInAsIncorrectUser(String role) {
        User user = APILogin.getUserByRole(role);

        if (user != null) {
            String currentPassword = user.getPassword();
            user.setPassword(new UserPasswordProvider().generate(10));

            try {
                login.signInAsUser(user);
            } finally {
                user.setPassword(currentPassword);
            }

        } else {
            throw new AssertionError("Unable get user by role:" + role);
        }
    }

    @When("I send sign out request")
    public void signOut() {
        login.signOut();
    }

    @When("I sign in as new created user")
    public void signInAsNewCreatedUser() {
        User user = Entities.getUsers().getLatest();
        login.signInAsUser(user);
    }
}
