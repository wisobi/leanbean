package com.wisobi.leanbean.dto;

import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.User;
import com.wisobi.leanbean.jpa.entity.Vote;

/**
 * Created by bjork on 05/11/14.
 */
public class DTO2DAOMapper {

  public static Meeting mapMeeting(MeetingTO meetingTO) {
    Meeting meeting = new Meeting();
    meeting.setId(meetingTO.getId());
    meeting.setDuration(meetingTO.getDuration());
    meeting.setStartDateTime(meetingTO.getStartDateTime());
    meeting.setTitle(meetingTO.getTitle());

    User user = new User();
    user.setId(meetingTO.getUserId());
    meeting.setUser(user);

    return meeting;
  }

  public static Topic mapTopic(TopicTO topicTO) {
    Topic topic = new Topic();
    topic.setId(topicTO.getId());
    topic.setTitle(topicTO.getTitle());
    topic.setPitch(topicTO.getPitch());

    User user = new User();
    user.setId(topicTO.getUserId());
    topic.setUser(user);

    Meeting meeting = new Meeting();
    meeting.setId(topicTO.getMeetingId());
    topic.setMeeting(meeting);

    return topic;
  }

  public static Vote mapVote(VoteTO voteTO) {
    Vote vote = new Vote();
    vote.setId(voteTO.getId());

    User user = new User();
    user.setId(voteTO.getUserId());
    vote.setUser(user);

    Topic topic = new Topic();
    topic.setId(voteTO.getTopicId());
    vote.setTopic(topic);

    return vote;
  }

  public static User mapUser(UserTO userTO) {
    User user = new User();
    user.setId(userTO.getId());
    user.setName(userTO.getName());
    user.setEmail(userTO.getEmail());
    return user;
  }

}
