package com.wisobi.leanbean.dto;

import com.wisobi.leanbean.Hashids;
import com.wisobi.leanbean.LeanBeanUtil;
import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.Device;
import com.wisobi.leanbean.jpa.entity.Vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by bjork on 05/11/14.
 */
public class DAO2DTOMapper {

  final static Logger logger = LoggerFactory.getLogger(DAO2DTOMapper.class);
  final static Hashids hashids = new Hashids("LeanBean");

  public static MeetingViewTO mapMeeting(Meeting meeting) {
    MeetingViewTO meetingViewTO = new MeetingViewTO();
    meetingViewTO.setId(hashids.encode(meeting.getId()));
    meetingViewTO.setTitle(meeting.getTitle());
    meetingViewTO.setDuration(meeting.getDuration());
    meetingViewTO.setStartDateTime(meeting.getStartDateTime());

    meetingViewTO.setDevice(mapDevice(meeting.getDevice()));
    meetingViewTO.setTopics(mapTopics(meeting.getTopics(), meeting.getVotes()));

    return meetingViewTO;
  }

  public static DeviceTO mapDevice(Device device) {
    DeviceTO deviceTO = new DeviceTO();
    deviceTO.setId(device.getId());
    deviceTO.setAlias(device.getAlias());
    deviceTO.setUuid(device.getUuid());
    return deviceTO;
  }

  public static TopicViewTO mapTopic(Topic topic, Set<Vote> votes) {
    TopicViewTO topicViewTO = new TopicViewTO();
    topicViewTO.setId(topic.getId());
    topicViewTO.setTitle(topic.getTitle());
    topicViewTO.setPitch(topic.getPitch());

    topicViewTO.setDevice(mapDevice(topic.getDevice()));

    Set<VoteViewTO> topicVotes = new HashSet<VoteViewTO>();
    // Loop through all the meeting votes
    for(Vote vote : votes){
      String voteTopicIdsStr = vote.getTopicIds();
      long[] voteTopicIds = LeanBeanUtil.stringToArray(voteTopicIdsStr);
      // Loop through the votes topics
      for(long voteTopicId : voteTopicIds) {
        // If the vote is on this topic, add it to the Set
        if(topic.getId() == voteTopicId) {
          VoteViewTO voteViewTO = mapVote(vote);
          topicVotes.add(voteViewTO);
        }
      }
    }
    topicViewTO.setVotes(topicVotes);
    return topicViewTO;
  }

  public static SortedSet<TopicViewTO> mapTopics(Set<Topic> topics, Set<Vote> votes) {
    SortedSet<TopicViewTO> topicViewTOs = new TreeSet<TopicViewTO>(TopicViewTO.TopicVoteComparator);
    for(Topic topic : topics) {
      topicViewTOs.add(mapTopic(topic, votes));
    }
    return topicViewTOs;
  }

  public static VoteViewTO mapVote(Vote vote) {
    VoteViewTO voteViewTO = new VoteViewTO();
    voteViewTO.setDevice(mapDevice(vote.getDevice()));
    return voteViewTO;
  }



}
