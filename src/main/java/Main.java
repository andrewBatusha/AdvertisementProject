import DAO.impl.AdvertisementJdbcDao;
import DAO.impl.DBWorkConnector;
import DAO.impl.UserJdbcDao;
import com.sun.javaws.IconUtil;
import enums.Status;
import enums.Theme;
import model.Advertisement;
import service.AdvertisementService;
import service.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        Advertisement adv = new Advertisement(1, "lol", "kek", "3030", "lol", Theme.HOBBY, Status.WAITING, false, 1, LocalDateTime.now());
        //new AdvertisementJdbcDao(new DBWorkConnector()).createAdvertisementTable();
        AdvertisementService advertisementService = new AdvertisementService(new AdvertisementJdbcDao(new DBWorkConnector()));
        advertisementService.addAdvertisement(adv);
        advertisementService.getAllAdvertisements().forEach(System.out::println);
    }

}
