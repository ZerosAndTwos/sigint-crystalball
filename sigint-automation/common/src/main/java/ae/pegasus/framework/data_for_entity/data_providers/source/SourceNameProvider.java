package ae.pegasus.framework.data_for_entity.data_providers.source;

import ae.pegasus.framework.data_for_entity.data_providers.DependencyData;
import ae.pegasus.framework.data_for_entity.data_providers.DependencyDataProvider;
import org.apache.commons.lang3.RandomStringUtils;

public class SourceNameProvider extends DependencyDataProvider {

    @Override
    public Object generate(int length) {
        DependencyData dependencyData = getDependencyData();
        String sourceType = (String) dependencyData.getData("type");
        String recordType = (String) dependencyData.getData("recordType");

        if (sourceType == null || recordType == null) {
            throw new AssertionError("SourceType or RecordType isNull");
        }
        return sourceType + "-" + recordType + "-" + RandomStringUtils.randomAlphabetic(10);
    }
}
