package com.wisobi.leanbean.restlet.resource;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjork on 25/08/14.
 */
public class BrowseMeetingResource extends ServerResource {

  final private static Logger logger = LoggerFactory.getLogger(BrowseMeetingResource.class);

  final private LeanBeanDao dao = new LeanBeanJpaDao();

  @Get("json")
  public List<MeetingViewTO> findMeetingsByDeviceId() {
    long deviceId = Long.parseLong(getRequestAttributes().get("device-id").toString());
    return findMeetingsByDeviceId(deviceId);
  }

  public List<MeetingViewTO> findMeetingsByDeviceId(long deviceId) {
    List<Meeting> meetings = dao.findMeetingsByDeviceId(deviceId);
    try {
      dao.close();
    } catch (Exception e) {
      logger.debug(e.getMessage());
    }

    if (meetings == null) {
      throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
    }

    List<MeetingViewTO> meetingViewTOs = new ArrayList<>(meetings.size());
    for(Meeting meeting : meetings) {
      MeetingViewTO meetingViewTO = DAO2DTOMapper.mapMeeting(meeting);
      meetingViewTOs.add(meetingViewTO);
    }

    return meetingViewTOs;
  }

}
