package com.wisobi.leanbean.dto;

import com.wisobi.leanbean.LeanBeanUtil;
import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.Device;
import com.wisobi.leanbean.jpa.entity.Vote;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    meeting.setId(topicTO.getMeetingId());
    topic.setMeeting(meeting);

    return topic;
  }

  public static Vote mapVote(VoteTO voteTO) {
    Vote vote = new Vote();

    Device device = new Device();
    device.setId(voteTO.getDeviceId());
    vote.setDevice(device);

    Meeting meeting = new Meeting();
    meeting.setId(voteTO.getMeetingId());
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
