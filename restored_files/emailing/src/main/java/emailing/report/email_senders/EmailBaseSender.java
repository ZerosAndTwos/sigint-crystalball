package emailing.report.email_senders;

import ae.pegasus.framework.app_context.properties.G4Properties;
import app_context.properties.MailProperties;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public abstract class EmailBaseSender {
    
    
    static MailProperties mailProperties = G4Properties.getMailProperties();
    private int tries = 0;
    Logger logger = Logger.getLogger(EmailBaseSender.class);
    
    
    protected abstract Session getSession();
    
    public void send_email(String html, String subject) {
        logger.info("Sending email...");
        Session session = getSession();
        if (session == null) {
            logger.error("Cannot send email due to not created session");
            return;
        }
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailProperties.getFromAddress()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailProperties.getRecipients()));
            message.setSubject(subject);
            message.setContent(html, "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            if (tries < 3) {
                tries++;
                send_email(html, subject);
            }
            logger.error("Cannot send email", e);
        }
    }
}
