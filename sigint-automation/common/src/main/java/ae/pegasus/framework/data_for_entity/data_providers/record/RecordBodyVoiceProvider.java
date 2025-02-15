package ae.pegasus.framework.data_for_entity.data_providers.record;

import ae.pegasus.framework.data_for_entity.data_providers.DependencyData;
import ae.pegasus.framework.data_for_entity.data_providers.DependencyDataProvider;
import ae.pegasus.framework.utils.RandomGenerator;

public class RecordBodyVoiceProvider extends DependencyDataProvider {
    
    @Override
    public Integer generate(int length) {
        DependencyData dependencyData = getDependencyData();
        String type = (String) dependencyData.getData("type");
        if (type.equals("Voice")) {
            return RandomGenerator.getRandomDuration();
        } else  return null;
    }
}
