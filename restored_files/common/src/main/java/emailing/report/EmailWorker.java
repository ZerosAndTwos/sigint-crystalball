package emailing.report;

import emailing.email.EmailFactory;
import org.apache.log4j.Logger;
import utils.FileHelper;

public class EmailWorker {
    
    private static Logger logger = Logger.getLogger(EmailWorker.class);
    
    public static void saveHtmlEmailToDisk() {
        String htmlEmail = EmailFactory.buildHmtlEmail();
        FileHelper.writeToFile("htmlEmail.html", htmlEmail);
    }
}
