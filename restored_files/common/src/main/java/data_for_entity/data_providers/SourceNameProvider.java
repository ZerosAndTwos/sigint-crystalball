package data_for_entity.data_providers;

import model.RecordType;
import model.SourceType;
import org.apache.commons.lang.RandomStringUtils;

public class SourceNameProvider extends DependencyDataProvider {
    
    @Override
    public Object generate(int length) {
        DependencyData dependencyData = getDependencyData();
        SourceType sourceType = SourceType.valueOf((String)dependencyData.getData("type"));
        RecordType recordType = RecordType.valueOf((String)dependencyData.getData("recordType"));
        
        if (sourceType == null || recordType == null) {
            return null;
        }
        return sourceType.toLetterCode() + "-" + recordType.toEnglishName() + "-" + RandomStringUtils.randomAlphabetic(10);
    }
}
