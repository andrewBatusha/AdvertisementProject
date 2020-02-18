import DAO.impl.AdvertisementJdbcDao;
import DAO.impl.DBWorkConnector;
import DAO.impl.UserJdbcDao;
import enums.Role;
import enums.Theme;
import model.Advertisement;
import model.User;
import service.AdvertisementService;
import service.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        UserService userService = new UserService(new UserJdbcDao(new DBWorkConnector()));
        userService.addUser(new User(2,"andrew", "batuiev", "andriicooll495@gmail.com", "root", Role.ADMIN));
       AdvertisementService advertisementService = new AdvertisementService(new AdvertisementJdbcDao(new DBWorkConnector()));
        advertisementService.addAdvertisement(new Advertisement(1,"dobrogo dnya","ajhdajdhahdjadhjadhjad","Mail","0303303", Theme.ART, true, 57, LocalDateTime.now()));
//        advertisementService.addAdvertisement(new Advertisement(2,"dobryi den","ajhdsdkjfskfjskfjadhjadhjad","Mail","0303303", Theme.ART, true, 56, LocalDateTime.now()));
//        advertisementService.addAdvertisement(new Advertisement(3,"dobrogo dnya","ajhaiuwdmlaksddhjad","Mail","0303303", Theme.ART, true, 56, LocalDateTime.now()));
    }

}
