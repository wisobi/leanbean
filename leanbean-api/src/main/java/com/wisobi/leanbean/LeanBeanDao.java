package com.wisobi.leanbean;

import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.User;
import com.wisobi.leanbean.jpa.entity.Vote;

/**
 * Created by bjork on 25/08/14.
 */
public interface LeanBeanDao {

  public Meeting findByMeetingId(long meetingId);
  public User findByUserId(long userId);
  public Topic findByTopicId(long topicId);
  public void addTopic(Topic topic) throws IllegalArgumentException;
  public void addUser(User user) throws IllegalArgumentException;
  public void addMeeting(Meeting meeting) throws IllegalArgumentException;
  public void addVote(Vote vote) throws IllegalArgumentException;
  public void close() throws Exception;

}
