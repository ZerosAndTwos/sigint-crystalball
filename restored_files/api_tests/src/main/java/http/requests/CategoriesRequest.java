package http.requests;

public class CategoriesRequest extends HttpRequest {

    private static final String URI = "/api/reports/categories";

    public CategoriesRequest() {
        super(URI);
    }
}
