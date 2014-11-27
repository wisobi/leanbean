package com.wisobi.leanbean.restlet.resource;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.dto.DAO2DTOMapper;
import com.wisobi.leanbean.dto.DTO2DAOMapper;
import com.wisobi.leanbean.dto.MeetingTO;
import com.wisobi.leanbean.dto.MeetingViewTO;
import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * Created by bjork on 25/08/14.
 */
public class MeetingResource extends ServerResource {

  private LeanBeanDao dao = new LeanBeanJpaDao();

  /*
  @Get("json")
  public Meeting findMeetingById() {
    long meetingId = Long.parseLong(getRequestAttributes().get("meeting-id").toString());
    Meeting meeting = dao.findByMeetingId(meetingId);
    return meeting;
  }
  */

  @Get("json")
  public MeetingViewTO findMeetingById() {
    long meetingId = Long.parseLong(getRequestAttributes().get("meeting-id"). toString());
    Meeting meeting = dao.findByMeetingId(meetingId);
    MeetingViewTO meetingViewTO = DAO2DTOMapper.mapMeeting(meeting);
    return meetingViewTO;
  }

  @Post("json")
  public void addMeeting(MeetingTO meetingTO) {
    Meeting meeting = DTO2DAOMapper.mapMeeting(meetingTO);
    try {
      dao.addMeeting(meeting);
    } catch (Exception e) {
      // Do nothing
    }
  }
}
