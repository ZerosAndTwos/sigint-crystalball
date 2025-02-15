package report;

import email.EmailFactory;
import email.HtmlEmail;
import email_senders.EmailSender;
import model.AppContext;
import org.apache.log4j.Logger;
import post_build_managers.BuildCondition;

public class EmailWorker {
    
    private static Logger logger = Logger.getLogger(EmailWorker.class);
    
    private static void send_email() {
        logger.info("Sending email on failure test results...");
        HtmlEmail email = EmailFactory.getEmail(BuildCondition.getPreviousStatus(), BuildCondition.getCurrentStatus());
        if (email == null) {
            logger.warn("Email is empty hence will not be sent");
            return;
        }
        EmailSender sender = new EmailSender();
        sender.send_email(email.getSubject(), email.getHtmlBody());
        BuildCondition.resetBuildFlag();
    }
    
    public static void main(String[] args) {
        Boolean shouldEmail = Boolean.valueOf(AppContext.getContext().getGeneralProperties().getProperty("email"));
        if (shouldEmail) {
            send_email();
        }
    }
}
