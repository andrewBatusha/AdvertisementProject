package servlet.registration;

import DAO.impl.DBWorkConnector;
import DAO.impl.UserJdbcDao;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/ConfirmedPage")
public class ConfirmedRegistration extends HttpServlet {
    UserService userService = new UserService(new UserJdbcDao(new DBWorkConnector()));
    private String userId;
    private String token;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (userService.isUserRegistratedCorrectly(Integer.parseInt(userId), token)) {
            User authorizedUser = userService.getUser(Integer.parseInt(userId));
            userService.changeUserActivatedStatus(authorizedUser, true);
            session.setAttribute("user", authorizedUser.getName() + " " + authorizedUser.getSurname());
            session.setAttribute("role", authorizedUser.getRole());
            session.setAttribute("email", authorizedUser.getEmail());
        }
        response.sendRedirect(request.getContextPath() +"/advertisement");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userId = request.getQueryString().replaceAll("userID=", "");
        userId = userId.substring(0, userId.indexOf("&"));
        token = request.getQueryString().substring(request.getQueryString().indexOf('&') + 1).replaceAll("token=", "");


        request.getRequestDispatcher("view/registration/confirmed.jsp").forward(request, response);
    }


}