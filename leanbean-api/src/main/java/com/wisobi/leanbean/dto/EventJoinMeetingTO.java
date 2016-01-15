package com.wisobi.leanbean.dto;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by bjork on 05/11/14.
 */
public class EventJoinMeetingTO implements Serializable {

  private long id;
  private long deviceId;
  private String meetingId;
  private String model;
  private String cordova;
  private String platform;
  private String uuid;
  private String version;
  private String manufacturer;
  private boolean virtual;
  private String serial;
  private Date created;

  public EventJoinMeetingTO() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(long deviceId) {
    this.deviceId = deviceId;
  }

  public String getMeetingId() {
    return meetingId;
  }

  public void setMeetingId(String meetingId) {
    this.meetingId = meetingId;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getCordova() {
    return cordova;
  }

  public void setCordova(String cordova) {
    this.cordova = cordova;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public boolean isVirtual() {
    return virtual;
  }

  public void setVirtual(boolean virtual) {
    this.virtual = virtual;
  }

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }
}
