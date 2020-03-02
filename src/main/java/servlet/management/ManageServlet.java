package servlet.management;

import DAO.impl.AdvertisementJdbcDao;
import DAO.impl.DBWorkConnector;
import DAO.impl.UserJdbcDao;
import enums.Status;
import model.Advertisement;
import service.AdvertisementService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/manageServlet")
public class ManageServlet extends HttpServlet {
    AdvertisementService advertisementService = new AdvertisementService(new AdvertisementJdbcDao(new DBWorkConnector()));
    UserService userService = new UserService(new UserJdbcDao(new DBWorkConnector()));

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String button = request.getParameter("button");
        String id = request.getParameter("id");
        Advertisement advertisement = advertisementService.getAdvertisement(Integer.parseInt(id));
        switch (button) {
            case "denied":
                session.setAttribute("email", userService.getUser(userService.getUserIdByAdv(advertisement)).getEmail());
                advertisementService.changeAdvertisementStatus(advertisement, Status.DENIED);
                response.sendRedirect(request.getContextPath() +"/explanatoryPage");
                break;
            case "approved":
                advertisementService.changeAdvertisementStatus(advertisement, Status.APPROVED);
                doGet(request, response);
                break;
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Advertisement> advertisements = advertisementService.getAllAdvertisements();
        request.setAttribute("advertisements", advertisements);
        List<String> date = new ArrayList<>();
        advertisements.forEach(x -> date.add(x.getDateForView()));
        request.setAttribute("dateOfPublishing", date);
        request.getRequestDispatcher("view/manageAdvertisement/manage.jsp").forward(request, response);
    }
}


