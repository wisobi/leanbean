package com.wisobi.leanbean.restlet.resource;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.dto.DAO2DTOMapper;
import com.wisobi.leanbean.dto.DTO2DAOMapper;
import com.wisobi.leanbean.dto.DeviceTO;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;
import com.wisobi.leanbean.jpa.entity.Device;

import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bjork on 04/11/14.
 */
public class DeviceResource extends ServerResource {

  final static Logger logger = LoggerFactory.getLogger(DeviceResource.class);

  private LeanBeanDao dao = new LeanBeanJpaDao();

  @Get("json")
  public void getDevice(String uuid) {

  }

  @Post("json")
  public DeviceTO addDevice(DeviceTO deviceTO) {
    Device device = DTO2DAOMapper.mapDevice(deviceTO);

    try {
      dao.addDevice(device);
      getResponse().setStatus(Status.SUCCESS_CREATED);
    } catch (Exception e) {
      logger.debug(e.getMessage());
      throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, e.getMessage(), e);
    } finally {
      try {
        dao.close();
      } catch (Exception e) {
        logger.error(e.getMessage());
      }
    }

    return DAO2DTOMapper.mapDevice(device);
  }

  @Put("json")
  public DeviceTO updateDevice(DeviceTO deviceTO) {
    Device device = DTO2DAOMapper.mapDevice(deviceTO);

    try {
      device = dao.updateDevice(device);
      getResponse().setStatus(Status.SUCCESS_CREATED);
    } catch (Exception e) {
      logger.debug(e.getMessage());
      throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, e.getMessage(), e);
    } finally {
      try {
        dao.close();
      } catch (Exception e) {
        logger.debug(e.getMessage());
      }
    }

    return DAO2DTOMapper.mapDevice(device);
  }

}
