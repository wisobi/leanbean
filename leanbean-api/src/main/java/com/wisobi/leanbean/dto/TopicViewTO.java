package com.wisobi.leanbean.dto;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;

/**
 * Created by bjork on 05/11/14.
 */
public class TopicViewTO implements Serializable {

  private long id;
  private UserViewTO user;
  private String title;
  private String pitch;
  private Set<VoteViewTO> votes;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public UserViewTO getUser() {
    return user;
  }

  public void setUser(UserViewTO user) {
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

  public Set<VoteViewTO> getVotes() {
    return votes;
  }

  public void setVotes(Set<VoteViewTO> votes) {
    this.votes = votes;
  }

  public int numVotes() {
    return this.votes == null ? 0 : this.votes.size();
  }

  public static Comparator<TopicViewTO> TopicVoteComparator = new Comparator<TopicViewTO>() {

    public int compare(TopicViewTO t1, TopicViewTO t2) {
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
