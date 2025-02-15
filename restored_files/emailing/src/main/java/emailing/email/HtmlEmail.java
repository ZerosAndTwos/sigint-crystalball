package emailing.email;


public class HtmlEmail {
    
    public String getHtmlBody() {
        return htmlBody;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    private String subject;
    private String htmlBody;
    private EmailContentBuilder emailBuilder;
   
    
    HtmlEmail(EmailContentBuilder builder) {
        this.emailBuilder = builder;
        this.htmlBody = initBody();
        this.subject = initSubject();
    }
    
    
    private String initSubject() {
        return emailBuilder.buildSubject();
    }
    
    private String initBody() {
            return emailBuilder.buildBody();
    }
    
    
}
