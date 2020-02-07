import enums.Role;
import model.Advertisement;
import model.User;
import service.AdvertisementService;
import service.UserService;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        AdvertisementService advertisementService = new AdvertisementService();
        UserService userService = new UserService();

        User user = new User(1, "andrew", "batuiev", "dkada", "12345", Role.ADMIN, "skylex", null);
        userService.addUser(user);
    }
}
