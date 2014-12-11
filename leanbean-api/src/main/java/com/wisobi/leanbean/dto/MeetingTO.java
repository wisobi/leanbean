package com.wisobi.leanbean.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bjork on 05/11/14.
 */
public class MeetingTO implements Serializable {

  public long id;
  public String title;
  public long userId;
  public int duration;
  public Date startDateTime;

  public MeetingTO() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public long getDeviceId() {
    return userId;
  }

  public void setDeviceId(long deviceId) {
    this.userId = deviceId;
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
