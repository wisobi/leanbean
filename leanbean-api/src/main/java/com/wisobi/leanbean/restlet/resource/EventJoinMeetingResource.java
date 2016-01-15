package com.wisobi.leanbean.restlet.resource;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.dto.DAO2DTOMapper;
import com.wisobi.leanbean.dto.DTO2DAOMapper;
import com.wisobi.leanbean.dto.DeviceTO;
import com.wisobi.leanbean.dto.EventJoinMeetingTO;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;
import com.wisobi.leanbean.jpa.entity.Device;
import com.wisobi.leanbean.jpa.entity.EventJoinMeeting;
import org.restlet.data.Status;
import org.restlet.resource.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bjork on 04/11/14.
 */
public class EventJoinMeetingResource extends ServerResource {

  final static Logger logger = LoggerFactory.getLogger(EventJoinMeetingResource.class);

  private LeanBeanDao dao = new LeanBeanJpaDao();

  @Post("json")
  public void logEventJoinMeeting(EventJoinMeetingTO eventTO) {
    EventJoinMeeting event = DTO2DAOMapper.mapEventJoinMeeting(eventTO);

    try {
      dao.logJoinMeeting(event);
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
  }

}
