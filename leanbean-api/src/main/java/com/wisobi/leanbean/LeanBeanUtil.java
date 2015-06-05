package com.wisobi.leanbean;

/**
 * Created by bjork on 12/12/14.
 */
public class LeanBeanUtil {

    final private static Hashids hashids = new Hashids("LeanBean");

    public static String arrayToString(long array[]) {
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
            sb.append(",").append(array[i]).append("");
        }
        return sb.substring(1);
    }

    public static long[] stringToArray(String input) {
        String[] strings = input.split(",");
        long[] longs = new long[strings.length];
        for (int i = 0; i < strings.length; i++) {
            longs[i] = Long.parseLong(strings[i]);
        }
        return longs;
    }

    public static String idEncode(long id){
        return hashids.encode(id);
    }

    public static long idDecode(String hash){
        return hashids.decode(hash)[0];
    }

}
