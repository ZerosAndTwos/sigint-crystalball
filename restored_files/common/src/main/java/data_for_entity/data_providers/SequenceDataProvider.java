package data_for_entity.data_providers;

import java.util.Collection;
import java.util.StringJoiner;

public class SequenceDataProvider extends DependencyDataProvider {

    @Override
    public Object generate(int length) {
        StringJoiner joiner = new StringJoiner(" ");
        DependencyData dependencyData = getDependencyData();
        Collection<Object> values = dependencyData.getValues();
        for (Object value : values) {
            joiner.add((String) value);
        }
        return joiner.toString();
    }
}
