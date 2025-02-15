package email;

import email.html_body_builder.EmailBodyBuilder;

class FailingEmail extends HtmlEmail {
    
    
    FailingEmail(EmailBodyBuilder builder) {
        super(builder);
    }
    
    @Override
    protected String initSubject() {
        return "Automation build failed";
    }
}
