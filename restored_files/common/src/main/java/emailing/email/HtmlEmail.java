package emailing.email;


import emailing.email.html_body_builder.EmailBodyBuilder;

public class HtmlEmail {
    
    public String getHtmlBody() {
        return htmlBody;
    }
    
    private String htmlBody;
    private EmailBodyBuilder emailBodyBuilder;
   
    
    HtmlEmail(EmailBodyBuilder builder) {
        this.emailBodyBuilder = builder;
        this.htmlBody = initBody();
    }
    
    private String initBody() {
        if (emailBodyBuilder == null) {
            throw  new AssertionError("Cannot build email body. EmailBodyBuilder is null");
        }
            return emailBodyBuilder.buildBody();
    }
    
    
}
