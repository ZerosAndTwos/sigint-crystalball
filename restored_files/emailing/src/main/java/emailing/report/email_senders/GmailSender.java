package emailing.report.email_senders;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

class GmailSender extends EmailBaseSender{
    
    @Override
    protected Session getSession() {
        Session session = null;
        Properties prop = System.getProperties();
        prop.setProperty("mail.smtp.host", mailProperties.getSmtpGmailHost());
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.port", mailProperties.getPort());
        prop.setProperty("mail.smtp.starttls.enable", "true");
        try {
        session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailProperties.getAccount(),
                        mailProperties.getPassword());
            }
    
        }); } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return session;
        }
    }
