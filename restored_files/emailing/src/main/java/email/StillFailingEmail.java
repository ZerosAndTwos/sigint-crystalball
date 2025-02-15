package email;

import email.html_body_builder.EmailBodyBuilder;

class StillFailingEmail extends FailingEmail {
    
    StillFailingEmail(EmailBodyBuilder builder) {
        super(builder);
    }
    
    @Override
    protected String initSubject() {
        return "Automation build is still failing";
    }
}
