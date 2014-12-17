package com.wisobi.leanbean.builder;

import com.wisobi.leanbean.jpa.entity.Device;
import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Vote;

/**
 * Created by bjork on 15/12/14.
 */
public class VoteBuilder {

  private Vote vote;

  public VoteBuilder() {
    this.vote = new Vote();
  }

  public VoteBuilder device(Device device) {
    this.vote.setDevice(device);
    return this;
  }

  public VoteBuilder meeting(Meeting meeting) {
    this.vote.setMeeting(meeting);
    return this;
  }

  public VoteBuilder topicIds(String topicIds) {
    this.vote.setTopicIds(topicIds);
    return this;
  }

  public Vote build() {
    Vote returnVote = this.vote;
    this.vote = new Vote();
    return returnVote;
  }

}
