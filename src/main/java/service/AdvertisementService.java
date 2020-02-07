package service;

import DAO.AdvertisementJdbcDao;
import DAO.IAdvertisementDao;
import model.Advertisement;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

public class AdvertisementService {
    private static IAdvertisementDao<Advertisement> advertisementJdbcDao = new AdvertisementJdbcDao();

    void addAdvertisement(User user, Advertisement advertisement) {
        user.getAdvertisements().add(advertisement);
    }

    void deleteAdvertisement(User user, Advertisement advertisement) {
        user.getAdvertisements().remove(advertisement);
    }

    void sendForVerification(Advertisement advertisement) throws SQLException, IOException {
        advertisementJdbcDao.insert(advertisement);
    }
}