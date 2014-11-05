package com.wisobi.leanbean.jpa;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.User;
import com.wisobi.leanbean.jpa.entity.Vote;

import java.util.Properties;

import javax.persistence.EntityManager;

/**
 * Created by bjork on 10/09/14.
 */
public class LeanBeanJpaDao implements LeanBeanDao {

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
  public User findByUserId(long userId) {
    User user = em.find(User.class, userId);
    return user;
  }

  @Override
  public Topic findByTopicId(long topicId) {
    Topic topic = em.find(Topic.class, topicId);
    return topic;
  }

  @Override
  public void addTopic(Topic topic) throws IllegalArgumentException {

    // Verify that topic has referenced user that exists
    if (topic.getUser() == null) {
      throw new IllegalArgumentException(
          "Missing data. Tried to add a topic without a referenced user.");
    }
    User user = findByUserId(topic.getUser().getId());
    if (user == null) {
      throw new IllegalArgumentException(
          "Inconsistent data. Tried to add a topic with a reference to user id " + topic.getUser()
              .getId() + " which cannot be found in data base.");
    }
    topic.setUser(user);

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
    em.merge(user);
    em.merge(meeting);
    em.persist(topic);
    // Set the bidirectional relationships once the topic is persisted
    meeting.addTopic(topic);
    em.getTransaction().commit();
  }

  @Override
  public void addUser(User user) {
    em.getTransaction().begin();
    em.persist(user);
    em.getTransaction().commit();
  }

  @Override
  public void addMeeting(Meeting meeting) throws IllegalArgumentException {

    // Verify that meeting has referenced user that exists
    if (meeting.getUser() == null) {
      throw new IllegalArgumentException(
          "Missing data. Tried to add a meeting without a referenced user.");
    }
    User user = findByUserId(meeting.getUser().getId());
    if (user == null) {
      throw new IllegalArgumentException(
          "Inconsistent data. Tried to add a meeting with a reference to user id " + meeting
              .getUser().getId() + " which cannot be found in data base.");
    }
    meeting.setUser(user);

    // Merge and persist
    em.getTransaction().begin();
    em.merge(user);
    em.persist(meeting);
    em.getTransaction().commit();
  }

  @Override
  public void addVote(Vote vote) throws IllegalArgumentException{

    // Verify that vote has referenced user that exists
    if(vote.getUser() == null) {
      throw new IllegalArgumentException(
          "Missing data. Tried to add a vote without a referenced user.");
    }
    User user = findByUserId(vote.getUser().getId());
    if(user == null) {
      throw new IllegalArgumentException(
          "Inconsistent data. Tried to add a vote with a reference to user id " + vote
              .getUser().getId() + " which cannot be found in data base.");
    }

    // Verify that vote has referenced topic that exists
    if(vote.getTopic() == null) {
      throw new IllegalArgumentException(
          "Missing data. Tried to add a vote without a referenced topic.");
    }
    Topic topic = findByTopicId(vote.getTopic().getId());
    if(topic == null) {
      throw new IllegalArgumentException(
          "Inconsistent data. Tried to add a vote with a reference to topic id " + vote
              .getTopic().getId() + " which cannot be found in data base.");
    }

    // Merge and persist
    em.getTransaction().begin();
    em.merge(user);
    em.merge(topic);
    em.persist(vote);
    // Set the bidirectional relationships once the vote is persisted
    topic.addVote(vote);
    em.getTransaction().commit();
  }
}
