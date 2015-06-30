package com.wisobi.leanbean.restlet;

import com.wisobi.leanbean.restlet.resource.*;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 * Created by bjork on 31/08/14.
 */
public class LeanBeanApplication extends Application {

  /**
   * Creates a root Restlet that will receive all incoming calls.
   */
  @Override
  public Restlet createInboundRoot() {
    Router router = new Router(getContext());
    router.attach("/v1/meetings/", MeetingResource.class);
    router.attach("/v1/meetings/{meeting-id}", MeetingResource.class);
    router.attach("/v1/meetings/{meeting-id}/devices/{device-id}/votes/", VoteResource.class);
    router.attach("/v1/devices/{device-id}/meetings/", BrowseMeetingResource.class);
    router.attach("/v1/devices/", DeviceResource.class);
    router.attach("/v1/devices/{device-id}", DeviceResource.class);
    router.attach("/v1/device-uuid/{device-uuid}", DeviceUUIDResource.class);
    router.attach("/v1/topics/", TopicResource.class);
    router.attach("/v1/topics/{topic-id}", TopicResource.class);

    router.attach("/v1/bootstrap", LeanBeanBootstrap.class);
    return router;
  }

  public static void main(String[] args) {
    try {
      // Create a new Component.
      Component component = new Component();

      // Add a new HTTP server listening on port 8182.
      component.getServers().add(Protocol.HTTP, 8182);

      // Attach the sample application.
      component.getDefaultHost().attach(new LeanBeanApplication());

      // Start the component.
      component.start();
    } catch (Exception e) {
      // Something is wrong.
      e.printStackTrace();
    }
  }

}
