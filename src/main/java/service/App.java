package service;
import com.sun.jersey.spi.container.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

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
        servletHolder.setInitOrder(1);
        context.addServlet(servletHolder, "/*");
        server.start();
        server.join();

    }
}
