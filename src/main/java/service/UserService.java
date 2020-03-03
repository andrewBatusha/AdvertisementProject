package service;

import DAO.interfaces.IUserDao;
import enums.Role;
import model.Advertisement;
import model.User;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

public class UserService {
    private IUserDao<User, Advertisement> userJdbcDao;
    private final static Logger logger = Logger.getLogger(UserService.class);

    public UserService(IUserDao<User, Advertisement> userJdbcDao) {
        this.userJdbcDao = userJdbcDao;
    }

    public User getUserByEmail(String email) {
        User user = null;
        try {
            user = userJdbcDao.selectByEmail(email);
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return user;
    }

    public int getUserIdByAdv(Advertisement adv) {
        int id = 0;
        try {
            id = userJdbcDao.getUserByAdvertisement(adv);
        } catch (SQLException e) {
            logger.error("Unexpected error", e);
        }
        return id;
    }

    public boolean addUser(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        try {
            userJdbcDao.insert(user);
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return true;
    }

    public boolean deleteUser(int id) {
        try {
            userJdbcDao.deleteByID(id);
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return true;
    }

    public User getUser(int id) {
        User user = null;
        try {
            user = userJdbcDao.selectByID(id);
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> list = null;
        try {
            list = userJdbcDao.selectAll();
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return list;
    }

    public boolean changeUserRole(User user, Role role) {
        user.setRole(role);
        try {
            userJdbcDao.update(user);
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return true;
    }

    public boolean changeUserBanStatus(User user, boolean banStatus) {
        user.setBanStatus(banStatus);
        try {
            userJdbcDao.update(user);
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return true;
    }

    public boolean changeUserActivatedStatus(User user, boolean activatedStatus) {
        user.setActivated(activatedStatus);
        try {
            userJdbcDao.update(user);
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return true;
    }

    public List<Advertisement> getMyAdvertisement(User user) throws SQLException, IOException {
        return userJdbcDao.selectAllAdvertisementByUserId(user.getId());
    }

    public boolean isUserAuthorized(String email, String password) {
        boolean f = false;
        try {
            f = userJdbcDao.isUserAuthorizedSuccessfully(email, hashPassword(password));
        } catch (SQLException e) {
            logger.error("Unexpected error", e);
        }
        return f;
    }

    public boolean isUserBanned(String email) {
        boolean f = false;
        try {
            f = userJdbcDao.isUserBanned(email);
        } catch (SQLException e) {
            logger.error("Unexpected error", e);
        }
        return f;
    }

    public boolean isUserRegistratedCorrectly(int id, String token) {
        boolean f = false;
        try {
            f = userJdbcDao.isUserRegistratedCorrectly(id, token);
        } catch (SQLException e) {
            logger.error("Unexpected error", e);
        }
        return f;
    }

    public boolean isUserActivated(String email) {
        boolean f = false;
        try {
            f = userJdbcDao.isUserActivated(email);
        } catch (SQLException e) {
            logger.error("Unexpected error", e);
        }
        return f;
    }

    public boolean isUserExist(String email) {
        boolean f = false;
        try {
            f = userJdbcDao.isUserExistInDatabase(email);
        } catch (SQLException | IOException e) {
            logger.error("Unexpected error", e);
        }
        return f;
    }

    public boolean createTable() {
        boolean f = false;
        try {
            f = userJdbcDao.createTable();
        } catch (IOException e) {
            logger.error("Unexpected error", e);
        }
        return f;
    }

    private String hashPassword(String plainTextPassword) {
        return Base64.getEncoder().encodeToString(plainTextPassword.getBytes());
    }
}
