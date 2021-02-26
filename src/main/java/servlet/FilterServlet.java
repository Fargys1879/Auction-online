package servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterServlet implements Filter {
    private FilterConfig filterConfig;
    public static boolean authorizationUser = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
            if (authorizationUser == true) {
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            } else {
                // Перенаправление на страницу login.jsp
                ServletContext ctx = filterConfig.getServletContext();
                HttpServletResponse response =(HttpServletResponse) servletResponse;
                response.sendRedirect("/login");
                return;
            }
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
