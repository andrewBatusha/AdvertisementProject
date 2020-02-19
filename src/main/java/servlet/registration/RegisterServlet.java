package servlet.registration;

import DAO.impl.DBWorkConnector;
import DAO.impl.UserJdbcDao;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserService(new UserJdbcDao(new DBWorkConnector()));

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // get request parameters for userID and password
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        String confirmPassword = request.getParameter("confirmPassword");
        boolean validCredential = !userService.isUserExist(user.getEmail());
        boolean confirmedPassword = user.getPassword().equals(confirmPassword);
        session.setAttribute("invalidCredential", !validCredential);
        session.setAttribute("unconfirmedPassword", !confirmedPassword);
        if(validCredential & confirmedPassword){
            try {
                userService.addUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session.setAttribute("user", user.getName());
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30*60);
            Cookie userName = new Cookie("user", user.getName());
            userName.setMaxAge(30*60);
            response.addCookie(userName);
            response.sendRedirect("/advertisement");
        }else{
            request.getRequestDispatcher("view/registration/register.jsp").forward(request, response);
        }
    }
}