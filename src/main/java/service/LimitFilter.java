package service;
import javax.servlet.*;
import java.io.IOException;

public class LimitFilter implements javax.servlet.Filter {
    private int limit = 5;
    private int count;
    private Object lock = new Object();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            boolean ok;
            synchronized (lock) {
                ok = count++ < limit;
            }
            if (ok) {
                // let the request through and process as usual
//                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                // handle limit case, e.g. return status code 429 (Too Many Requests)
                // see http://tools.ietf.org/html/rfc6585#page-3
                System.out.println("Code 429");
            }
        } finally {
            synchronized (lock) {
                count--;
            }
        }
    }
    @Override
    public void destroy() {

    }
}
