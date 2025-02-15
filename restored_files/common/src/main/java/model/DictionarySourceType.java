package model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DictionarySourceType {

    private String id;
    private String type;
    private String letterCode;
    private String englishName;
    private String arabicName;

    public DictionarySourceType() {}

    public DictionarySourceType(SourceType sourceType) {
        type = sourceType.name();
        id = type;
        letterCode = sourceType.toLetterCode();
        englishName = sourceType.toEnglishName();
        arabicName = sourceType.toArabicName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLetterCode() {
        return letterCode;
    }

    public void setLetterCode(String letterCode) {
        this.letterCode = letterCode;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public static List<DictionarySourceType> fromSourceTypes(SourceType[] sourceTypes) {
        List<DictionarySourceType> types = new ArrayList<>(sourceTypes.length);
        for (SourceType sourceType : sourceTypes) {
            types.add(new DictionarySourceType(sourceType));
        }

        return types;
    }
}
