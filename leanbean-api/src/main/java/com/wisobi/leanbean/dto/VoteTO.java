package com.wisobi.leanbean.dto;

import java.io.Serializable;

/**
 * Created by bjork on 05/11/14.
 */
public class VoteTO implements Serializable {

  private long id;
  private long deviceId;
  private long meetingId;
  private long[] topicIds;

  public VoteTO() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getDeviceId() { return deviceId; }

  public void setDeviceId(long deviceId) { this.deviceId = deviceId; }

  public long getMeetingId() { return meetingId; }

  public void setMeetingId(long meetingId) { this.meetingId = meetingId; }

  public long[] getTopicIds() {
    return topicIds;
  }

  public void setTopicIds(long[] topicIds) {
    this.topicIds = topicIds;
  }
}
