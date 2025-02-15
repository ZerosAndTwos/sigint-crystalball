package utils;

import json.JsonConverter;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static Map<String, Integer> multiplier;
    static {
        multiplier = new HashMap<>();
        multiplier.put("h", 3600);
        multiplier.put("m", 60);
        multiplier.put("s", 1);
    }

    private static Logger log = Logger.getLogger(Parser.class);

    public static int getDurationFromString(String text) {
        Integer sum = 0;
        for (String key : multiplier.keySet()) {
            Pattern pattern = Pattern.compile(String.format("[0-9]*%s", key));
            Matcher matcher = pattern.matcher(text);
            Boolean finded = matcher.find();
            if (!finded) {
                log.debug(String.format("Unable to parse %s from time string", key));
                continue;
            }
            String value = matcher.group().replace(String.format("%s", key), "");
            sum += Integer.parseInt(value) * multiplier.get(key);
        }

        return sum;
    }

    public static Set<String> stringToSet(String list) {
        Set<String> strings = new HashSet<>();
        for (String value : list.split(",")) {
            strings.add(value.trim());
        }
        return strings;
    }

    public static String setToString(Set<String> list) {
        return list
                .toString()
                .replace("[", "")
                .replace("]", "");
    }

    public static String entityToString(Object entity) {
        return entity.getClass().getName() + ": " + JsonConverter.toJsonString(entity);
    }

}
