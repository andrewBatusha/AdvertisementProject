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
        SetUp setUp = new SetUp();
    }

}
