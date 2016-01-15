package com.wisobi.leanbean.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Created by bjork on 25/08/14.
 */
@Entity
@Table(name = "topic")
public class Topic implements Serializable {

  private long id;
  private Device device;
  private String title;
  private String pitch;
  private Meeting meeting;
  private Date created;
  private Date updated;

  public Topic() {
  }

  public Topic(String title, String pitch, Meeting meeting, Device device) {
    this.device = device;
    this.title = title;
    this.pitch = pitch;
    this.meeting = meeting;
  }

  @Id
  @GeneratedValue
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "device_id")
  public Device getDevice() {
    return device;
  }

  public void setDevice(Device device) {
    this.device = device;
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

  @ManyToOne
  @JoinColumn(name = "meeting_id")
  @JsonBackReference
  public Meeting getMeeting() {
    return meeting;
  }

  public void setMeeting(Meeting meeting) {
    this.meeting = meeting;
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
