package com.wisobi.leanbean.builder;

import com.wisobi.leanbean.jpa.entity.Device;

/**
 * Created by bjork on 15/12/14.
 */
public class DeviceBuilder {

  private Device device;

  public DeviceBuilder() {
    device = new Device();
  }

  public DeviceBuilder id(long id) {
    this.device.setId(id);
    return this;
  }

  public DeviceBuilder alias(String alias) {
    this.device.setAlias(alias);
    return this;
  }

  public DeviceBuilder uuid(String uuid) {
    this.device.setUuid(uuid);
    return this;
  }

  public Device build() {
    Device returnDevice = this.device;
    this.device = new Device();
    return returnDevice;
  }


}
