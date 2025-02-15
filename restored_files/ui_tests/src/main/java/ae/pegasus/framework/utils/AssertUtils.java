package ae.pegasus.framework.utils;

import ae.pegasus.framework.assertion.Asserter;

import java.util.Arrays;
import java.util.List;

public class AssertUtils {
    public static void checkArrays(String[] actual, String[] expected, String verifiedParameter) {
        Arrays.sort(actual);
        Arrays.sort(expected);
        Asserter.getAsserter().softAssertEquals(Arrays.asList(actual), Arrays.asList(expected), verifiedParameter);
    }

    public static void checkLists(List<String> actualValues, List<String> expectedValues, String verifiedParameter) {
        actualValues.sort(String::compareToIgnoreCase);
        expectedValues.sort(String::compareToIgnoreCase);
        Asserter.getAsserter().softAssertEquals(
                actualValues,
                expectedValues,
                verifiedParameter);
    }

}
