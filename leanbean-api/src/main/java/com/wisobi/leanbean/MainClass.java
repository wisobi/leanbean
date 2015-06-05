package com.wisobi.leanbean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by bjork on 12/12/14.
 */
public class MainClass {

  public static void main(String[] args) {
    long[] ids = {1, 4, 7};

    System.out.println(Arrays.toString(ids));
    System.out.println(MainClass.arrayToString(ids));

    Hashids hashids = new Hashids("LeanBean");
    int x = 2;
    for(int i = 1; i < 1000000; i++) {
        String hash = hashids.encode(i);
        if(hash.length() == x) {
            System.out.println(i + " " + hash + " " + hashids.decode(hash)[0]);
            x++;
        }
        if(hash.length() < x - 1) {
            System.out.println(i + " " + hash + " " + hashids.decode(hash)[0]);
        }
        if(x == 7) {
            break;
        }
    }
  }

    private static void shuffleArray(char[] array)
    {
        int index;
        char temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

  public static String arrayToString(long array[])
  {
    if (array.length == 0) return "";
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < array.length; ++i)
    {
      sb.append(",'").append(array[i]).append("'");
    }
    return sb.substring(1);
  }
}
