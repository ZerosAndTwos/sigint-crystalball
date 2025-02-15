package emailing.email.html_body_builder;

public class StillFailingContentBuilder extends FailingContentBuilder {
    
    public StillFailingContentBuilder(String stand) {
        super(stand);
    }
    
    @Override
    protected String buildSubject() {
        return getStand() + ":Automation build still failing";
    }
}
