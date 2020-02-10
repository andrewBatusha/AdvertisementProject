import enums.Status;
import enums.Theme;
import model.Advertisement;
import service.AdvertisementService;
import service.UserService;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        AdvertisementService advertisementService = new AdvertisementService();
        UserService userService = new UserService();

        //User user = new User(1, "andrew", "batuiev", "dkada", "12345", Role.ADMIN, "skylex", null);
        Advertisement adv = new Advertisement(1, "lol", "kek", "3030", "lol", Theme.HOBBY, Status.WAITING, false, 3);
       // advertisementService.addAdvertisement(adv);
        //advertisementService.changeAdvertisementStatus(adv, Status.APPROVED);
        //advertisementService.deleteAdvertisement(3);
        advertisementService.getAdvertisementsByTheme(Theme.HOBBY).forEach(System.out::println);
    }
}
