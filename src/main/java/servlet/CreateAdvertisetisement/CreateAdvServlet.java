package servlet.CreateAdvertisetisement;

import DAO.impl.AdvertisementJdbcDao;
import DAO.impl.DBWorkConnector;
import DAO.impl.UserJdbcDao;
import enums.Theme;
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
import java.time.LocalDateTime;

@WebServlet("/CreateServlet")
public class CreateAdvServlet extends HttpServlet {
    AdvertisementService advertisementService = new AdvertisementService(new AdvertisementJdbcDao(new DBWorkConnector()));
    UserService userService = new UserService(new UserJdbcDao(new DBWorkConnector()));

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Advertisement adv = new Advertisement();
        adv.setHeadline(request.getParameter("headline"));
        adv.setDescription(request.getParameter("description"));
        adv.setTheme(Theme.valueOf(request.getParameter("statusList")));
        adv.setNumber(request.getParameter("phone"));
        adv.setDateOfPublished(LocalDateTime.now());
        adv.setIdUser(userService.getUserByEmail((String) session.getAttribute("email")).getId());
        advertisementService.addAdvertisement(adv);
        response.sendRedirect("/advertisement");
    }
}