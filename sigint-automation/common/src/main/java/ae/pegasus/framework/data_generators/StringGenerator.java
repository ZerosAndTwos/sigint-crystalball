package ae.pegasus.framework.data_generators;

import org.apache.commons.lang3.RandomStringUtils;

public class StringGenerator extends BasicGenerator {

    public static String getNumericString(int stringLength) {
        return RandomStringUtils.randomNumeric(stringLength);
    }

    public static String getAlphaNumericString(int stringLength) {
        return RandomStringUtils.randomAlphanumeric(stringLength);
    }

    public static String getAlphaString(int stringLength) {
        return RandomStringUtils.randomAlphabetic(stringLength);
    }
}
