package emailing.report;

import emailing.email.EmailFactory;
import org.apache.log4j.Logger;
import utils.FileHelper;

public class EmailFormer {
    
    private static Logger logger = Logger.getLogger(EmailFormer.class);
    
    
    public static void saveHtmlFileToDisk() {
        logger.info("Saving html email to user home directory...");
        String htmlEmail = EmailFactory.buildHmtlEmail();
        FileHelper.writeToFile("htmlEmail.html", htmlEmail);
        logger.info("Html Email is saved");
    }
}
