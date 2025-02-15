package verifier;

import conditions.ExpectedCondition;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class Verify {

    private static Logger log = Logger.getLogger(Verify.class);

    public static void shouldBe(ExpectedCondition conditions) {
        try {
        Assert.assertTrue(conditions.check()); }
        catch (AssertionError e) {
            log.error("Error comparing with condition:"+conditions+".Should be true but got false");
            throw new AssertionError();
        }
    }
}
