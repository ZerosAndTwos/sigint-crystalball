package email_senders;

import model.AppContext;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    
    
    private static Properties mailProperties = AppContext.getContext().getMailProperties();
    private int tries = 0;
    private Logger logger = Logger.getLogger(EmailSender.class);
    
    public void send_email(String subject, String emailBody) {
        Properties prop = System.getProperties();
        prop.setProperty("mail.smtp.host", mailProperties.getProperty("smtp"));
        Session session = Session.getDefaultInstance(prop);
        send(session, emailBody, subject);
    }
    
    
    private  void send(Session session, String html, String subject)  {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailProperties.getProperty("from")));
            message.addRecipients(Message.RecipientType.TO, mailProperties.getProperty("recepients"));
            message.setSubject(subject);
            message.setContent(html, "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            if (tries < 3) {
                tries++;
                send(session, html, subject);
            }
            logger.error("Cannot send email");
            logger.error(e.getMessage());
            
        }
    }
    
    
    
}
