package com.wisobi.leanbean.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bjork on 05/11/14.
 */
public class MeetingTO implements Serializable {

  private String id;
  private String title;
  private long deviceId;
  private int duration;
  private Date startDateTime;

  public MeetingTO() {

  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public long getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(long deviceId) {
    this.deviceId = deviceId;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public Date getStartDateTime() {
    return startDateTime;
  }

  public void setStartDateTime(Date startDateTime) {
    this.startDateTime = startDateTime;
  }
}
