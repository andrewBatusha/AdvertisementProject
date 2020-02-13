package service;

import DAO.IAdvertisementDao;
import enums.Status;
import enums.Theme;
import model.Advertisement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdvertisementService {
    private IAdvertisementDao<Advertisement> advertisementJdbcDao;

    public AdvertisementService(IAdvertisementDao<Advertisement> advertisementJdbcDao) {
        this.advertisementJdbcDao = advertisementJdbcDao;
    }

    public boolean addAdvertisement(Advertisement advertisement) throws SQLException, IOException {
        if (!advertisementJdbcDao.isEntityExistInDatabase(advertisement.getIdAdvertisement()))
            advertisementJdbcDao.insert(advertisement);
        return true;
    }

    public boolean deleteAdvertisement(int id) throws SQLException, IOException {
        if (advertisementJdbcDao.isEntityExistInDatabase(id))
            advertisementJdbcDao.deleteByID(id);
        return true;
    }

    public boolean changeAdvertisementStatus(Advertisement advertisement, Status status) throws SQLException, IOException {
        advertisement.setStatus(status);
        advertisementJdbcDao.update(advertisement);
        return true;
    }

    public List<Advertisement> getAllAdvertisements() throws SQLException, IOException {
        return advertisementJdbcDao.selectAll();
    }

    public Advertisement getAdvertisement(int id) throws SQLException, IOException {
        return advertisementJdbcDao.selectByID(id);
    }

    public List<Advertisement> getAdvertisementsByTheme(Theme theme) throws SQLException, IOException {
        return advertisementJdbcDao.selectAllByTheme(theme);
    }
}