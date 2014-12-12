package com.wisobi.leanbean.dto;

import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.Device;
import com.wisobi.leanbean.jpa.entity.Vote;

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

  public static Set<Vote> mapVotes(VoteTO voteTO) {
    Set<Vote> votes = new HashSet<Vote>();

    for(long topicId : voteTO.getTopicIds()) {
      Vote vote = new Vote();
      vote.setId(voteTO.getId());

      Device device = new Device();
      device.setId(voteTO.getDeviceId());
      vote.setDevice(device);

      Topic topic = new Topic();
      topic.setId(topicId);
      vote.setTopic(topic);

      votes.add(vote);
    }

    return votes;
  }

  public static Device mapDevice(DeviceTO deviceTO) {
    Device device = new Device();
    device.setId(deviceTO.getId());
    device.setCordova(deviceTO.getCordova());
    device.setModel(deviceTO.getModel());
    device.setPlatform(deviceTO.getPlatform());
    device.setAlias(deviceTO.getAlias());
    device.setUuid(deviceTO.getUuid());
    device.setVersion(deviceTO.getVersion());
    return device;
  }

}
