package com.wisobi.leanbean;

import com.wisobi.leanbean.entity.Meeting;
import com.wisobi.leanbean.entity.Topic;
import com.wisobi.leanbean.entity.User;
import com.wisobi.leanbean.entity.Vote;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class LeanBeanDaoTest {

  final static Logger logger = LoggerFactory.getLogger(LeanBeanDaoTest.class);

  private static LeanBeanDao dao = new LeanBeanJpaDao();

  @After
  public void tearDown() throws Exception {

  }

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {

    User user1 = new User();
    user1.setName("Alice");
    user1.setEmail("alice@wisobi.com");
    dao.addUser(user1);

    User user2 = new User();
    user2.setName("Bob");
    user2.setEmail("bob@wisobi.com");
    dao.addUser(user2);

    User user3 = new User();
    user3.setName("Carol");
    user3.setEmail("carol@wisobi.com");
    dao.addUser(user3);

    User user4 = new User();
    user4.setName("Dave");
    user4.setEmail("dave@wisobi.com");
    dao.addUser(user4);

    Meeting meeting1 = new Meeting("Weekly Manager Meeting", user1);
    dao.addMeeting(meeting1);

    Meeting meeting2 = new Meeting("Department Meeting", user2);
    dao.addMeeting(meeting2);

    // Topics for meeting 1
    Topic
        topic1 =
        new Topic("Salary process update", "This is a short pitch of Salary process update.",
                  meeting1, user1);
    dao.addTopic(topic1);
    System.out.println("setUp(): Topic1 id = " + topic1.getId());

    Topic
        topic2 =
        new Topic("Risk of developer churn", "This is a short pitch of Risk of developer churn.",
                  meeting1, user2);
    dao.addTopic(topic2);

    Topic
        topic3 =
        new Topic("Autonomous teams", "This is a short pitch of Autonomous teams.", meeting1,
                  user2);
    dao.addTopic(topic3);

    Topic
        topic4 =
        new Topic("Is our code tested good enough?",
                  "This is a short pitch of Is our code tested good enough?", meeting1, user2);
    dao.addTopic(topic4);

    Topic
        topic5 =
        new Topic("Upcoming conference", "This is a short pitch of Upcoming conference.", meeting1,
                  user3);
    dao.addTopic(topic5);

    // Topics for meeting 2
    Topic
        topic6 =
        new Topic("New  meeting, new topic", "This is a short pitch of New meeting.", meeting2,
                  user1);
    dao.addTopic(topic6);

    // Votes for meeting 1
    Vote vote1 = new Vote(user1, topic1);
    dao.addVote(vote1);
    Vote vote2 = new Vote(user1, topic2);
    dao.addVote(vote2);
    Vote vote3 = new Vote(user2, topic2);
    dao.addVote(vote3);
    Vote vote4 = new Vote(user2, topic3);
    dao.addVote(vote4);

  }

  @Test
  public void testSortedTopics() throws Exception {
    Meeting meeting = dao.findByMeetingId(1);
    int numVotesPrev = Integer.MAX_VALUE;
    int numVotes = 0;
    int index = 0;
    for (Topic topic : meeting.getTopics()) {
      numVotes = topic.numVotes();
      // Previous topic should have more than or equal to number of current topic's votes.
      logger.debug("Topic index: " + index++ + ", number of votes: " + numVotes);
      assert numVotesPrev >= numVotes;
      numVotesPrev = numVotes;
    }
  }

  @Test
  public void testTopicWithoutVotes() throws Exception {
    Meeting meeting = dao.findByMeetingId(2);
    Topic topic = meeting.getTopics().iterator().next();
    logger.debug("Topic Id: " + topic.getId() + ", votes: " + topic.getVotes());
    assertNull(topic.getVotes());
  }

  @Test
  public void testMeetingUser() throws Exception {
    Meeting meeting = dao.findByMeetingId(1);
    User user = meeting.getUser();
    logger.debug("Meeting Id: " + meeting.getId() + ", user: " + user.getName());
    assertNotNull(user);
    assertEquals("Alice", user.getName());
  }

  @Test
  public void testTopicUser() throws Exception {
    Meeting meeting = dao.findByMeetingId(1);
    Topic topic = meeting.getTopics().iterator().next();
    User user = topic.getUser();
    logger.debug("Topic Id: " + topic.getId() + ", user: " + user.getName());
    assertNotNull(user);
    assertEquals("Bob", user.getName());
  }

  @Test
  public void testVoteUser() throws Exception {
    Meeting meeting = dao.findByMeetingId(1);
    Topic topic = meeting.getTopics().iterator().next();
    Vote vote = topic.getVotes().iterator().next();
    User user = vote.getUser();
    logger.debug("Vote Id: " + vote.getId() + ", user: " + user.getName());
    assertNotNull(user);
    assertEquals("Bob", user.getName());
  }

}