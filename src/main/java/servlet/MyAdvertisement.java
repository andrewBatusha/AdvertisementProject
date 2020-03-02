package servlet;

import DAO.impl.DBWorkConnector;
import DAO.impl.UserJdbcDao;
import model.Advertisement;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/myAdvertisement")
public class MyAdvertisement extends HttpServlet {
    UserService userService = new UserService(new UserJdbcDao(new DBWorkConnector()));
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Advertisement> advertisements = null;
        HttpSession session = request.getSession();
        try {
            advertisements = userService.getMyAdvertisement(userService.getUserByEmail((String)session.getAttribute("email")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("advertisements", advertisements);
        request.getRequestDispatcher("view/authorization/myAdvertisement.jsp").forward(request,response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Advertisement> advertisements = null;
        HttpSession session = request.getSession();
        try {
           advertisements = userService.getMyAdvertisement(userService.getUserByEmail((String)session.getAttribute("email")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("advertisements", advertisements);
        request.getRequestDispatcher("view/authorization/myAdvertisement.jsp").forward(request,response);
    }
}