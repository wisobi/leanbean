package com.wisobi.leanbean;

import com.wisobi.leanbean.dto.DTO2DAOMapper;
import com.wisobi.leanbean.dto.MeetingTO;
import com.wisobi.leanbean.dto.TopicTO;
import com.wisobi.leanbean.dto.UserTO;
import com.wisobi.leanbean.dto.VoteTO;
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

    MeetingTO meetingTO = new MeetingTO();
    meetingTO.setId(id);
    meetingTO.setTitle(title);
    meetingTO.setStartDateTime(startDateTime);
    meetingTO.setDuration(duration);
    meetingTO.setUserId(userId);

    Meeting meeting = DTO2DAOMapper.mapMeeting(meetingTO);

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

    TopicTO topicTO = new TopicTO();
    topicTO.setId(id);
    topicTO.setTitle(title);
    topicTO.setPitch(pitch);
    topicTO.setUserId(userId);
    topicTO.setMeetingId(meetingId);

    Topic topic = DTO2DAOMapper.mapTopic(topicTO);

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

    UserTO userTO = new UserTO();
    userTO.setId(id);
    userTO.setName(name);
    userTO.setEmail(email);

    User user = DTO2DAOMapper.mapUser(userTO);

    assertEquals(id, user.getId());
    assertEquals(name, user.getName());
    assertEquals(email, user.getEmail());
  }

  @Test
  public void testMapVote() {
    long id = 1;
    long topicId = 2;
    long userId = 3;

    VoteTO voteTO = new VoteTO();
    voteTO.setId(id);
    voteTO.setTopicId(topicId);
    voteTO.setUserId(userId);

    Vote vote = DTO2DAOMapper.mapVote(voteTO);

    assertEquals(id, vote.getId());
    assertEquals(topicId, vote.getTopic().getId());
    assertEquals(userId, vote.getUser().getId());
  }

}
