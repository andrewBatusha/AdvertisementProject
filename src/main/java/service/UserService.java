package service;

import DAO.IUserDao;
import enums.Role;
import model.Advertisement;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private IUserDao<User, Advertisement> userJdbcDao;

    public UserService(IUserDao<User, Advertisement> userJdbcDao) {
        this.userJdbcDao = userJdbcDao;
    }

    public boolean addUser(User user) throws SQLException, IOException {
        userJdbcDao.insert(user);
        return true;
    }

    public boolean deleteUser(int id) throws SQLException, IOException {
        userJdbcDao.deleteByID(id);
        return true;
    }

    public User getUser(int id) throws SQLException, IOException {
        return userJdbcDao.selectByID(id);
    }

    public List<User> getAllUsers() throws SQLException, IOException {
        return userJdbcDao.selectAll();
    }

    public boolean changeUserRole(User user, Role role) throws SQLException, IOException {
        user.setRole(role);
        userJdbcDao.update(user);
        return true;
    }

    public List<Advertisement> getMyAdvertisement(User user) throws SQLException, IOException {
        return userJdbcDao.selectAllAdvertisementByUserId(user.getId());
    }
}
