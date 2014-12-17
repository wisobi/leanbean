package com.wisobi.leanbean.dto;


import java.io.Serializable;

/**
 * Created by bjork on 05/11/14.
 */
public class DeviceTO implements Serializable {

  private long id;
  private String alias;
  private String uuid;

  public DeviceTO() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

}
