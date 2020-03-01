import DAO.impl.AdvertisementJdbcDao;
import DAO.impl.DBWorkConnector;
import DAO.impl.UserJdbcDao;
import enums.Role;
import model.Advertisement;
import model.User;
import service.AdvertisementService;
import service.UserService;

public class SetUp {
    private UserService userService = new UserService(new UserJdbcDao(new DBWorkConnector()));
    private AdvertisementService advService = new AdvertisementService(new AdvertisementJdbcDao(new DBWorkConnector()));
    public SetUp() {
        userService.createTable();
        advService.createTable();
        generateUsers();
    }

    private void generateUsers() {
        userService.addUser(new User
                (1,
                        "userName",
                        "userSurname",
                        "user@gmail.com",
                        "dXNlcg==",
                        Role.USER,
                        false,
                        "b49844b1d5754ced8a034cc6af11a462",
                        true
                ));
        userService.addUser(new User
                (2,
                        "managerName",
                        "managerSurname",
                        "manager@gmail.com",
                        "bWFuYWdlcg==",
                        Role.MANAGER,
                        false,
                        "4fd264b4709d4cbea58fa389aba3fdb8",
                        true
                ));
        userService.addUser(new User
                (3,
                        "adminName",
                        "adminSurname",
                        "admin@gmail.com",
                        "YWRtaW4=",
                        Role.ADMIN,
                        false,
                        "8a3719a144a944dd80af9118f2cff28c",
                        true
                ));
    }
    private void generateAdv(){
    }
}
