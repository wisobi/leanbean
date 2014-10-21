package com.wisobi.leanbean.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
@Table(name = "meeting")
public class Meeting implements Serializable {

  private long id;
  private String title;
  private Set<Topic> topics = new HashSet<Topic>();
  private User user;
  private int duration;
  private Date startDateTime;

  public Meeting() {
  }

  public Meeting(String title, User user) {
    this.title = title;
    this.user = user;
  }


  public Meeting(String title, User user, int duration, Date startDateTime) {
    this.title = title;
    this.user = user;
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
    Set<Topic> sortedTopics = new TreeSet<Topic>(Topic.TopicVoteComparator);
    sortedTopics.addAll(this.topics);
    return sortedTopics;
  }

  public void setTopics(Set<Topic> topics) {
    this.topics = topics;
  }

  public void addTopic(Topic topic) {
    this.topics.add(topic);
    topic.setMeeting(this);
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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
