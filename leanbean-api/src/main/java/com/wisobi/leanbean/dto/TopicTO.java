package com.wisobi.leanbean.dto;

import java.io.Serializable;

/**
 * Created by bjork on 05/11/14.
 */
public class TopicTO implements Serializable {

  private long id;
  private long userId;
  private String title;
  private String pitch;
  private long meetingId;

  public TopicTO() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
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

  public long getMeetingId() {
    return meetingId;
  }

  public void setMeetingId(long meetingId) {
    this.meetingId = meetingId;
  }
}
