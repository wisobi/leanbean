package com.wisobi.leanbean.dto;

import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.Device;
import com.wisobi.leanbean.jpa.entity.Vote;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by bjork on 05/11/14.
 */
public class DAO2DTOMapper {

  public static MeetingViewTO mapMeeting(Meeting meeting) {
    MeetingViewTO meetingViewTO = new MeetingViewTO();
    meetingViewTO.setId(meeting.getId());
    meetingViewTO.setTitle(meeting.getTitle());
    meetingViewTO.setDuration(meeting.getDuration());
    meetingViewTO.setStartDateTime(meeting.getStartDateTime());

    meetingViewTO.setDevice(mapDevice(meeting.getDevice()));
    meetingViewTO.setTopics(mapTopics(meeting.getTopics()));

    return meetingViewTO;
  }

  public static DeviceTO mapDevice(Device device) {
    DeviceTO deviceTO = new DeviceTO();
    deviceTO.setId(device.getId());
    deviceTO.setCordova(device.getCordova());
    deviceTO.setModel(device.getModel());
    deviceTO.setPlatform(device.getPlatform());
    deviceTO.setAlias(device.getAlias());
    deviceTO.setUuid(device.getUuid());
    deviceTO.setVersion(device.getVersion());
    return deviceTO;
  }

  public static TopicViewTO mapTopic(Topic topic) {
    TopicViewTO topicViewTO = new TopicViewTO();
    topicViewTO.setId(topic.getId());
    topicViewTO.setTitle(topic.getTitle());
    topicViewTO.setPitch(topic.getPitch());

    topicViewTO.setDevice(mapDevice(topic.getDevice()));
    topicViewTO.setVotes(mapVotes(topic.getVotes()));

    return topicViewTO;
  }

  public static Set<TopicViewTO> mapTopics(Set<Topic> topics) {
    Set<TopicViewTO> topicViewTOs = new TreeSet<TopicViewTO>(TopicViewTO.TopicVoteComparator);
    for(Topic topic : topics) {
      topicViewTOs.add(mapTopic(topic));
    }
    return topicViewTOs;
  }

  public static VoteViewTO mapVote(Vote vote) {
    VoteViewTO voteViewTO = new VoteViewTO();
    voteViewTO.setId(vote.getId());
    voteViewTO.setDevice(mapDevice(vote.getDevice()));
    return voteViewTO;
  }

  public static Set<VoteViewTO> mapVotes(Set<Vote> votes) {
    Set<VoteViewTO> voteViewTOs = new HashSet<VoteViewTO>(votes.size());
    for(Vote vote : votes) {
      voteViewTOs.add(mapVote(vote));
    }
    return voteViewTOs;
  }

}
