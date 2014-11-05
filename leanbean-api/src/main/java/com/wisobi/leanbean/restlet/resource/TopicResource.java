package com.wisobi.leanbean.restlet.resource;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;
import com.wisobi.leanbean.jpa.entity.Topic;

import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Created by bjork on 22/10/14.
 */
public class TopicResource extends ServerResource {

  private LeanBeanDao dao = new LeanBeanJpaDao();

  @Post("json")
  public void addTopic(Topic topic) {

    try {
      dao.addTopic(topic);
      getResponse().setStatus(Status.SUCCESS_CREATED);
    } catch (Exception e) {
      throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, e.getMessage(), e);
    }
  }

}
