package ae.pegasus.framework.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectUtils {

    /**
     * Convert object to Map. Key - field name, value - field value.
     *
     * @param obj object to process
     * @return Map with fields name and value
     */
    public static Map<String, Object> fieldsValueToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return map;
    }

    public static Map<String, Object> mapWithoutNullValues(Map<String, Object> map) {
        Set<Map.Entry<String, Object>> set = map.entrySet().stream()
                .filter(stringObjectEntry -> stringObjectEntry.getValue() != null)
                .collect(Collectors.toSet());
        return set.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
