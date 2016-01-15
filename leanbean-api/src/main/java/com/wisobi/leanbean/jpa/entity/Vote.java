package com.wisobi.leanbean.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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
  private Date created;
  private Date updated;

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

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created", nullable = false, updatable=false)
  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated", nullable = false)
  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  @PrePersist
  protected void onCreate() {
    Date date = new Date();
    setUpdated(date);
    setCreated(date);
  }

  @PreUpdate
  protected void onUpdate() {
    setUpdated(new Date());
  }
}
