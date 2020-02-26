package servlet.administration;

import DAO.impl.DBWorkConnector;
import DAO.impl.UserJdbcDao;
import enums.Role;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/adminServlet")
public class AdminServlet extends HttpServlet {
    UserService userService = new UserService(new UserJdbcDao(new DBWorkConnector()));

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String button = request.getParameter("button");
        String id = request.getParameter("id");
        User user = userService.getUser(Integer.parseInt(id));
        switch (button) {
            case "ban":
                userService.changeUserBanStatus(user, true);
                break;
            case "unban":
                userService.changeUserBanStatus(user, false);
                break;
            case "promote":
                userService.changeUserRole(user, Role.MANAGER);
                break;
            case "demote":
                userService.changeUserRole(user, Role.USER);
                break;
        }
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService(new UserJdbcDao(new DBWorkConnector()));
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("view/administration/admin.jsp").forward(request, response);
    }
}