package servlet.registration;

import DAO.impl.DBWorkConnector;
import DAO.impl.UserJdbcDao;
import email.TLSEmail;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserService(new UserJdbcDao(new DBWorkConnector()));

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        // get request parameters for userID and password
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setEmail(request.getParameter("email").toLowerCase());
        user.setPassword(request.getParameter("password"));
        user.setToken(token);
        String confirmPassword = request.getParameter("confirmPassword");
        boolean validCredential = !userService.isUserExist(user.getEmail());
        boolean confirmedPassword = user.getPassword().equals(confirmPassword);
        session.setAttribute("invalidCredential", !validCredential);
        session.setAttribute("unconfirmedPassword", !confirmedPassword);
        if (validCredential && confirmedPassword) {
            userService.addUser(user);
            String uri = "http://localhost:8081/ConfirmedPage?userID=" + userService.getUserByEmail(user.getEmail()).getId() + "&token=" + token;
            TLSEmail.sendMessage(user.getEmail(), "Confirm registration in AdvMe", "activate this token:" + uri);
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30 * 60);
            response.sendRedirect(request.getContextPath() + "/advertisement");
        } else {
            request.getRequestDispatcher("view/registration/register.jsp").forward(request, response);
        }
    }
}