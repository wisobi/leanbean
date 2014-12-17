package com.wisobi.leanbean.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

}
