package com.wisobi.leanbean.restlet.resource;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.dto.DTO2DAOMapper;
import com.wisobi.leanbean.dto.VoteTO;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;
import com.wisobi.leanbean.jpa.entity.Vote;

import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by bjork on 04/11/14.
 */
public class VoteResource extends ServerResource {

  final static Logger logger = LoggerFactory.getLogger(VoteResource.class);

  private LeanBeanDao dao = new LeanBeanJpaDao();

  @Post("json")
  public void addVotes(VoteTO voteTO) {
    Set<Vote> votes = DTO2DAOMapper.mapVotes(voteTO);
    try {
      for(Vote vote : votes) {
        dao.addVote(vote);
      }
      getResponse().setStatus(Status.SUCCESS_CREATED);
    } catch (Exception e) {
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
