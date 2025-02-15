package data_for_entity.data_providers;

import model.RecordType;
import utils.RandomGenerator;

public class RecordBodyVoiceProvider extends DependencyDataProvider {
    
    @Override
    public Object generate(int length) {
        DependencyData dependencyData = getDependencyData();
        String type = (String) dependencyData.getData("type");
        if (type.equals(RecordType.Voice)) {
            return RandomGenerator.getRandomDuration();
        } else  return null;
    }
}
