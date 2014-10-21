package com.wisobi.leanbean;

import com.wisobi.leanbean.entity.Meeting;
import com.wisobi.leanbean.entity.Topic;
import com.wisobi.leanbean.entity.User;
import com.wisobi.leanbean.entity.Vote;

/**
 * Created by bjork on 25/08/14.
 */
public interface LeanBeanDao {

  public Meeting findByMeetingId(long meetingId);
  public User findByUserId(long userId);
  public void addTopic(Topic topic);
  public void addUser(User user);
  public void addMeeting(Meeting meeting);
  public void addVote(Vote vote);

}
