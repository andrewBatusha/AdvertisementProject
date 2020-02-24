package servlet.management;

import DAO.impl.AdvertisementJdbcDao;
import DAO.impl.DBWorkConnector;
import enums.Status;
import model.Advertisement;
import service.AdvertisementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/manageServlet")
public class ManageServlet extends HttpServlet {
    AdvertisementService advertisementService = new AdvertisementService(new AdvertisementJdbcDao(new DBWorkConnector()));

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String button = request.getParameter("button");
        switch (button) {
            case "denied":
                denied(request,response);
                break;
            case "approved":
                approved(request,response);
                break;
        }

            doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Advertisement> advertisements = advertisementService.getAllAdvertisements();
        request.setAttribute("advertisements", advertisements);
        List<String> date = new ArrayList<>();
        advertisements.forEach(x -> date.add(x.getDateForView()));
        request.setAttribute("dateOfPublishing", date);
        request.getRequestDispatcher("view/manageAdvertisement/manage.jsp").forward(request, response);
    }


    private void approved(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Advertisement advertisement = advertisementService.getAdvertisement(Integer.parseInt(id));
        advertisementService.changeAdvertisementStatus(advertisement,Status.APPROVED);
    }

    private void denied(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Advertisement advertisement = advertisementService.getAdvertisement(Integer.parseInt(id));
        advertisementService.changeAdvertisementStatus(advertisement,Status.DENIED);
    }
}


