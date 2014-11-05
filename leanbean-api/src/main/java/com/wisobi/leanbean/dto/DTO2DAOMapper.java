package com.wisobi.leanbean.dto;

import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.User;
import com.wisobi.leanbean.jpa.entity.Vote;

/**
 * Created by bjork on 05/11/14.
 */
public class DTO2DAOMapper {

  public static Meeting mapMeeting(MeetingDTO meetingDTO) {
    Meeting meeting = new Meeting();
    meeting.setId(meetingDTO.getId());
    meeting.setDuration(meetingDTO.getDuration());
    meeting.setStartDateTime(meetingDTO.getStartDateTime());
    meeting.setTitle(meetingDTO.getTitle());

    User user = new User();
    user.setId(meetingDTO.getUserId());
    meeting.setUser(user);

    return meeting;
  }

  public static Topic mapTopic(TopicDTO topicDTO) {
    Topic topic = new Topic();
    topic.setId(topicDTO.getId());
    topic.setTitle(topicDTO.getTitle());
    topic.setPitch(topicDTO.getPitch());

    User user = new User();
    user.setId(topicDTO.getUserId());
    topic.setUser(user);

    Meeting meeting = new Meeting();
    meeting.setId(topicDTO.getMeetingId());
    topic.setMeeting(meeting);

    return topic;
  }

  public static Vote mapVote(VoteDTO voteDTO) {
    Vote vote = new Vote();
    vote.setId(voteDTO.getId());

    User user = new User();
    user.setId(voteDTO.getUserId());
    vote.setUser(user);

    Topic topic = new Topic();
    topic.setId(voteDTO.getTopicId());
    vote.setTopic(topic);

    return vote;
  }

  public static User mapUser(UserDTO userDTO) {
    User user = new User();
    user.setId(userDTO.getId());
    user.setName(userDTO.getName());
    user.setEmail(userDTO.getEmail());
    return user;
  }

}
