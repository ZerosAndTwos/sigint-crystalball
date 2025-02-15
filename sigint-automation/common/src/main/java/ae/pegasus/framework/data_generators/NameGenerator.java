package ae.pegasus.framework.data_generators;

public class NameGenerator extends BasicGenerator {

    private static final int MIN_NAME_SIZE = 3;
    private static final int MAX_NAME_SIZE = 10;

    public static String getRandomName() {
        int nameLength = getRand().nextSecureInt(MIN_NAME_SIZE, MAX_NAME_SIZE);
        StringBuilder result = new StringBuilder();
        result.append(StringGenerator.getAlphaString(1).toUpperCase());
        result.append(StringGenerator.getAlphaString(nameLength-1).toLowerCase());
        return result.toString();
    }
}
