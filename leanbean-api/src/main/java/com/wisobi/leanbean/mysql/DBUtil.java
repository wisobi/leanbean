package com.wisobi.leanbean.mysql;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by bjork on 27/08/14.
 */
public class DBUtil {

  public static void close(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        // Do nothing
      }
    }
  }

  public static void close(Statement statement) {
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        // Do nothing
      }
    }
  }

  public static void close(ResultSet resultSet) {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        // Do nothing
      }
    }
  }

  public static String readQuery(String resource) {
    String query = null;
    try {
      InputStream input = DBUtil.class.getResourceAsStream(resource);
      query = IOUtils.toString(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return query;
  }
}