package com.wisobi.leanbean.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 * Created by bjork on 25/08/14.
 */
@Entity
@Table(name = "topic")
public class Topic implements Serializable {

  private long id;
  private User user;
  private String title;
  private String pitch;
  private Set<Vote> votes;
  private Meeting meeting;

  public Topic() {
  }

  public Topic(String title, String pitch, Meeting meeting, User user) {
    this.user = user;
    this.title = title;
    this.pitch = pitch;
    this.meeting = meeting;
    meeting.addTopic(this);
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
  @JoinColumn(name = "user_id")
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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

  @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER)
  @JsonManagedReference
  public Set<Vote> getVotes() {
    return votes;
  }

  public void setVotes(Set<Vote> votes) {
    this.votes = votes;
  }

  public void addVote(Vote vote) {
    if (this.votes == null) {
      this.votes = new HashSet<Vote>();
    }
    this.votes.add(vote);
    vote.setTopic(this);
  }

  public int numVotes() {
    return this.votes == null ? 0 : this.votes.size();
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

  public static Comparator<Topic> TopicVoteComparator = new Comparator<Topic>() {

    public int compare(Topic t1, Topic t2) {
      if (t1.getId() == t2.getId()) {
        return 0;
      }
      if (t1.numVotes() > t2.numVotes()) {
        return -1;
      } else  {
        return 1;
      }
    }

  };

}
