package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LimitFilter implements javax.servlet.Filter {
    private static Logger logger;
    private static Marker marker;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger = LoggerFactory.getLogger(this.getClass().getSimpleName() );
        marker = MarkerFactory.getMarker("");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            logger.info( marker, String.format("From %s:%d  %s %s",
                    request.getRemoteAddr(), request.getRemotePort(), request.getMethod(), request.getRequestURI()
                    )
            );
        }
        // pass request to next filter in chain
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {

    }
}
