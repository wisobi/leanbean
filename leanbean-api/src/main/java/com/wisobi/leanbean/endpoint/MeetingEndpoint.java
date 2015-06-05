package com.wisobi.leanbean.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.LeanBeanUtil;
import com.wisobi.leanbean.dto.DAO2DTOMapper;
import com.wisobi.leanbean.dto.DeviceTO;
import com.wisobi.leanbean.dto.MeetingViewTO;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;
import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.restlet.resource.MeetingResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by bjork on 27/08/14.
 */
@ServerEndpoint("/ws/meeting/{meeting-id}")
public class MeetingEndpoint {


  final static Logger logger = LoggerFactory.getLogger(MeetingEndpoint.class);
  private static Session session;

  public static void broadcastMeeting(long meetingId) {
    MeetingResource meetingResource = new MeetingResource();
    MeetingViewTO meeting = meetingResource.findMeetingById(meetingId);
    if (meeting == null) {
      broadcast("meetingId", LeanBeanUtil.idEncode(meetingId), "{}");
    }
    try {
      String meetingJSON = new ObjectMapper().writer().writeValueAsString(meeting);
      broadcast("meetingId", meeting.getId(), meetingJSON);
    } catch (JsonProcessingException e) {
      logger.debug(e.getMessage());
    }
  }

  private static void broadcast(String key, String value, String msg) {
    for (Session s : session.getOpenSessions()) {
      String propValue = (String)s.getUserProperties().get(key);
      if (s.isOpen() && value.equals(propValue)) {
        try {
          s.getBasicRemote().sendText(msg);
        } catch (IOException e) {
          logger.debug("LeanBean Error: Failed to send message to client", e);
          try {
            s.close();
          } catch (IOException e1) {
            // Ignore
          }
        }
      }
    }
  }

  @OnOpen
  public void start(@PathParam("meeting-id") String meetingId, Session session) {
    session.getUserProperties().put("meetingId", meetingId);
    this.session = session;
    logger.debug("LeanBean OnOpen meetingId " + meetingId);
  }

  @OnClose
  public void end(@PathParam("meeting-id") String meetingId, Session session) {
    logger.debug("LeanBean OnClose meetingId " + meetingId);
  }

  @OnError
  public void onError(Throwable t) throws Throwable {
    logger.error("LeanBean Error: " + t.toString(), t);
  }

  @OnMessage
  public void onMessage(String message, Session session) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      DeviceTO deviceTO = mapper.readValue(message, DeviceTO.class);
      logger.debug(deviceTO.getId() + ", " + deviceTO.getAlias());
    } catch (IOException e) {
      e.printStackTrace();
    }
    logger.debug(message);
    //broadcast(message);
  }
}
