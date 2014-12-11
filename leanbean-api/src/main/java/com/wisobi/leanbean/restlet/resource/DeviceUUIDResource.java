package com.wisobi.leanbean.restlet.resource;

import com.wisobi.leanbean.LeanBeanDao;
import com.wisobi.leanbean.dto.DAO2DTOMapper;
import com.wisobi.leanbean.dto.DTO2DAOMapper;
import com.wisobi.leanbean.dto.DeviceTO;
import com.wisobi.leanbean.jpa.LeanBeanJpaDao;
import com.wisobi.leanbean.jpa.entity.Device;

import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bjork on 03/12/14.
 */
public class DeviceUUIDResource extends ServerResource {

  final static Logger logger = LoggerFactory.getLogger(DeviceUUIDResource.class);

  private LeanBeanDao dao = new LeanBeanJpaDao();

  @Get
  public DeviceTO getDeviceByUUID() {
    String uuid = getRequestAttributes().get("device-uuid").toString();
    Device device = dao.findByDeviceUUID(uuid);
    try {
      dao.close();
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    if (device == null) {
      throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
    }
    DeviceTO deviceTO = DAO2DTOMapper.mapDevice(device);
    return deviceTO;
  }

}
