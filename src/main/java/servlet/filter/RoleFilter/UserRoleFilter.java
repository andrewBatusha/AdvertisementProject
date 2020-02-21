package servlet.filter.RoleFilter;


import com.mysql.cj.Session;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "validateFilter", urlPatterns = {"/myAdvertisement", "/CreatingPage"})
public class UserRoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = ((HttpServletRequest) request).getSession();
        if(session.getAttribute("user") == null) {
            httpServletResponse.sendRedirect("/loginPage");
        }else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}