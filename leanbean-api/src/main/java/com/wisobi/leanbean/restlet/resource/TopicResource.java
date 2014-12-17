package com.wisobi.leanbean.restlet.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.dto.DTO2DAOMapper;
import com.wisobi.leanbean.dto.TopicTO;
import com.wisobi.leanbean.endpoint.MeetingEndpoint;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;
import com.wisobi.leanbean.jpa.entity.Topic;

import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bjork on 22/10/14.
 */
public class TopicResource extends ServerResource {

  final static Logger logger = LoggerFactory.getLogger(TopicResource.class);

  private LeanBeanDao dao = new LeanBeanJpaDao();

  @Post("json")
  public void addTopic(TopicTO topicTO) {
    Topic topic = DTO2DAOMapper.mapTopic(topicTO);
    try {
      dao.addTopic(topic);
      getResponse().setStatus(Status.SUCCESS_CREATED);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, e.getMessage(), e);
    } finally {
      try {
        dao.close();
      } catch (Exception e) {
        logger.error(e.getMessage());
      }
    }

    logger.debug("addTopic() broadcastMeeting with meetingId = " + topic.getMeeting().getId());
    MeetingEndpoint.broadcastMeeting(topic.getMeeting().getId());
  }

  @Delete
  public void deleteTopic() {
    long topicId = Long.parseLong(getRequestAttributes().get("topic-id").toString());
    Topic topic;
    try {
      topic = dao.findByTopicId(topicId);
      dao.deleteTopic(topic);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, e.getMessage(), e);
    } finally {
      try {
        dao.close();
      } catch (Exception e) {
        logger.error(e.getMessage());
      }
    }

    MeetingEndpoint.broadcastMeeting(topic.getMeeting().getId());
  }

}
