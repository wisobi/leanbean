package com.wisobi.leanbean.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by bjork on 05/11/14.
 */
public class MeetingViewTO implements Serializable {

  private String type = "meetingview";
  private String id;
  private String title;
  private SortedSet<TopicViewTO> topics;
  private DeviceTO device;
  private int duration;
  private Date startDateTime;

  public String getType() { return type; }

  public void setType(String type) { this.type = type; }

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

  public SortedSet<TopicViewTO> getTopics() {
    return topics;
  }

  public void setTopics(SortedSet<TopicViewTO> topics) {
    this.topics = topics;
  }

  public DeviceTO getDevice() {
    return device;
  }

  public void setDevice(DeviceTO device) {
    this.device = device;
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
