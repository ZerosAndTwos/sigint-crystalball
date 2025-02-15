package http.requests;

public class GetDictionariesRequest extends HttpRequest {

    private final static String URI = "/api/sigint/dictionaries";

    public GetDictionariesRequest() {
        super(URI);
    }
}
