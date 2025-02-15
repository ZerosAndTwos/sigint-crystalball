package utils;


public class CollectionUtils {

    public static String[] trimSpaces(String[] arr) {

        for (int i=0;i< arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        return arr;

    }
}
