package reporter;

import post_build_managers.EmailManager;
import failure_strategy.Statistic;
import org.apache.log4j.Logger;

public class EmailCondition {
    
    private static Logger logger = Logger.getLogger(EmailCondition.class);
    private static EmailManager emailManager = new EmailManager();
    
    public static void setEmailtoSucess() {
        try {
v            emailManager.updateEmailSendInfo(false);
        } catch (Exception e) {
            logger.error("Was unable to mark email send as sucess. Might cause failures in email of next" +
                    "run");
        }
    }
    
    public static void setEmailCondition() {
        try {
            if (Statistic.hasFailuresWithoutBugs() && !emailManager.getEmailSendInfo()) {
                emailManager.updateEmailSendInfo(true);
            }
        } catch (Exception e) {
            logger.error("Was unable to update email info. Email might not be sent");
        }
    }
    
    public static boolean getEmailCondition() throws Exception {
        return emailManager.getEmailSendInfo();
    }
       
    
}
