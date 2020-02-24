package servlet.filter.RoleFilter;

import enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "ManagerRoleFilter", urlPatterns = {"/manageServlet"})
public class ManagerRoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = ((HttpServletRequest) request).getSession();
        if(session.getAttribute("role") != Role.MANAGER) {
            httpServletResponse.sendRedirect("view/include/errorPage.jsp");
        }else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}