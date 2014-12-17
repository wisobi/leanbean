package com.wisobi.leanbean.restlet;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;
import com.wisobi.leanbean.jpa.entity.Device;
import com.wisobi.leanbean.jpa.entity.Vote;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Helper resource just to bootstrap the data layer with some data while working with the
 * leanbean-web.
 *
 * $ wget localhost:8080/v1/bootstrap
 *
 * Created by bjork on 16/10/14.
 */
public class LeanBeanBootstrap extends ServerResource {

  private LeanBeanDao dao = new LeanBeanJpaDao();

  @Get("json")
  public String toJson() {
    try {
      Device device1 = new Device();
      device1.setAlias("Alice");
      device1.setUuid("1ab9f8483ab42ef9");
      dao.addDevice(device1);

      Device device2 = new Device();
      device2.setAlias("Bob");
      device2.setUuid("1ab9f8483ab42ef7");
      dao.addDevice(device2);

      Device device3 = new Device();
      device3.setAlias("Carol");
      device3.setUuid("1ab9f8483ab42ef6");
      dao.addDevice(device3);

      Device device4 = new Device();
      device4.setAlias("Dave");
      device4.setUuid("1ab9f8483ab42ef5");
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
          new Topic("Upcoming conference", "This is a short pitch of Upcoming conference.",
                    meeting1, device3);
      dao.addTopic(topic5);

      // Topics for meeting 2
      Topic
          topic6 =
          new Topic("New  meeting, new topic", "This is a short pitch of New meeting.", meeting2,
                    device1);
      dao.addTopic(topic6);

      // Votes for meeting 1
      Vote vote1 = new Vote(device1, meeting1, "1,2");
      dao.addVote(vote1);
      Vote vote2 = new Vote(device2, meeting1, "2,3");
      dao.addVote(vote2);
      Vote vote3 = new Vote(device3, meeting1, "2");
      dao.addVote(vote3);

      dao.close();
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }

    return "Success";
  }

}
