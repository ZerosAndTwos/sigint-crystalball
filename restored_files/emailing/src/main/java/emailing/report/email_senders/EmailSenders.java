package emailing.report.email_senders;

import ae.pegasus.framework.app_context.properties.G4Properties;
import app_context.properties.RunProperties;
import org.apache.log4j.Logger;

public class EmailSenders {
    
    private static RunProperties runProperties = G4Properties.getRunProperties();
    private static Logger logger = Logger.getLogger(EmailSenders.class);
    
    
    
    private static Reporters getReporterName() {
        Reporters reporter;
        String propReporter = runProperties.getEmailSender().toUpperCase();
        try {
            reporter = Reporters.valueOf(propReporter);
        } catch (IllegalArgumentException e) {
            logger.error("Type of email sender is not recognized as valid:"+ propReporter +
                        "Valid senders types:"+ Reporters.values().toString()+ " .Default value will be used - Pegasus");
            reporter = Reporters.PEGASUS;
        }
        
        return reporter;
        
    }
    
    public static EmailBaseSender getEmailSender() {
        Reporters reporter = getReporterName();
        if (reporter.equals(Reporters.GMAIL))
            return new GmailSender();
        else
            return  new PegasusSender();
        }
        
    }

