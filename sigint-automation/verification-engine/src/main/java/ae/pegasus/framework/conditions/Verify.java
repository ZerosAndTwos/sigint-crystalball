package ae.pegasus.framework.conditions;

import ae.pegasus.framework.errors.VerificationError;
import org.apache.log4j.Logger;

public class Verify {

    private static Logger log = Logger.getLogger(Verify.class);

    public static void shouldBe(ExpectedCondition condition) {

        Boolean isSuccessful = condition.check();
        if (!isSuccessful) {
            String message = "Error comparing with condition: " + condition + ". Should be true but got false";
            log.error(message);
            throw new VerificationError(message);
        }

    }

    public static Boolean isTrue(ExpectedCondition condition) {
        return condition.check();
    }

    public static void shouldNotBe(ExpectedCondition condition) {
        Boolean isFalse = condition.check();
        if (isFalse) {
            String message = String.format("Error comparing with condition %s. Should be false but got true", condition);
            log.error(message);
            throw new VerificationError(message);
        }

    }
}