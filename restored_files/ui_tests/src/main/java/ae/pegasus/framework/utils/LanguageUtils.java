package ae.pegasus.framework.utils;

import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.language.detect.LanguageResult;

import java.io.IOException;

public class LanguageUtils {

    private static LanguageDetector LANGUAGE_DETECTOR = null;

    public static LanguageDetector getLanguageDetector() {
        if (LANGUAGE_DETECTOR == null) {
            try {
                LANGUAGE_DETECTOR = LanguageDetector.getDefaultLanguageDetector().loadModels();
            } catch (IOException e) {
                throw new IllegalStateException(e.getCause());
            }
        }
        return LANGUAGE_DETECTOR;
    }

    public static LanguageResult detectLanguage(String toDetect) {
        return getLanguageDetector().detect(toDetect);
    }


}
