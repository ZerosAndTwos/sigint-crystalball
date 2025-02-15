package ae.pegasus.framework.data_for_entity.data_providers.record;

import ae.pegasus.framework.app_context.AppContext;
import ae.pegasus.framework.data_for_entity.data_providers.DependencyData;
import ae.pegasus.framework.data_for_entity.data_providers.DependencyDataProvider;
import ae.pegasus.framework.model.SourceType;
import ae.pegasus.framework.utils.RandomGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class RecordTypeProvider extends DependencyDataProvider {

    @Override
    public String generate(int length) {

        DependencyData dependencyData = getDependencyData();
        String sSourceType = (String) dependencyData.getData("type");

        List<SourceType> sourceTypes = AppContext.get().getDictionary().getSourceTypes();
        if (sourceTypes == null) {
            throw new AssertionError("Got null sourceTypes list in Context");
        }

        if (sSourceType != null) {
            List<SourceType> typeList = sourceTypes.stream()
                    .filter(type -> type.getDataSource() != null && type.getDataSource().equals(sSourceType))
                    .collect(Collectors.toList());

            if (typeList.isEmpty()) {
                throw new AssertionError("Unable get subSource from dictionary for sourceType:" + sSourceType);
            } else {
                return RandomGenerator.getRandomItemFromList(typeList).getSubSource();
            }
        } else {
            throw new AssertionError("Unable generate subSource due sourceType is NULL");
        }
    }
}