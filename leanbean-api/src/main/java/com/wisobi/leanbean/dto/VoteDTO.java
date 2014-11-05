package com.wisobi.leanbean.dto;

import java.io.Serializable;

/**
 * Created by bjork on 05/11/14.
 */
public class VoteDTO implements Serializable {

  private long id;
  private long userId;
  private long topicId;

  public VoteDTO() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getTopicId() {
    return topicId;
  }

  public void setTopicId(long topicId) {
    this.topicId = topicId;
  }
}
