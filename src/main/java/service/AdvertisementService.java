package service;

import DAO.interfaces.IAdvertisementDao;
import enums.Status;
import enums.Theme;
import model.Advertisement;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementService {
    private IAdvertisementDao<Advertisement> advertisementJdbcDao;
    final static Logger logger = Logger.getLogger(AdvertisementService.class);
    public AdvertisementService(IAdvertisementDao<Advertisement> advertisementJdbcDao) {
        this.advertisementJdbcDao = advertisementJdbcDao;
    }

    public boolean addAdvertisement(Advertisement advertisement) {
        try {
            advertisementJdbcDao.insert(advertisement);
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return true;
    }

    public boolean deleteAdvertisement(int id) {
        try {
            advertisementJdbcDao.deleteByID(id);
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return true;
    }

    public boolean changeAdvertisementStatus(Advertisement advertisement, Status status){
        advertisement.setStatus(status);
        try {
            advertisementJdbcDao.update(advertisement);
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return true;
    }



    public List<Advertisement> getAllAdvertisements(){
        List<Advertisement> list = null;
        try {
            list = new ArrayList<>(advertisementJdbcDao.selectAll());
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return list;
    }

    public Advertisement getAdvertisement(int id){
        Advertisement adv = null;
        try {
            adv = advertisementJdbcDao.selectByID(id);
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return adv;
    }

    public List<Advertisement> getAdvertisementsByTheme(Theme theme) {
        List<Advertisement> list = null;
        try {
            list = new ArrayList<>(advertisementJdbcDao.selectAllByTheme(theme));
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return  list;
    }

    public boolean createTable(){
        boolean f = false;
        try {
            f = advertisementJdbcDao.createTable();
        } catch (IOException e) {
            logger.error("Unexpected error", e);
        }
        return f;
    }
}