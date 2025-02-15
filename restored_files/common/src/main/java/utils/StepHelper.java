package utils;

public class StepHelper {

    public static boolean compareByCriteria(String criteria, String actualValue, String expectedValue) {
        int actual = Integer.valueOf(actualValue);
        int expected = Integer.valueOf(expectedValue);

        return compareByCriteria(criteria, actual, expected);
    }

    public static boolean compareByCriteria(String criteria, int actualValue, int expectedValue) {

        boolean condition;

        switch (criteria.trim()) {
            case ">":
                condition = actualValue > expectedValue;
                break;
            case "<":
                condition = actualValue < expectedValue;
                break;
            case "==":
                condition = actualValue == expectedValue;
                break;
            default:
                throw new AssertionError("Unknown criteria value, expected: >, < or ==");
        }
        return condition;
    }
}
