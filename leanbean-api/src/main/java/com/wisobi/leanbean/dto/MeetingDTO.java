package com.wisobi.leanbean.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bjork on 05/11/14.
 */
public class MeetingDTO implements Serializable {

  private long id;
  private String title;
  private long userId;
  private int duration;
  private Date startDateTime;

  public MeetingDTO() {

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

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
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
