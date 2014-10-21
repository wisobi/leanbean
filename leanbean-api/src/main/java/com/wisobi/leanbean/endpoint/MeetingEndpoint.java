package com.wisobi.leanbean.endpoint;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by bjork on 27/08/14.
 */
@ServerEndpoint("/ws/meeting")
public class MeetingEndpoint {

  @OnMessage
  public String onMessage(String message, Session session) {
    return message;
  }
}
