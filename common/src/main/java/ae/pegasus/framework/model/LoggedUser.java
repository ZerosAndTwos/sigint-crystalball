package ae.pegasus.framework.model;

public class LoggedUser {
    
    private User user;

    public LoggedUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return user.getId();
    }

    public String getPassword() {
        return user.getPassword();
    }
}
