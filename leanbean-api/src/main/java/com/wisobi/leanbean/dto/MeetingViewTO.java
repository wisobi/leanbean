package com.wisobi.leanbean.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjork on 05/11/14.
 */
public class MeetingViewTO implements Serializable {

  private long id;
  private String title;
  private Set<TopicViewTO> topics = new HashSet<TopicViewTO>();
  private DeviceTO device;
  private int duration;
  private Date startDateTime;

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

  public Set<TopicViewTO> getTopics() {
    return topics;
  }

  public void setTopics(Set<TopicViewTO> topics) {
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
