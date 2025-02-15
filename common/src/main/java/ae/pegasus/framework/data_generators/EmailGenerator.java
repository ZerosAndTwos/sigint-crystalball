package ae.pegasus.framework.data_generators;

public class EmailGenerator extends NameGenerator {
    public static final String E_MAIL_PART_DELIMITERS = "._-";
    public static final String E_MAIL_NAME_AND_HOST_DELIMITER = "@";

    private static final String[] E_MAIL_HOSTS = new String[]{
            "pegasus.ae",
            "gmail.com",
            "yandex.ru"};

    private static final int MIN_NAME_PARTS_NUMBER = 2;
    private static final int MAX_NAME_PARTS_NUMBER = 4;

    private static Character getEmailPartDelimiter() {
        return E_MAIL_PART_DELIMITERS.charAt(getRandomIndexFor(new char[][]{E_MAIL_PART_DELIMITERS.toCharArray()}));
    }

    private static String getEmailEnding() {
        return E_MAIL_NAME_AND_HOST_DELIMITER + E_MAIL_HOSTS[getRandomIndexFor(E_MAIL_HOSTS)];
    }

    public static String getRandomEmail() {
        String result;
        int numberOfNameParts = getRand().nextSecureInt(MIN_NAME_PARTS_NUMBER, MAX_NAME_PARTS_NUMBER);
        String[] nameParts = new String[numberOfNameParts];
        for (int partCreated = 0; partCreated < numberOfNameParts; partCreated++) {
            nameParts[partCreated] = getRandomName();
        }
        result = prepareName(nameParts);
        result += getEmailEnding();
        return result;
    }

    private static String prepareName(String... nameParts) {
        Character eMailPartsDelimiter = getEmailPartDelimiter();
        StringBuilder result = new StringBuilder();
        for (String part : nameParts) {
            result.append(part);
            result.append(eMailPartsDelimiter);
        }
        return result.delete(result.lastIndexOf(eMailPartsDelimiter.toString()), result.length()).toString();
    }

    public static String getEmailForName(String... nameParts) {
        return prepareName(nameParts) + getEmailEnding();
    }
}
