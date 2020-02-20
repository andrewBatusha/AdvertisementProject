package service;

import DAO.IAdvertisementDao;
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
        } catch (SQLException e) {
            logger.error("addAdvertisement(Advertisement advertisement)" +e);
        } catch (IOException e) {
            logger.error("addAdvertisement(Advertisement advertisement)" +e);
        }
        return true;
    }

    public boolean deleteAdvertisement(int id) {
        try {
            advertisementJdbcDao.deleteByID(id);
        } catch (SQLException e) {
            logger.error("deleteAdvertisement(int id)" + e);
        } catch (IOException e) {
            logger.error("deleteAdvertisement(int id)" + e);
        }
        return true;
    }

    public boolean changeAdvertisementStatus(Advertisement advertisement, Status status){
        advertisement.setStatus(status);
        try {
            advertisementJdbcDao.update(advertisement);
        } catch (SQLException e) {
            logger.error("changeAdvertisementStatus(Advertisement advertisement, Status status)" + e);
        } catch (IOException e) {
            logger.error("changeAdvertisementStatus(Advertisement advertisement, Status status)" + e);
        }
        return true;
    }

    public List<Advertisement> getAllAdvertisements(){
        List<Advertisement> list = null;
        try {
            list = new ArrayList<>(advertisementJdbcDao.selectAll());
        } catch (SQLException e) {
            logger.error("getAllAdvertisements()" + e);
        } catch (IOException e) {
            logger.error("getAllAdvertisements()" + e);
        }
        return list;
    }

    public Advertisement getAdvertisement(int id){
        Advertisement adv = null;
        try {
            adv = advertisementJdbcDao.selectByID(id);
        } catch (SQLException e) {
            logger.error("getAdvertisement()" + e);
        } catch (IOException e) {
            logger.error("getAdvertisement()" + e);
        }
        return adv;
    }

    public List<Advertisement> getAdvertisementsByTheme(Theme theme) {
        List<Advertisement> list = null;
        try {
            list = new ArrayList<>(advertisementJdbcDao.selectAllByTheme(theme));
        } catch (SQLException e) {
            logger.error("getAdvertisementsByTheme()" + e);
        } catch (IOException e) {
            logger.error("getAdvertisementsByTheme()" + e);
        }
        return  list;
    }
}