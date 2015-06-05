package com.wisobi.leanbean.dto;

import java.io.Serializable;

/**
 * Created by bjork on 05/11/14.
 */
public class TopicTO implements Serializable {

  private long id;
  private long deviceId;
  private String title;
  private String pitch;
  private String meetingId;

  public TopicTO() {

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPitch() {
    return pitch;
  }

  public void setPitch(String pitch) {
    this.pitch = pitch;
  }

  public String getMeetingId() {
    return meetingId;
  }

  public void setMeetingId(String meetingId) {
    this.meetingId = meetingId;
  }
}
