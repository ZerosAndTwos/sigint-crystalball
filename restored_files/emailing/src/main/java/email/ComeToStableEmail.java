package email;

import email.html_body_builder.EmailBodyBuilder;

class ComeToStableEmail extends HtmlEmail {
    
    ComeToStableEmail(EmailBodyBuilder builder) {
        super(builder);
    }
    
    @Override
    protected String initSubject() {
        return "Automation build come to stable";
    }
}
