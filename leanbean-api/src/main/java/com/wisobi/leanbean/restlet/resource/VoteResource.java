package com.wisobi.leanbean.restlet.resource;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.dto.DTO2DAOMapper;
import com.wisobi.leanbean.dto.VoteDTO;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;
import com.wisobi.leanbean.jpa.entity.Vote;

import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Created by bjork on 04/11/14.
 */
public class VoteResource extends ServerResource {

  private LeanBeanDao dao = new LeanBeanJpaDao();

  @Post("json")
  public void addTopic(VoteDTO voteDTO) {
    Vote vote = DTO2DAOMapper.mapVote(voteDTO);
    try {
      dao.addVote(vote);
      getResponse().setStatus(Status.SUCCESS_CREATED);
    } catch (Exception e) {
      throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, e.getMessage(), e);
    }
  }

}
