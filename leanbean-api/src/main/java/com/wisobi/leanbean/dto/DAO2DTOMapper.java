package com.wisobi.leanbean.dto;

import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.User;
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

    meetingViewTO.setUser(mapUser(meeting.getUser()));
    meetingViewTO.setTopics(mapTopics(meeting.getTopics()));

    return meetingViewTO;
  }

  public static UserViewTO mapUser(User user) {
    UserViewTO userViewTO = new UserViewTO();
    userViewTO.setId(user.getId());
    userViewTO.setName(user.getName());
    userViewTO.setEmail(user.getEmail());
    return userViewTO;
  }

  public static TopicViewTO mapTopic(Topic topic) {
    TopicViewTO topicViewTO = new TopicViewTO();
    topicViewTO.setId(topic.getId());
    topicViewTO.setTitle(topic.getTitle());
    topicViewTO.setPitch(topic.getPitch());

    topicViewTO.setUser(mapUser(topic.getUser()));
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
    voteViewTO.setUser(mapUser(vote.getUser()));
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
