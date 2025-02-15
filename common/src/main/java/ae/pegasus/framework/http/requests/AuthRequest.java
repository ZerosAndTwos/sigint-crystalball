package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;

import static ae.pegasus.framework.utils.StringUtils.capitalizeFirstLetter;

public class AuthRequest extends HttpRequest {

    private final static String URI = "/api/auth";

    public AuthRequest() {
        super(URI);
    }

    public AuthRequest singOut() {
        this
                .setURI(URI + "/tokens/me")
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }

    public AuthRequest signIn(String username, String password) {
        AuthRequest.User user = new AuthRequest.User(username, password);

        this
                .setURI(URI + "/tokens")
                .setHttpMethod(HttpMethod.POST)
                .setPayload(user);
        return this;
    }

    public AuthRequest dataSources() {
        this
                .setURI(URI + "/datasources")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public AuthRequest classifications() {
        this
                .setURI(URI + "/classifications")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    class User {
        private String name;
        private String password;

        public User(String name, String password) {
            this.name = capitalizeFirstLetter(name);
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
