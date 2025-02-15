package ae.pegasus.framework.controllers.login;

import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.login.LoginPage;
import ae.pegasus.framework.controllers.APILogin;
import ae.pegasus.framework.controllers.PageController;
import ae.pegasus.framework.model.User;

public class LoginController  extends PageController<LoginPage>{
    
    
    public LoginController() {
        super(Pages.loginPage(), LoginPage.class);
    }
    
   
    public void sign_in(User user) {
        getPage().load();
        getPage().typeUsername(user.getName());
        getPage().typePassword(user.getPassword());
        getPage().clickSignInButton();
        new APILogin().signInAsUser(user);
    }
}
