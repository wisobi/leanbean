package com.wisobi.leanbean.restlet.resource;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.dto.DTO2DAOMapper;
import com.wisobi.leanbean.dto.UserDTO;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;
import com.wisobi.leanbean.jpa.entity.User;

import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Created by bjork on 04/11/14.
 */
public class UserResource extends ServerResource {

  private LeanBeanDao dao = new LeanBeanJpaDao();

  @Post("json")
  public void addUser(UserDTO userDTO) {
    User user = DTO2DAOMapper.mapUser(userDTO);

    try {
      dao.addUser(user);
      getResponse().setStatus(Status.SUCCESS_CREATED);
    } catch (Exception e) {
      throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, e.getMessage(), e);
    }

  }

}
