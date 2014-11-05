package com.wisobi.leanbean.restlet;

import com.wisobi.leanbean.restlet.resource.MeetingResource;
import com.wisobi.leanbean.restlet.resource.TopicResource;
import com.wisobi.leanbean.restlet.resource.UserResource;
import com.wisobi.leanbean.restlet.resource.VoteResource;

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
    router.attach("/v1/meeting/", MeetingResource.class);
    router.attach("/v1/meeting/{meeting-id}", MeetingResource.class);
    router.attach("/v1/user/", UserResource.class);
    router.attach("/v1/topic/", TopicResource.class);
    router.attach("/v1/vote/", VoteResource.class);
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
