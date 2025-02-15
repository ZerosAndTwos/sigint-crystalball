package emailing.report;

import model.AppContext;
import org.apache.log4j.Logger;

import javax.mail.*;
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
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.port", mailProperties.getProperty("port"));
        prop.setProperty("mail.smtp.starttls.enable", "true");
        //Session session1 = Session.getDefaultInstance(prop);
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return  new PasswordAuthentication(mailProperties.getProperty("account"),
                        mailProperties.getProperty("password"));
            }
            
        });
        send(session, emailBody, subject);
    }
    
    
    private  void send(Session session, String html, String subject) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailProperties.getProperty("from")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailProperties.getProperty("recepients")));
            message.setSubject(subject);
            message.setContent(html, "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            if (tries < 3) {
                tries++;
                send(session, html, subject);
            }
            logger.error(e.getMessage());
            logger.trace("Cannot send email", e);
            e.printStackTrace();
    
        }
    }
}
