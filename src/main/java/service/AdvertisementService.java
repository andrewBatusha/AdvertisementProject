package service;

import DAO.AdvertisementJdbcDao;
import DAO.IAdvertisementDao;
import enums.Status;
import enums.Theme;
import model.Advertisement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdvertisementService {
    private static IAdvertisementDao<Advertisement> advertisementJdbcDao = new AdvertisementJdbcDao();

    public void addAdvertisement(Advertisement advertisement) throws SQLException, IOException {
        if(!advertisementJdbcDao.isEntityExistInDatabase(advertisement.getIdAdvertisement()))
            advertisementJdbcDao.insert(advertisement);
    }

    public void deleteAdvertisement(int id) throws SQLException, IOException {
        if(advertisementJdbcDao.isEntityExistInDatabase(id))
            advertisementJdbcDao.deleteByID(id);
    }

    public void changeAdvertisementStatus(Advertisement advertisement, Status status) throws SQLException, IOException {
        advertisement.setStatus(status);
        advertisementJdbcDao.update(advertisement);
    }
    public List<Advertisement> getAllAdvertisements() throws SQLException, IOException {
        return advertisementJdbcDao.selectAll();
    }
    public Advertisement getUser(int id) throws SQLException, IOException{
        return advertisementJdbcDao.selectByID(id);
    }

    public List<Advertisement> getAdvertisementsByTheme(Theme theme) throws SQLException, IOException{
        return advertisementJdbcDao.selectAllByTheme(theme);
    }
}