package com.wisobi.leanbean;

import java.util.Arrays;

/**
 * Created by bjork on 12/12/14.
 */
public class MainClass {

  public static void main(String[] args) {
    long[] ids = {1, 4, 7};

    System.out.println(Arrays.toString(ids));
    System.out.println(MainClass.arrayToString(ids));
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
