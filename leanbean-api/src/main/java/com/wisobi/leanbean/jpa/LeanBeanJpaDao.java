package com.wisobi.leanbean.jpa;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.entity.Meeting;
import com.wisobi.leanbean.entity.Topic;
import com.wisobi.leanbean.entity.User;
import com.wisobi.leanbean.entity.Vote;

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
  public void addTopic(Topic topic) {
    em.getTransaction().begin();
    em.persist(topic);
    em.getTransaction().commit();
  }

  @Override
  public void addUser(User user) {
    em.getTransaction().begin();
    em.persist(user);
    em.getTransaction().commit();
  }

  @Override
  public void addMeeting(Meeting meeting) {
    em.getTransaction().begin();
    em.persist(meeting);
    em.getTransaction().commit();
  }

  @Override
  public void addVote(Vote vote) {
    em.getTransaction().begin();
    em.persist(vote);
    em.getTransaction().commit();
  }
}
