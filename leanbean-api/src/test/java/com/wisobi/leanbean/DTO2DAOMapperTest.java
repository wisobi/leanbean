package com.wisobi.leanbean;

import com.wisobi.leanbean.dto.DTO2DAOMapper;
import com.wisobi.leanbean.dto.MeetingTO;
import com.wisobi.leanbean.dto.TopicTO;
import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;

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
    long deviceId = 2;

    MeetingTO meetingTO = new MeetingTO();
    meetingTO.setId(LeanBeanUtil.idEncode(id));
    meetingTO.setTitle(title);
    meetingTO.setStartDateTime(startDateTime);
    meetingTO.setDuration(duration);
    meetingTO.setDeviceId(deviceId);

    Meeting meeting = DTO2DAOMapper.mapMeeting(meetingTO);

    assertEquals(id, meeting.getId());
    assertEquals(title, meeting.getTitle());
    assertEquals(startDateTime, meeting.getStartDateTime());
    assertEquals(duration, meeting.getDuration());
    assertEquals(deviceId, meeting.getDevice().getId());
  }

  @Test
  public void testMapTopic() {

    long id = 1;
    String title = "Title";
    String pitch = "Pitch";
    long deviceId = 2;
    long meetingId = 3;

    TopicTO topicTO = new TopicTO();
    topicTO.setId(id);
    topicTO.setTitle(title);
    topicTO.setPitch(pitch);
    topicTO.setDeviceId(deviceId);
    topicTO.setMeetingId(LeanBeanUtil.idEncode(meetingId));

    Topic topic = DTO2DAOMapper.mapTopic(topicTO);

    assertEquals(id, topic.getId());
    assertEquals(title, topic.getTitle());
    assertEquals(pitch, topic.getPitch());
    assertEquals(deviceId, topic.getDevice().getId());
    assertEquals(meetingId, topic.getMeeting().getId());
  }
/*
  @Test
  public void testMapUser() {

    long id = 1;
    String username;
    String model;
    String cordova;
    String platform;
    String uuid;
    String version;

    DeviceTO deviceTO = new DeviceTO();
    deviceTO.setId(id);
    deviceTO.setName(name);
    deviceTO.setEmail(email);

    Device device = DTO2DAOMapper.mapUser(deviceTO);

    assertEquals(id, device.getId());
    assertEquals(name, device.getName());
    assertEquals(email, device.getEmail());
  }

  @Test
  public void testMapVote() {
    long id = 1;
    long topicId = 2;
    long userId = 3;

    VoteTO voteTO = new VoteTO();
    voteTO.setId(id);
    voteTO.setTopicId(topicId);
    voteTO.setDeviceId(userId);

    Vote vote = DTO2DAOMapper.mapVote(voteTO);

    assertEquals(id, vote.getId());
    assertEquals(topicId, vote.getTopic().getId());
    assertEquals(userId, vote.getDevice().getId());
  }
  */

}
