package model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DictionaryRecordType {

    private String id;
    private String type;
    private String letterCode;
    private String englishName;
    private String arabicName;

    public DictionaryRecordType (){}

    public DictionaryRecordType(RecordType recordType) {
        type = recordType.name();
        id = type;
        letterCode = recordType.toLetterCode();
        englishName = recordType.toEnglishName();
        arabicName = recordType.toArabicName();
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

    public static List<DictionaryRecordType> fromRecordTypes(RecordType[] recordTypes) {
        List<DictionaryRecordType> types = new ArrayList<>(recordTypes.length);
        for (RecordType recordType : recordTypes) {
            types.add(new DictionaryRecordType(recordType));
        }

        return types;
    }
}
