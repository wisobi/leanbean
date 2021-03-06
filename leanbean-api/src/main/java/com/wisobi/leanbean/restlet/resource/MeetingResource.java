package com.wisobi.leanbean.restlet.resource;

import com.wisobi.leanbean.Hashids;
import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.LeanBeanUtil;
import com.wisobi.leanbean.dto.DAO2DTOMapper;
import com.wisobi.leanbean.dto.DTO2DAOMapper;
import com.wisobi.leanbean.dto.MeetingTO;
import com.wisobi.leanbean.dto.MeetingViewTO;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;
import com.wisobi.leanbean.jpa.entity.Meeting;

import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bjork on 25/08/14.
 */
public class MeetingResource extends ServerResource {

  final private static Logger logger = LoggerFactory.getLogger(MeetingResource.class);

  final private LeanBeanDao dao = new LeanBeanJpaDao();

  @Get("json")
  public MeetingViewTO findMeetingById() {
    String hash = getRequestAttributes().get("meeting-id").toString();
    long meetingId = LeanBeanUtil.idDecode(hash);
    return findMeetingById(meetingId);
  }

  public MeetingViewTO findMeetingById(long meetingId) {
    if(meetingId < 0) {
      // If the hash cannot be decoded to an integer, no need to check the database
      getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
      throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
    }

    Meeting meeting = dao.findByMeetingId(meetingId);
    try {
      dao.close();
    } catch (Exception e) {
      logger.debug(e.getMessage());
    }

    if (meeting == null) {
      // No meeting with decoded id found in the database
      getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
      throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
    }
    MeetingViewTO meetingViewTO = DAO2DTOMapper.mapMeeting(meeting);

    return meetingViewTO;
  }

  @Post("json")
  public MeetingViewTO addMeeting(MeetingTO meetingTO) {
    Meeting meeting = DTO2DAOMapper.mapMeeting(meetingTO);
    logger.info("Meeting: " + meeting.getTitle() + ", Device: " + meeting.getDevice().getId());
    try {
      dao.addMeeting(meeting);
      getResponse().setStatus(Status.SUCCESS_CREATED);
    } catch (Exception e) {
      logger.debug(e.getMessage());
      throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, e.getMessage(), e);
    } finally {
      try {
        dao.close();
      } catch (Exception e) {
        logger.error(e.getMessage());
      }
    }

    return DAO2DTOMapper.mapMeeting(meeting);
  }
}
