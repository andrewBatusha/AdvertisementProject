import DAO.DBConnector;
import enums.Role;
import enums.Status;
import enums.Theme;
import model.Advertisement;
import model.User;
import service.AdvertisementService;
import service.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        AdvertisementService advertisementService = new AdvertisementService();
        UserService userService = new UserService();

        User user = new User(32,"andrew","batuiev","@gmail.com","password", Role.ADMIN,null);
        UserService.addUser(user);
       // System.out.println(UserService.getUser(32));
        System.out.println(AdvertisementService.getAdvertisement(5));
        Advertisement adv = new Advertisement(1, "lol", "kek", "3030", "lol", Theme.HOBBY, Status.WAITING, false, 3, LocalDateTime.now());
        //AdvertisementService.addAdvertisement(adv);
        //AdvertisementService.getAllAdvertisements().forEach(System.out::println);
        UserService.getAllUsers().forEach(System.out::println);
    }
}
