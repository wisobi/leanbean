package com.wisobi.leanbean.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by bjork on 29/08/14.
 */
@Entity
@IdClass(VoteId.class)
@Table(name = "vote")
public class Vote implements Serializable {

  private Device device;

  private Meeting meeting;

  private String topicIds;

  public Vote() {
  }

  public Vote(Device device, Meeting meeting, String topicIds) {
    this.device = device;
    this.meeting = meeting;
    this.topicIds = topicIds;
  }

  @Id
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "device_id")
  public Device getDevice() {
    return device;
  }

  public void setDevice(Device device) {
    this.device = device;
  }

  @Id
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "meeting_id")
  @JsonBackReference
  public Meeting getMeeting() {
    return meeting;
  }

  public void setMeeting(Meeting meeting) {
    this.meeting = meeting;
  }

  public String getTopicIds() {
    return topicIds;
  }

  public void setTopicIds(String topicIds) {
    this.topicIds = topicIds;
  }
}
