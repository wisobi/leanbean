package com.wisobi.leanbean;

import com.wisobi.leanbean.entity.Meeting;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;

import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 * Created by bjork on 25/08/14.
 */
public class MeetingResource extends ServerResource {

  private LeanBeanDao dao = new LeanBeanJpaDao();

  @Get("json")
  public Meeting findMeetingById() {
    long meetingId = Long.parseLong(getRequestAttributes().get("meeting").toString());
    Meeting meeting = dao.findByMeetingId(meetingId);
    return meeting;
  }

  @Put("json")
  public void addMeeting(Meeting meeting) {
    dao.addMeeting(meeting);
  }
}
