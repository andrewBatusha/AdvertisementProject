package servlet.authorization;

import DAO.impl.DBWorkConnector;
import DAO.impl.UserJdbcDao;
import enums.Role;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    UserService userService = new UserService(new UserJdbcDao(new DBWorkConnector()));
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        boolean validCredential = userService.isUserAuthorized(user, pwd);
        boolean banStatus = userService.isUserBanned(user);
        boolean activated = userService.isUserActivated(user);
        session.setAttribute("invalidCredential", !validCredential);
        session.setAttribute("banStatus", banStatus);
        session.setAttribute("activatedStatus", !activated);
        if(validCredential && !banStatus && activated){
            User authorizedUser = userService.getUserByEmail(user);
            session.setAttribute("user", authorizedUser.getName() + " " + authorizedUser.getSurname());
            session.setAttribute("role", authorizedUser.getRole());
            session.setAttribute("email", authorizedUser.getEmail());
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30*60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30*60);
            response.addCookie(userName);
            if (session.getAttribute("role") == Role.USER) {
                response.sendRedirect("/myAdvertisement");
            } else if(session.getAttribute("role") == Role.MANAGER){
                response.sendRedirect("/manageServlet");
            } else if(session.getAttribute("role") == Role.ADMIN){
                response.sendRedirect("/adminServlet");
            }
        }else{
            request.getRequestDispatcher("view/authorization/login.jsp").forward(request, response);
        }
    }
}