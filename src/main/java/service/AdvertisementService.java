package service;

import DAO.IAdvertisementDao;
import DAO.impl.AdvertisementJdbcDao;
import enums.Status;
import enums.Theme;
import model.Advertisement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdvertisementService {
    private static IAdvertisementDao<Advertisement> advertisementJdbcDao = new AdvertisementJdbcDao();

    public static boolean addAdvertisement(Advertisement advertisement) throws SQLException, IOException {
        if(!advertisementJdbcDao.isEntityExistInDatabase(advertisement.getIdAdvertisement()))
            advertisementJdbcDao.insert(advertisement);
        return true;
    }

    public static boolean deleteAdvertisement(int id) throws SQLException, IOException {
        if(advertisementJdbcDao.isEntityExistInDatabase(id))
            advertisementJdbcDao.deleteByID(id);
        return true;
    }

    public static boolean changeAdvertisementStatus(Advertisement advertisement, Status status) throws SQLException, IOException {
        advertisement.setStatus(status);
        advertisementJdbcDao.update(advertisement);
        return true;
    }
    public static List<Advertisement> getAllAdvertisements() throws SQLException, IOException {
        return advertisementJdbcDao.selectAll();
    }

    public static Advertisement getAdvertisement(int id) throws SQLException, IOException{
        return advertisementJdbcDao.selectByID(id);
    }

    public static List<Advertisement> getAdvertisementsByTheme(Theme theme) throws SQLException, IOException{
        return advertisementJdbcDao.selectAllByTheme(theme);
    }
}