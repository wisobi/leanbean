package com.wisobi.leanbean.jpa;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.Device;
import com.wisobi.leanbean.jpa.entity.Vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;

/**
 * Created by bjork on 10/09/14.
 */
public class LeanBeanJpaDao implements LeanBeanDao {

  final static Logger logger = LoggerFactory.getLogger(LeanBeanJpaDao.class);

  private EntityManager em;

  public LeanBeanJpaDao() {
    em = JpaUtil.getEntityManagerFactory().createEntityManager();
  }

  public LeanBeanJpaDao(Properties properties) {
    em = JpaUtil.getEntityManagerFactory(properties).createEntityManager();
  }

  @Override
  public void close() {
    em.close();
  }

  @Override
  public Meeting findByMeetingId(long meetingId) {
    Meeting meeting = em.find(Meeting.class, meetingId);
    return meeting;
  }

  @Override
  public Device findByDeviceId(long deviceId) {
    Device device = em.find(Device.class, deviceId);
    return device;
  }

  @Override
  public Device findByDeviceUUID(String deviceUUID) {
    List resultList = em.createQuery("select d from Device d where uuid = :uuid")
        .setParameter("uuid", deviceUUID)
        .getResultList();
    Device device = null;
    if (!resultList.isEmpty()) {
      device = (Device) resultList.get(0);
    }
    return device;
  }

  @Override
  public Topic findByTopicId(long topicId) {
    Topic topic = em.find(Topic.class, topicId);
    if (topic == null) {
      throw new IllegalArgumentException(
          "Inconsistent data. Tried to remove a topic with topic id " + topicId
          + " which cannot be found in data base.");
    }
    return topic;
  }

  @Override
  public void addTopic(Topic topic) throws IllegalArgumentException {

    // Verify that topic has referenced device that exists
    if (topic.getDevice() == null) {
      throw new IllegalArgumentException(
          "Missing data. Tried to add a topic without a referenced device.");
    }
    Device device = findByDeviceId(topic.getDevice().getId());
    if (device == null) {
      throw new IllegalArgumentException(
          "Inconsistent data. Tried to add a topic with a reference to device id " + topic
              .getDevice()
              .getId() + " which cannot be found in data base.");
    }
    topic.setDevice(device);

    // Verify that topic has referenced meeting that exists
    if (topic.getMeeting() == null) {
      throw new IllegalArgumentException(
          "Missing data. Tried to add a topic without a referenced meeting.");
    }
    Meeting meeting = findByMeetingId(topic.getMeeting().getId());
    if (meeting == null) {
      throw new IllegalArgumentException(
          "Inconsistent data. Tried to add a topic with a reference to meeting id " + topic
              .getMeeting().getId() + " which cannot be found in data base.");
    }
    topic.setMeeting(meeting);

    // Merge and persist
    em.getTransaction().begin();
    em.merge(device);
    em.merge(meeting);
    em.persist(topic);
    // Set the bidirectional relationships once the topic is persisted
    meeting.addTopic(topic);
    em.getTransaction().commit();
  }

  @Override
  public void addDevice(Device device) {
    em.getTransaction().begin();
    em.persist(device);
    em.getTransaction().commit();
  }

  @Override
  public void addMeeting(Meeting meeting) throws IllegalArgumentException {

    // Verify that meeting has referenced device that exists
    if (meeting.getDevice() == null) {
      throw new IllegalArgumentException(
          "Missing data. Tried to add a meeting without a referenced device.");
    }
    Device device = findByDeviceId(meeting.getDevice().getId());
    if (device == null) {
      throw new IllegalArgumentException(
          "Inconsistent data. Tried to add a meeting with a reference to device id " + meeting
              .getDevice().getId() + " which cannot be found in data base.");
    }
    meeting.setDevice(device);

    // Merge and persist
    em.getTransaction().begin();
    em.merge(device);
    em.persist(meeting);
    em.getTransaction().commit();
  }

  @Override
  public void addVote(Vote vote) throws IllegalArgumentException {

    // Verify that vote has referenced device that exists
    if (vote.getDevice() == null) {
      throw new IllegalArgumentException(
          "Missing data. Tried to add a vote without a referenced device.");
    }
    Device device = findByDeviceId(vote.getDevice().getId());

    if (device == null) {
      throw new IllegalArgumentException(
          "Inconsistent data. Tried to add a vote with a reference to device id " + vote
              .getDevice().getId() + " which cannot be found in data base.");
    }

    // Verify that vote has referenced topic that exists
    if (vote.getMeeting() == null) {
      throw new IllegalArgumentException(
          "Missing data. Tried to add a vote without a referenced meeting.");
    }
    Meeting meeting = findByMeetingId(vote.getMeeting().getId());

    if (meeting == null) {
      throw new IllegalArgumentException(
          "Inconsistent data. Tried to add a vote with a reference to meeting id " + vote
              .getMeeting().getId() + " which cannot be found in data base.");
    }

    //Vote mergeVote = new Vote();
    //mergeVote.setDevice(device);
    //mergeVote.setMeeting(meeting);
    //mergeVote.setTopicIds(vote.getTopicIds());
    // Merge and persist
    em.getTransaction().begin();
    //em.merge(device);
    //em.merge(meeting);
    //em.persist(vote);
    em.merge(vote);
    // Set the bidirectional relationships once the vote is persisted
    //meeting.addVote(vote);
    em.getTransaction().commit();
  }

  @Override
  public Vote updateVote(Vote vote) throws IllegalArgumentException {

    // Verify that vote has referenced device that exists
    if (vote.getDevice() == null) {
      throw new IllegalArgumentException(
          "Missing data. Tried to add a vote without a referenced device.");
    }
    Device device = findByDeviceId(vote.getDevice().getId());

    if (device == null) {
      throw new IllegalArgumentException(
          "Inconsistent data. Tried to add a vote with a reference to device id " + vote
              .getDevice().getId() + " which cannot be found in data base.");
    }

    // Verify that vote has referenced topic that exists
    if (vote.getMeeting() == null) {
      throw new IllegalArgumentException(
          "Missing data. Tried to add a vote without a referenced meeting.");
    }
    Meeting meeting = findByMeetingId(vote.getMeeting().getId());

    if (meeting == null) {
      throw new IllegalArgumentException(
          "Inconsistent data. Tried to add a vote with a reference to meeting id " + vote
              .getMeeting().getId() + " which cannot be found in data base.");
    }

    // Merge and persist
    em.getTransaction().begin();
    Vote returnVote = em.merge(vote);
    em.getTransaction().commit();
    return returnVote;
  }

  @Override
  public void deleteTopic(Topic topic) throws IllegalArgumentException {
    em.getTransaction().begin();
    em.remove(topic);
    em.getTransaction().commit();
  }

  @Override
  public Device updateDevice(Device device) throws IllegalArgumentException {
    if (device == null || !(device.getId() > 0)) {
      throw new IllegalArgumentException(
          "Missing data. Tried to update a device without device id.");
    }

    em.getTransaction().begin();
    Device returnDevice = em.merge(device);
    em.getTransaction().commit();

    return returnDevice;
  }
}
