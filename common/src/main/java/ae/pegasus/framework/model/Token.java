package ae.pegasus.framework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {

    public static final String tokenProperty = "accessToken";
    public static final String tokenCookieProperty = "t";
    private String value;
    private String id;

    @JsonProperty(tokenProperty)
    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}