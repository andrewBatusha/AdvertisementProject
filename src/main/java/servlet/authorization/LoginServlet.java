package servlet.authorization;

import DAO.impl.DBWorkConnector;
import DAO.impl.UserJdbcDao;
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
        session.setAttribute("invalidCredential", !validCredential);
        if(validCredential){
            session.setAttribute("user", user);
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30*60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30*60);
            response.addCookie(userName);
            request.getRequestDispatcher("/myAdvertisement").forward(request, response);
        }else{
            request.getRequestDispatcher("view/authorization/login.jsp").forward(request, response);
        }
    }
}