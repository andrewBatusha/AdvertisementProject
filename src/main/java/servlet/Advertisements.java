package servlet;

import DAO.impl.AdvertisementJdbcDao;
import DAO.impl.DBWorkConnector;
import model.Advertisement;
import service.AdvertisementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/advertisement")
public class Advertisements extends HttpServlet {
    AdvertisementService advertisementService = new AdvertisementService(new AdvertisementJdbcDao(new DBWorkConnector()));
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Advertisement> advertisements = advertisementService.getAllAdvertisements();
            request.setAttribute("advertisements", advertisements);
            request.getRequestDispatcher("view/advertisements/advertisement.jsp").forward(request,response);
    }
}