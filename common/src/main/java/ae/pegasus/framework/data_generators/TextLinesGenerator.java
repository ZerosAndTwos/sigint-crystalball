package ae.pegasus.framework.data_generators;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextLinesGenerator extends BasicGenerator {

    private static Path pathToTextSource = null;
    private static List<String> lines = new ArrayList<>();

    public static String getText(int numberOfLines) {
        if (lines.isEmpty()) {
            lines = getLines();
        }
        int startIndex = getRandomIndexFor(lines);
        return String.join(System.lineSeparator(), lines.subList(startIndex, startIndex + numberOfLines)).replace("\r\n", "\n");
    }

    public static void setPathToTextSource(Path pathToTextSource) {
        TextLinesGenerator.pathToTextSource = pathToTextSource;
    }

    private static List<String> getLines() {
        if (pathToTextSource == null) {
            throw new IllegalStateException("Please provide path to text source");
        }
        try {
            return Files.readAllLines(pathToTextSource);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to generate text due to: " + e.getLocalizedMessage(), e.getCause());
        }
    }
}
