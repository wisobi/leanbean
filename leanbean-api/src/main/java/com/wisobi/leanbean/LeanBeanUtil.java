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

    /**
     * Help method that generates a short hash from a long.
     *
     * @param id the long to encode to a hash
     * @return The hashed id as a string
     */
    public static String idEncode(long id){
        return hashids.encode(id);
    }

    /**
     * Help method that checks that the hashed id can be decoded.
     *
     * @param hash the hash to decode to an long
     * @return The actual id or -1 if hash cannot be decoded (e.g. asdf)
     */
    public static long idDecode(String hash){
        long[] ids = hashids.decode(hash);
        if(ids.length == 0) {
            return -1;
        }
        return ids[0];
    }

}
