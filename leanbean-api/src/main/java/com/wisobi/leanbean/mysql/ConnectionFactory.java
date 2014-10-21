package com.wisobi.leanbean.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by bjork on 26/08/14.
 */
public class ConnectionFactory {
  //static reference to itself
  private static ConnectionFactory instance = new ConnectionFactory();
  public Properties props = new Properties();

  //private constructor
  private ConnectionFactory() {
    InputStream stream = ConnectionFactory.class.getResourceAsStream("/leanbean.properties");
    try {
      props.load(stream);
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      String dbDriver = props.getProperty("DB_DRIVER");
      Class.forName(dbDriver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private Connection createConnection() {
    Connection connection = null;
    try {
      String url = props.getProperty("DB_URL");
      String user = props.getProperty("DB_USER");
      String pass = props.getProperty("DB_PASS");
      connection = DriverManager.getConnection(url, user, pass);
    } catch (SQLException e) {
      System.out.println("ERROR: Unable to Connect to Database.");
    }
    return connection;
  }

  public static Connection getConnection() {
    return instance.createConnection();
  }
}