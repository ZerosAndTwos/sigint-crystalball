package email;

import email.html_body_builder.FailingBodyBuilder;
import email.html_body_builder.StableBodyBuilder;
import post_build_managers.BuildStatus;

public class EmailFactory {
    
    public static HtmlEmail getEmail(BuildStatus previousStatus, BuildStatus currentStatus) {
        if (previousStatus.equals(BuildStatus.FAILED)) {
            return (currentStatus.equals(BuildStatus.FAILED) || currentStatus.equals(BuildStatus.UNKNOWN))
                    ? new StillFailingEmail(new FailingBodyBuilder()) : new ComeToStableEmail(new StableBodyBuilder()); }
        else if (previousStatus.equals(BuildStatus.PASSED) || previousStatus.equals(BuildStatus.UNKNOWN)) {
            return (currentStatus.equals(BuildStatus.FAILED)) ? new FailingEmail(new FailingBodyBuilder()): null;
            }
        throw  new AssertionError("Build status wasn't recognized");
        }
    
    }

