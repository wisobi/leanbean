package com.wisobi.leanbean;

import com.wisobi.leanbean.dto.DAO2DTOMapper;
import com.wisobi.leanbean.dto.TopicViewTO;
import com.wisobi.leanbean.jpa.entity.Device;
import com.wisobi.leanbean.builder.DeviceBuilder;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.builder.TopicBuilder;
import com.wisobi.leanbean.jpa.entity.Vote;
import com.wisobi.leanbean.builder.VoteBuilder;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjork on 15/12/14.
 */
public class DAO2DTOMapperTest {

  final static Logger logger = LoggerFactory.getLogger(DAO2DTOMapperTest.class);

  @Test
  public void testSortedTopics() {

    DeviceBuilder db = new DeviceBuilder();
    Device device1 = db.id(1).alias("Alice").build();
    Device device2 = db.id(2).alias("Bob").build();
    Device device3 = db.id(3).alias("Carol").build();

    TopicBuilder tb = new TopicBuilder();
    Topic topic1 = tb.id(1).title("Topic 1").device(device1).build();
    Topic topic2 = tb.id(2).title("Topic 2").device(device1).build();
    Topic topic3 = tb.id(3).title("Topic 3").device(device2).build();
    Topic topic4 = tb.id(4).title("Topic 4").device(device3).build();

    Set<Topic> topics = new HashSet<Topic>();
    topics.add(topic1);
    topics.add(topic2);
    topics.add(topic3);
    topics.add(topic4);

    VoteBuilder vb = new VoteBuilder();
    Vote vote1 = vb.device(device1).topicIds("1,2").build();
    Vote vote2 = vb.device(device2).topicIds("1,3").build();

    Set<Vote> votes = new HashSet<Vote>();
    votes.add(vote1);
    votes.add(vote2);

    Set<TopicViewTO> topicViewTOs = DAO2DTOMapper.mapTopics(topics, votes);
    int numVotesPrev = Integer.MAX_VALUE;
    int numVotes = 0;
    int index = 0;
    for (TopicViewTO topic : topicViewTOs) {
      numVotes = topic.numVotes();
      // Previous topic should have more than or equal to number of current topic's votes.
      logger.debug("Topic id = " + topic.getId() + ", index =  " + index++ + ", number of votes =  " + numVotes);
      assert numVotesPrev >= numVotes;
      numVotesPrev = numVotes;
    }
  }

}
