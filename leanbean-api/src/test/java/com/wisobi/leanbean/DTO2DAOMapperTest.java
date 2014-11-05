package com.wisobi.leanbean;

import com.wisobi.leanbean.dto.DTO2DAOMapper;
import com.wisobi.leanbean.dto.MeetingDTO;
import com.wisobi.leanbean.dto.TopicDTO;
import com.wisobi.leanbean.dto.UserDTO;
import com.wisobi.leanbean.dto.VoteDTO;
import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.User;
import com.wisobi.leanbean.jpa.entity.Vote;

import org.junit.Test;

import java.util.Date;
import static org.junit.Assert.assertEquals;

/**
 * Created by bjork on 05/11/14.
 */
public class DTO2DAOMapperTest {

  @Test
  public void testMapMeeting() {

    long id = 1;
    String title = "Title";
    Date startDateTime = new Date(System.currentTimeMillis());
    int duration = 60;
    long userId = 2;

    MeetingDTO meetingDTO = new MeetingDTO();
    meetingDTO.setId(id);
    meetingDTO.setTitle(title);
    meetingDTO.setStartDateTime(startDateTime);
    meetingDTO.setDuration(duration);
    meetingDTO.setUserId(userId);

    Meeting meeting = DTO2DAOMapper.mapMeeting(meetingDTO);

    assertEquals(id, meeting.getId());
    assertEquals(title, meeting.getTitle());
    assertEquals(startDateTime, meeting.getStartDateTime());
    assertEquals(duration, meeting.getDuration());
    assertEquals(userId, meeting.getUser().getId());
  }

  @Test
  public void testMapTopic() {

    long id = 1;
    String title = "Title";
    String pitch = "Pitch";
    long userId = 2;
    long meetingId = 3;

    TopicDTO topicDTO = new TopicDTO();
    topicDTO.setId(id);
    topicDTO.setTitle(title);
    topicDTO.setPitch(pitch);
    topicDTO.setUserId(userId);
    topicDTO.setMeetingId(meetingId);

    Topic topic = DTO2DAOMapper.mapTopic(topicDTO);

    assertEquals(id, topic.getId());
    assertEquals(title, topic.getTitle());
    assertEquals(pitch, topic.getPitch());
    assertEquals(userId, topic.getUser().getId());
    assertEquals(meetingId, topic.getMeeting().getId());
  }

  @Test
  public void testMapUser() {
    long id = 1;
    String name = "Name";
    String email = "email@wisobi.com";

    UserDTO userDTO = new UserDTO();
    userDTO.setId(id);
    userDTO.setName(name);
    userDTO.setEmail(email);

    User user = DTO2DAOMapper.mapUser(userDTO);

    assertEquals(id, user.getId());
    assertEquals(name, user.getName());
    assertEquals(email, user.getEmail());
  }

  @Test
  public void testMapVote() {
    long id = 1;
    long topicId = 2;
    long userId = 3;

    VoteDTO voteDTO = new VoteDTO();
    voteDTO.setId(id);
    voteDTO.setTopicId(topicId);
    voteDTO.setUserId(userId);

    Vote vote = DTO2DAOMapper.mapVote(voteDTO);

    assertEquals(id, vote.getId());
    assertEquals(topicId, vote.getTopic().getId());
    assertEquals(userId, vote.getUser().getId());
  }

}
