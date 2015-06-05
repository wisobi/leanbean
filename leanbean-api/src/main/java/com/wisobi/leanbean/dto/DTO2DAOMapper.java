package com.wisobi.leanbean.dto;

import com.wisobi.leanbean.LeanBeanUtil;
import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.Device;
import com.wisobi.leanbean.jpa.entity.Vote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjork on 05/11/14.
 */
public class DTO2DAOMapper {

    final static Logger logger = LoggerFactory.getLogger(DTO2DAOMapper.class);

  public static Meeting mapMeeting(MeetingTO meetingTO) {
    Meeting meeting = new Meeting();
    if(meetingTO.getId() != null) {
        meeting.setId(LeanBeanUtil.idDecode(meetingTO.getId()));
    }
    meeting.setDuration(meetingTO.getDuration());
    meeting.setStartDateTime(meetingTO.getStartDateTime());
    meeting.setTitle(meetingTO.getTitle());

    Device device = new Device();
    device.setId(meetingTO.getDeviceId());
    meeting.setDevice(device);

    return meeting;
  }

  public static Topic mapTopic(TopicTO topicTO) {
    Topic topic = new Topic();
    topic.setId(topicTO.getId());
    topic.setTitle(topicTO.getTitle());
    topic.setPitch(topicTO.getPitch());

    Device device = new Device();
    device.setId(topicTO.getDeviceId());
    topic.setDevice(device);

    Meeting meeting = new Meeting();
    long meetingId = LeanBeanUtil.idDecode(topicTO.getMeetingId());
    meeting.setId(meetingId);
    topic.setMeeting(meeting);

    return topic;
  }

  public static Vote mapVote(VoteTO voteTO) {
    Vote vote = new Vote();

    Device device = new Device();
    device.setId(voteTO.getDeviceId());
    vote.setDevice(device);

    Meeting meeting = new Meeting();
    long meetingId = LeanBeanUtil.idDecode(voteTO.getMeetingId());
    meeting.setId(meetingId);
    vote.setMeeting(meeting);

    String topicIds = LeanBeanUtil.arrayToString(voteTO.getTopicIds());
    vote.setTopicIds(topicIds);

    return vote;
  }

  public static Device mapDevice(DeviceTO deviceTO) {
    Device device = new Device();
    device.setId(deviceTO.getId());
    device.setAlias(deviceTO.getAlias());
    device.setUuid(deviceTO.getUuid());
    return device;
  }

}
