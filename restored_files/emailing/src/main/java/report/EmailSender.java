package report;

import model.AppContext;

public class EmailSender {
    
    public static void main(String[] args) {
        Boolean shouldEmail = Boolean.valueOf(AppContext.getContext().getGeneralProperties().getProperty("email"));
        if (shouldEmail) {
           new EmailWorker().send_email();
        }
    }
}
