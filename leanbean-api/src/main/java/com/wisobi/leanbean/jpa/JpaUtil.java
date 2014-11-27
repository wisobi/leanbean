package com.wisobi.leanbean.jpa;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by bjork on 11/09/14.
 */
public class JpaUtil {

  private JpaUtil() {

  }

  private static class LazyHolder {

    private static final EntityManagerFactory emf;

    private static Properties properties;

    static {
      emf = Persistence.createEntityManagerFactory("leanbean", properties);
    }
  }

  public static EntityManagerFactory getEntityManagerFactory() {
    return LazyHolder.emf;
  }

  public static EntityManagerFactory getEntityManagerFactory(Properties properties) {
    LazyHolder.properties = properties;
    return LazyHolder.emf;
  }

}
