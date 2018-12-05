package service;
import com.sun.jersey.spi.container.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.DoSFilter;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class App {
    public static void main(String[] args) throws Exception{
        String port = System.getenv("PORT") != null ? System.getenv("PORT") : "8080";
        Server server = new Server(Integer.parseInt(port));
        ServletContextHandler context =
                new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        ServletHolder servletHolder =
                new ServletHolder(new ServletContainer());
        servletHolder.setInitParameter(
                "com.sun.jersey.config.property.resourceConfigClass",
                "com.sun.jersey.api.core.PackagesResourceConfig");
        servletHolder.setInitParameter(
                "com.sun.jersey.config.property.packages",
                "service");
        //TODO
        final EnumSet<DispatcherType> REQUEST_SCOPE = EnumSet.of(DispatcherType.REQUEST);
        // For limit
        FilterHolder filterHolder = new FilterHolder( DoSFilter.class );
        filterHolder.setInitParameter("maxRequestsPerSec", "1");  // max requests per second per client
        filterHolder.setInitParameter("delayMs", "-1");           // millisec to delay excess requests. -1 means reject (for testing)
        filterHolder.setInitParameter("remotePort", "false");     // true = track connections by remote ip+port
        filterHolder.setInitParameter("enabled", "true");
        filterHolder.setInitParameter("trackSessions", "true");
        context.addFilter( filterHolder, "/*", REQUEST_SCOPE );
        servletHolder.setInitOrder(1);
        context.addServlet(servletHolder, "/*");
        context.addFilter(LimitFilter.class,"/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        server.start();
        server.join();

    }
}
