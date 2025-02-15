package email;

import email.html_body_builder.EmailBodyBuilder;

public abstract class HtmlEmail {
    
    public String getSubject() {
        return subject;
    }
    
    public String getHtmlBody() {
        return htmlBody;
    }
    
    protected String subject;
    protected String htmlBody;
    protected EmailBodyBuilder emailBodyBuilder;
    
    protected abstract String initSubject();
    
    public HtmlEmail(EmailBodyBuilder builder) {
        this.emailBodyBuilder = builder;
        this.htmlBody = initBody();
        this.subject = initSubject();
    }
    
    protected String initBody() {
        if (emailBodyBuilder == null) {
            throw  new AssertionError("Cannot build email body. EmailBodyBuilder is null");
        }
            return emailBodyBuilder.buildBody();
    }
    
    
}
