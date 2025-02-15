package json;

import model.entities.EntityList;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ValueNode;
import org.glassfish.jersey.media.multipart.MultiPart;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static utils.StringUtils.prettyPrint;

public class JsonConverter {

    private static Logger log = Logger.getRootLogger();
    public static ObjectMapper mapper = new ObjectMapper();

    private static final String CONVERT_OBJ_MSG = "Converting json: \n'%s'\n to object: %s";
    private static final String CONVERT_OBJ_ERR = "Error when converting json: \n'%s'\n to object: %s";

    private static final String CONVERT_LIST_MSG = "Converting json: \n'%s'\n to object list: %s";
    private static final String CONVERT_LIST_ERR = "Error when converting json: \n'%s'\n to object list: %s";

    public static Map<String, String> loadJsonToStringMap(String filename) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        InputStream entityList = classloader.getResourceAsStream(filename);
        if (entityList == null) {
            String error = "Unable to get list of entities from file:" + filename;
            log.error(error);
            throw new Error(error);
        }

        MapType mapType = JsonConverter.constructMapTypeToValue(String.class);
        try {
            return JsonConverter.mapper.readValue(entityList, mapType);
        } catch (IOException e) {
            String error = "Cannot load list of entities";
            log.error(error);
            log.error(e.getMessage(), e);
            throw new Error(error);
        }
    }

    private static MapType constructMapTypeToValue(Class<?> valueClass) {
        TypeFactory typeFactory = mapper.getTypeFactory();
        return typeFactory.constructMapType(HashMap.class, String.class, valueClass);
    }

    public static String toJsonString(Object object) {
        if (object instanceof MultiPart) {
            return "Multipart object";
        }
        log.debug("Converting to json string Object " + object);
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (IOException | NullPointerException e) {
            String error = "Error occurred when trying to convert object with class:"
                    + object.getClass() + " to JSON string";
            log.error(error);
            log.error(e.getMessage(), e);
            throw new Error(error, e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T jsonToObject(String jsonString, Class<T> userClass) {
        log.debug(String.format(CONVERT_OBJ_MSG, prettyPrint(jsonString), userClass));
        try {
            return userClass == String.class ? (T) jsonString : mapper.readValue(jsonString, userClass);
        } catch (IOException | NullPointerException e) {
            String error = String.format(CONVERT_OBJ_ERR, prettyPrint(jsonString), userClass);
            log.error(error);
            log.error(e.getMessage(), e);
            throw new RuntimeException(error);
        }
    }

    public static <T> T jsonToObject(String jsonString, Class<T> entityClass, String wrapperField) {
        log.debug(String.format(CONVERT_OBJ_MSG, prettyPrint(jsonString), entityClass));
        MapType mapType = JsonConverter.constructMapTypeToValue(Object.class);
        try {
            HashMap<String, Object> map = mapper.readValue(jsonString, mapType);
            Object obj = map.get(wrapperField);
            return mapper.convertValue(obj, entityClass);
        } catch (IOException | NullPointerException e) {
            String error = String.format(CONVERT_OBJ_ERR, prettyPrint(jsonString), entityClass);
            log.error(error);
            log.error(e.getMessage(), e);
            throw new Error(error);
        }
    }

    public static <T> List<T> jsonToObjectsList(String jsonString, Class<T[]> userClass) {
        log.debug(String.format(CONVERT_LIST_MSG, prettyPrint(jsonString), userClass));
        try {
            return Arrays.asList(mapper.readValue(jsonString, userClass));
        } catch (IOException | NullPointerException e) {
            String error = String.format(CONVERT_LIST_ERR, prettyPrint(jsonString), userClass);
            log.error(error);
            log.error(e.getMessage(), e);
            throw new RuntimeException(error);
        }
    }

    public static <T extends EntityList> T jsonToObjectsList(InputStream stream, Class<T> userClass) {
        log.debug("Converting from Input stream to user:" + userClass);
        try {
            return mapper.readValue(stream, userClass);
        } catch (IOException | NullPointerException e) {
            String error = "Error occurred when converting from Input stream to user: " + userClass;
            log.error(error);
            log.error(e.getMessage(), e);
            throw new Error(error);
        }
    }

    public static <T> List<T> jsonToObjectsList(String jsonString, Class<T[]> userClass, String wrapperField) {
        log.debug(String.format(CONVERT_LIST_MSG, prettyPrint(jsonString), userClass));
        MapType mapType = JsonConverter.constructMapTypeToValue(Object.class);

        try {
            HashMap<String, Object> map = mapper.readValue(jsonString, mapType);
            Object obj = map.get(wrapperField);

            T[] objects = mapper.convertValue(obj, userClass);
            return Arrays.asList(objects);
        } catch (IOException | NullPointerException e) {
            String error = String.format(CONVERT_LIST_ERR, prettyPrint(jsonString), userClass);
            log.error(error);
            log.error(e.getMessage(), e);
            throw new RuntimeException(error);
        }
    }

    @SuppressWarnings("unchecked")
    public static LinkedHashMap<String, Object> toMap(String json) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(json, LinkedHashMap.class);
            return map;
        } catch (IOException e) {
            log.error(e);
        }
        return map;
    }

    public Map<String, String> flattenJsonMap(String json) {
        Map<String, String> map = new HashMap<>();
        try {
            addKeys("", new ObjectMapper().readTree(json), map);
        } catch (IOException e) {
            log.error(e);
        }
        return map;
    }

    private void addKeys(String currentPath, JsonNode jsonNode, Map<String, String> map) {
        if (jsonNode.isObject()) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            Iterator<Map.Entry<String, JsonNode>> iter = objectNode.getFields();
            String pathPrefix = currentPath.isEmpty() ? "" : currentPath + ".";

            while (iter.hasNext()) {
                Map.Entry<String, JsonNode> entry = iter.next();
                addKeys(pathPrefix + entry.getKey(), entry.getValue(), map);
            }
        } else if (jsonNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            for (int i = 0; i < arrayNode.size(); i++) {
                addKeys(currentPath + "[" + i + "]", arrayNode.get(i), map);
            }
        } else if (jsonNode.isValueNode()) {
            ValueNode valueNode = (ValueNode) jsonNode;
            map.put(currentPath, valueNode.asText());
        }
    }
}
