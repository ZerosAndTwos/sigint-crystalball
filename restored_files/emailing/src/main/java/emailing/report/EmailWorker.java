package emailing.report;

import ae.pegasus.framework.app_context.properties.G4Properties;
import emailing.email.EmailFactory;
import emailing.email.HtmlEmail;
import emailing.report.email_senders.EmailSenders;
import org.apache.log4j.Logger;

public class EmailWorker {
    
    private static Logger logger = Logger.getLogger(EmailWorker.class);
    
    public static void main(String[] args) {
        Boolean shouldEmail = G4Properties.getRunProperties().shouldEmail();
        if (shouldEmail) {
            HtmlEmail htmlEmail;
            try {
                logger.info("Forming email based on the current build results");
                htmlEmail = EmailFactory.buildHtmlEmail();
                if (htmlEmail == null) {
                    logger.info("Email will not be send as it is currently stable");
                    return;
                }
            } catch (AssertionError e) {
                logger.warn("Email will not be sent due to the error occurred while forming email");
                logger.error(e.getMessage(), e);
                return;
            }
            EmailSenders.getEmailSender().send_email(htmlEmail.getHtmlBody(), htmlEmail.getSubject());
            logger.info("Html Email is sent");
        }
    }
}
