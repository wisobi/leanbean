package com.wisobi.leanbean.jpa.entity;

import java.io.Serializable;

/**
 * Created by bjork on 12/12/14.
 */
public class VoteId implements Serializable {

  private Meeting meeting;
  private Device device;

  public VoteId() {}

  public VoteId(Meeting meeting, Device device) {
    this.meeting = meeting;
    this.device = device;
  }

  public Meeting getMeeting() {
    return meeting;
  }

  public void setMeeting(Meeting meeting) {
    this.meeting = meeting;
  }

  public Device getDevice() {
    return device;
  }

  public void setDevice(Device device) {
    this.device = device;
  }

  @Override
  public int hashCode() {
    final int prime = 37;
    int result = 1;
    result = prime * result + (int)(meeting.getId()^ (meeting.getId() >>> 32));
    result = prime * result + (int)(device.getId() ^ (device.getId() >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    VoteId other = (VoteId) obj;
    if (meeting.getId() == other.getMeeting().getId() && device.getId() == other.getDevice().getId()) {
      return true;
    } else {
      return false;
    }
  }

}
