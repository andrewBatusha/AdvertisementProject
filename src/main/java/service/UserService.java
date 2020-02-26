package service;

import DAO.IUserDao;
import enums.Role;
import model.Advertisement;
import model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private IUserDao<User, Advertisement> userJdbcDao;

    public UserService(IUserDao<User, Advertisement> userJdbcDao) {
        this.userJdbcDao = userJdbcDao;
    }

    public User getUserByEmail(String email) {
        User user = null;
        try {
            user = userJdbcDao.selectByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int getUserIdByAdv(Advertisement adv) {
        int id = 0;
        try {
            id = userJdbcDao.getUserByAdvertisement(adv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean addUser(User user) throws SQLException, IOException {
        userJdbcDao.insert(user);
        return true;
    }

    public boolean deleteUser(int id) throws SQLException, IOException {
        userJdbcDao.deleteByID(id);
        return true;
    }

    public User getUser(int id) {
        User user = null;
        try {
            user = userJdbcDao.selectByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;

    }

    public List<User> getAllUsers() {
        List<User> list = null;
        try {
            list = userJdbcDao.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean changeUserRole(User user, Role role) {
        user.setRole(role);
        try {
            userJdbcDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean changeUserBanStatus(User user, boolean banStatus) {
        user.setBanStatus(banStatus);
        try {
            userJdbcDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Advertisement> getMyAdvertisement(User user) throws SQLException, IOException {
        return userJdbcDao.selectAllAdvertisementByUserId(user.getId());
    }

    public boolean isUserAuthorized(String email, String password){
        boolean f = false;
        try {
            f = userJdbcDao.isUserAuthorizedSuccessfully(email,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }
    public boolean isUserBanned(String email){
        boolean f = false;
        try {
            f = userJdbcDao.isUserBanned(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }
    public boolean isUserExist(String email){
        boolean f = false;
        try {
            f = userJdbcDao.isUserExistInDatabase(email);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }
}
