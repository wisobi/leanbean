package com.wisobi.leanbean.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by bjork on 29/08/14.
 */
@Entity
@Table(name = "vote")
public class Vote implements Serializable {

  private long id;

  private Device device;

  private Topic topic;

  public Vote() {
  }

  public Vote(Device device, Topic topic) {
    this.device = device;
    this.topic = topic;
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

  @ManyToOne
  @JoinColumn(name = "topic_id")
  @JsonBackReference
  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }
}
