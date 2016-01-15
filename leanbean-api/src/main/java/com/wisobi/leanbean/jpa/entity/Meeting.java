package com.wisobi.leanbean.jpa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Created by bjork on 25/08/14.
 */
@Entity
@Table(name = "meeting")
public class Meeting implements Serializable {

  private long id;
  private String title;
  private Set<Topic> topics = new HashSet<Topic>();
  private Set<Vote> votes = new HashSet<Vote>();
  private Device device;
  private int duration;
  private Date startDateTime;
  private Date created;
  private Date updated;

  public Meeting() {
  }

  public Meeting(String title, Device device) {
    this.title = title;
    this.device = device;
  }


  public Meeting(String title, Device device, int duration, Date startDateTime) {
    this.title = title;
    this.device = device;
    this.duration = duration;
    this.startDateTime = startDateTime;
  }

  @Id
  @GeneratedValue
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

  @OneToMany(mappedBy = "meeting", fetch = FetchType.EAGER)
  @JsonManagedReference
  public Set<Topic> getTopics() {
    //Set<Topic> sortedTopics = new TreeSet<Topic>(Topic.TopicVoteComparator);
    //sortedTopics.addAll(this.topics);
    //return sortedTopics;
    return this.topics;
  }

  public void setTopics(Set<Topic> topics) {
    this.topics = topics;
  }

  public void addTopic(Topic topic) {
    this.topics.add(topic);
    topic.setMeeting(this);
  }

  @OneToMany(mappedBy = "meeting", fetch = FetchType.EAGER)
  public Set<Vote> getVotes() {
    return votes;
  }

  public void setVotes(Set<Vote> votes) {
    this.votes = votes;
  }

  public void addVote(Vote vote) {
    this.votes.add(vote);
    vote.setMeeting(this);
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "device_id")
  public Device getDevice() {
    return device;
  }

  public void setDevice(Device device) {
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
