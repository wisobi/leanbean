package com.wisobi.leanbean.jetty;

import com.wisobi.leanbean.endpoint.MeetingEndpoint;
import com.wisobi.leanbean.restlet.LeanBeanApplication;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;
//import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.server.ServerContainer;

/**
 * Created by bjork on 30/06/15.
 */
public class LeanBeanServer {

    public static void main(String[] args)
    {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Add the LeanBean Web Application
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar("/opt/leanbean/leanbean-api/target/leanbean-api-1.0-SNAPSHOT.war");
        server.setHandler(webapp);

        /*
        try
        {
            // Initialize javax.websocket layer and add WebSocket endpoint to it
            ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(context);
            wscontainer.addEndpoint(MeetingEndpoint.class);

            server.start();
            server.join();
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
        */
    }
}
