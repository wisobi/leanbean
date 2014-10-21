package com.wisobi.leanbean.mysql;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.entity.Meeting;
import com.wisobi.leanbean.entity.Topic;
import com.wisobi.leanbean.entity.User;
import com.wisobi.leanbean.entity.Vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bjork on 27/08/14.
 */
public class LeanBeanMysqlDao implements LeanBeanDao {

  @Override
  public Meeting findByMeetingId(long meetingId) {
    Meeting meeting = new Meeting();
    Topic topic = null;
    Vote vote = null;

    // Read query from file
    String query2 = DBUtil.readQuery("/db-find-by-meeting-id.sql");

    String query = "select *"
                   + "from meeting as m "
                   + "left outer join user as u on m.user_id = m.id";

    if (query == null) {
      return null;
    }

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultset = null;
    try {
      connection = ConnectionFactory.getConnection();
      statement = connection.prepareStatement(query);
      statement.setLong(1, meetingId);
      resultset = statement.executeQuery();

      // Get first resultset row
      if (resultset.next()) {
        meeting = createMeetingFromResultSet(resultset);
        topic = createTopicFromResultSet(resultset);
        meeting.addTopic(topic);
        vote = createVoteFromResultSet(resultset);
        topic.addVote(vote);
      }
      // Loop through the rest
      while (resultset.next()) {
        // Check if we reached a new topic
        long nextTopicId = resultset.getLong("topic_id");
        if (topic.getId() != nextTopicId) {
          topic = createTopicFromResultSet(resultset);
          meeting.addTopic(topic);
        }
        // Add the vote
        vote = createVoteFromResultSet(resultset);
        topic.addVote(vote);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBUtil.close(statement);
      DBUtil.close(connection);
    }

    return meeting;
  }

  @Override
  public User findByUserId(long userId) {
    User user = new User();
    user.setId(1);
    user.setEmail("bjork@wisobi.com");
    user.setName("Jonas Björk");
    return user;
  }


  private Meeting createMeetingFromResultSet(ResultSet resultset) throws SQLException {
    Meeting meeting = new Meeting();
    long meetingId = resultset.getLong("meeting_id");
    meeting.setId(meetingId);
    String meetingTitle = resultset.getString("meeting_title");
    meeting.setTitle(meetingTitle);
    int meetingDuration = resultset.getInt("meeting_duration");
    meeting.setDuration(meetingDuration);

    long userId = resultset.getLong("meeting_user_id");
    String userName = resultset.getString("meeting_user_name");
    User user = new User();
    user.setId(userId);
    user.setName(userName);
    meeting.setUser(user);

    return meeting;
  }

  private Topic createTopicFromResultSet(ResultSet resultset) throws SQLException {
    Topic topic = new Topic();
    long topicId = resultset.getLong("topic_id");
    topic.setId(topicId);
    String topicTitle = resultset.getString("topic_title");
    topic.setTitle(topicTitle);
    String topicPitch = resultset.getString("topic_pitch");
    topic.setPitch(topicPitch);

    long userId = resultset.getLong("topic_user_id");
    String userName = resultset.getString("topic_user_name");
    User user = new User();
    user.setId(userId);
    user.setName(userName);
    topic.setUser(user);

    return topic;
  }

  private Vote createVoteFromResultSet(ResultSet resultset) throws SQLException {
    Vote vote = new Vote();

    long userId = resultset.getLong("vote_user_id");
    String userName = resultset.getString("vote_user_name");
    User user = new User();
    user.setId(userId);
    user.setName(userName);
    vote.setUser(user);

    return vote;
  }

  @Override
  public void addTopic(Topic topic) {

  }

  @Override
  public void addUser(User user) {

  }

  @Override
  public void addMeeting(Meeting meeting) {

  }

  @Override
  public void addVote(Vote vote) {

  }
}
