package http;

public class G4Response {
    
    private String jsonResponseString;
    private int code;
    
    public G4Response(String jsonResponseString, int code) {
        this.jsonResponseString = jsonResponseString;
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return jsonResponseString;
    }
}
