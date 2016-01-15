package com.wisobi.leanbean;

import com.wisobi.leanbean.dto.DeviceTO;
import com.wisobi.leanbean.jpa.entity.*;

import java.util.List;

/**
 * Created by bjork on 25/08/14.
 */
public interface LeanBeanDao {

  public Meeting findByMeetingId(long meetingId);
  public List<Meeting> findMeetingsByDeviceId(long deviceId);
  public Device findByDeviceId(long deviceId);
  public Device findByDeviceUUID(String deviceUUID);
  public Topic findByTopicId(long topicId);
  public void addTopic(Topic topic) throws IllegalArgumentException;
  public void addDevice(Device device) throws IllegalArgumentException;
  public void addMeeting(Meeting meeting) throws IllegalArgumentException;
  public void addVote(Vote vote) throws IllegalArgumentException;
  public void deleteTopic(Topic topic) throws IllegalArgumentException;
  public Device updateDevice(Device device) throws IllegalArgumentException;
  public Vote updateVote(Vote vote) throws IllegalArgumentException;
  public void logJoinMeeting(EventJoinMeeting eventJoinMeeting);
  public void close() throws Exception;

}
