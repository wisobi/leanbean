package com.wisobi.leanbean.dto;

import java.io.Serializable;

/**
 * Created by bjork on 05/11/14.
 */
public class VoteViewTO implements Serializable {

  private long id;
  private DeviceTO device;

  public VoteViewTO() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public DeviceTO getDevice() { return device; }

  public void setDevice(DeviceTO device) { this.device = device; }
}