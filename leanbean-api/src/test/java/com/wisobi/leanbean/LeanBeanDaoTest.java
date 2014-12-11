package com.wisobi.leanbean;

import com.wisobi.leanbean.jpa.LeanBeanJpaDao;
import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.Device;
import com.wisobi.leanbean.jpa.entity.Vote;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class LeanBeanDaoTest {

  final static Logger logger = LoggerFactory.getLogger(LeanBeanDaoTest.class);

  private static LeanBeanDao dao;

  @AfterClass
  public static void closeDao() throws Exception {
    dao.close();
  }

  @BeforeClass
  public static void initDao() throws Exception {

    dao = new LeanBeanJpaDao();

    Device device1 = new Device();
    device1.setAlias("Alice");
    device1.setModel("Android SDK built for x86");
    device1.setCordova("3.6.4");
    device1.setPlatform("Android");
    device1.setUuid("1ab9f8483ab42ef1");
    device1.setVersion("4.4.2");
    dao.addDevice(device1);

    Device device2 = new Device();
    device2.setAlias("Bob");
    device2.setModel("Android SDK built for x86");
    device2.setCordova("3.6.4");
    device2.setPlatform("Android");
    device2.setUuid("1ab9f8483ab42ef2");
    device2.setVersion("4.4.2");
    dao.addDevice(device2);

    Device device3 = new Device();
    device3.setAlias("Carol");
    device3.setModel("Android SDK built for x86");
    device3.setCordova("3.6.4");
    device3.setPlatform("Android");
    device3.setUuid("1ab9f8483ab42ef3");
    device3.setVersion("4.4.2");
    dao.addDevice(device3);

    Device device4 = new Device();
    device4.setAlias("Dave");
    device4.setModel("Android SDK built for x86");
    device4.setCordova("3.6.4");
    device4.setPlatform("Android");
    device4.setUuid("1ab9f8483ab42ef4");
    device4.setVersion("4.4.2");
    dao.addDevice(device4);

    Meeting meeting1 = new Meeting("Weekly Manager Meeting", device1);
    dao.addMeeting(meeting1);

    Meeting meeting2 = new Meeting("Department Meeting", device2);
    dao.addMeeting(meeting2);

    // Topics for meeting 1
    Topic
        topic1 =
        new Topic("Salary process update", "This is a short pitch of Salary process update.",
                  meeting1, device1);
    dao.addTopic(topic1);
    System.out.println("setUp(): Topic1 id = " + topic1.getId());

    Topic
        topic2 =
        new Topic("Risk of developer churn", "This is a short pitch of Risk of developer churn.",
                  meeting1, device2);
    dao.addTopic(topic2);

    Topic
        topic3 =
        new Topic("Autonomous teams", "This is a short pitch of Autonomous teams.", meeting1,
                  device2);
    dao.addTopic(topic3);

    Topic
        topic4 =
        new Topic("Is our code tested good enough?",
                  "This is a short pitch of Is our code tested good enough?", meeting1, device2);
    dao.addTopic(topic4);

    Topic
        topic5 =
        new Topic("Upcoming conference", "This is a short pitch of Upcoming conference.", meeting1,
                  device3);
    dao.addTopic(topic5);

    // Topics for meeting 2
    Topic
        topic6 =
        new Topic("New  meeting, new topic", "This is a short pitch of New meeting.", meeting2,
                  device1);
    dao.addTopic(topic6);

    // Votes for meeting 1
    Vote vote1 = new Vote(device1, topic1);
    dao.addVote(vote1);
    Vote vote2 = new Vote(device1, topic2);
    dao.addVote(vote2);
    Vote vote3 = new Vote(device2, topic2);
    dao.addVote(vote3);
    Vote vote4 = new Vote(device2, topic3);
    dao.addVote(vote4);

  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void testNumTopics() {
    Meeting meeting = dao.findByMeetingId(1);
    int numTopics = meeting.getTopics().size();
    assertEquals(5, numTopics);
  }

  @Test
  public void testSortedTopics() {
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
  public void testTopicWithoutVotes() {
    Meeting meeting = dao.findByMeetingId(2);
    Topic topic = meeting.getTopics().iterator().next();
    logger.debug("Topic Id: " + topic.getId() + ", votes: " + topic.getVotes());
    assertNull(topic.getVotes());
  }

  @Test
  public void testMeetingDevice() {
    Meeting meeting = dao.findByMeetingId(1);
    Device device = meeting.getDevice();
    logger.debug("Meeting Id: " + meeting.getId() + ", username: " + device.getAlias());
    assertNotNull(device);
    assertEquals("Alice", device.getAlias());
  }

  @Test
  public void testTopicDevice() {
    Meeting meeting = dao.findByMeetingId(1);
    boolean tested = false;
    for (Topic topic : meeting.getTopics()) {
      if (topic.getId() == 1) {
        Device device = topic.getDevice();
        logger.debug("Topic Id: " + topic.getId() + ", username: " + device.getAlias());
        assertNotNull(device);
        assertEquals("Alice", device.getAlias());
        tested = true;
      }
    }
    assertTrue(tested);
  }

  @Test
  public void testVoteDevice() {
    Meeting meeting = dao.findByMeetingId(1);
    boolean tested = false;
    for (Topic topic : meeting.getTopics()) {
      if (topic.getId() == 1) {
        for (Vote vote : topic.getVotes()) {
          if (vote.getId() == 1) {
            Device device = vote.getDevice();
            logger.debug("Vote Id: " + vote.getId() + ", username: " + device.getAlias());
            assertNotNull(device);
            assertEquals("Alice", device.getAlias());
            tested = true;
          }
        }
      }
    }
    assertTrue(tested);
  }

  /*
   * Test persistence and data consistency of Meeting
   */

  @Test(expected = IllegalArgumentException.class)
  public void testAddMeetingWithNullDevice() {
    Meeting meeting = new Meeting("Review of Key Results", null);
    dao.addMeeting(meeting);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMeetingWithInconsistentDevice() {
    Device device = new Device();
    device.setId(1000);

    Meeting meeting = new Meeting("Review of Key Results", device);
    dao.addMeeting(meeting);
  }

  @Test
  public void testAddMeeting() {
    Device device = new Device();
    device.setId(1);

    Meeting meeting = new Meeting("Review of Key Results", device);
    dao.addMeeting(meeting);
  }

  /*
   * Test persistence and data consistency of Device
   */

  @Test
  public void testAddDevice() {
    Device device = new Device();
    device.setAlias("Eve");
    device.setModel("Android SDK built for x86");
    device.setCordova("3.6.4");
    device.setPlatform("Android");
    device.setUuid("1ab9f8483ab42ef5");
    device.setVersion("4.4.2");
    dao.addDevice(device);
  }

  @Test
  public void testFindDeviceByUUID() {
    Device device = dao.findByDeviceUUID("1ab9f8483ab42ef1");
    assertNotNull(device);
    assertEquals(1, device.getId());
  }

  /*
   * Test persistence and data consistency of Topic
   */

  @Test(expected = IllegalArgumentException.class)
  public void testAddTopicWithNullDevice() {
    Meeting meeting = new Meeting();
    meeting.setId(2);

    Topic topic = new Topic("Topic title", "This is a short pitch of Topic title", meeting, null);
    dao.addTopic(topic);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTopicWithInconsistentDevice() {
    Meeting meeting = new Meeting();
    meeting.setId(2);

    Device device = new Device();
    device.setId(1000);

    Topic topic = new Topic("Topic title", "This is a short pitch of Topic title", meeting, device);
    dao.addTopic(topic);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTopicWithNullMeeting() {
    Device device = new Device();
    device.setId(1);

    Topic topic = new Topic("Topic Title", "This is a short pitch of Topic Title", null, device);
    dao.addTopic(topic);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTopicWithInconsistentMeeting() {
    Device device = new Device();
    device.setId(1);

    Meeting meeting = new Meeting();
    meeting.setId(1000);

    Topic topic = new Topic("Topic Title", "This is a short pitch of Topic Title", meeting, device);
    dao.addTopic(topic);
  }

  @Test
  public void testAddTopic() {
    Device device = new Device();
    device.setId(1);

    Meeting meeting = new Meeting();
    meeting.setId(2);

    Topic topic = new Topic("Topic title", "This is a short pitch of Topic Title", meeting, device);
    dao.addTopic(topic);
  }

  /*
   * Test persistence and data consistency of Vote
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddVoteWithNullDevice() {
    Topic topic = new Topic();
    topic.setId(1);

    Vote vote = new Vote(null, topic);
    dao.addVote(vote);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddVoteWithInconsistentDevice() {
    Topic topic = new Topic();
    topic.setId(1);

    Device device = new Device();
    device.setId(1000);

    Vote vote = new Vote(device, topic);
    dao.addVote(vote);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddVoteWithNullTopic() {
    Device device = new Device();
    device.setId(1);

    Vote vote = new Vote(device, null);
    dao.addVote(vote);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddVoteWithInconsistentTopic() {
    Device device = new Device();
    device.setId(1);

    Topic topic = new Topic();
    topic.setId(1000);

    Vote vote = new Vote(device, topic);
    dao.addVote(vote);
  }

  @Test
  public void addVote() {
    Device device = new Device();
    device.setId(1);

    Topic topic = new Topic();
    topic.setId(6);

    Vote vote = new Vote(device, topic);
    dao.addVote(vote);
  }

}