package com.wisobi.leanbean.restlet.resource;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bjork on 12/12/14.
 */
public class LogLoginResource extends ServerResource {

  final static Logger logger = LoggerFactory.getLogger(LogLoginResource.class);

  private LeanBeanDao dao = new LeanBeanJpaDao();

  @Post("json")
  public void addLogLogin() {

  }


}
