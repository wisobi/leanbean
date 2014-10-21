package com.wisobi.leanbean.mysql;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.entity.Meeting;
import com.wisobi.leanbean.entity.Topic;

import junit.framework.TestCase;

import java.util.List;
import java.util.Set;

public class LeanBeanMysqlDaoTest extends TestCase {

  private LeanBeanDao dao = new LeanBeanMysqlDao();

  public void setUp() throws Exception {

  }

  public void tearDown() throws Exception {

  }

  public void testFindByMeetingId() throws Exception {
    Meeting meeting = dao.findByMeetingId(1);
    Set<Topic> topics = meeting.getTopics();
    for(Topic topic : topics) {
      System.out.println(topic.getTitle() + " : " + topic.numVotes());
    }
  }
}