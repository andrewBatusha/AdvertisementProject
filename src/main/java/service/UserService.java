package service;

import DAO.IUserDao;
import DAO.UserJdbcDao;
import enums.Role;
import model.Advertisement;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static IUserDao<User, Advertisement> userJdbcDao = new UserJdbcDao();

    public void addUser(User user) throws SQLException, IOException {
            userJdbcDao.insert(user);
    }

    public void deleteUser(int id) throws SQLException, IOException {
            userJdbcDao.deleteByID(id);
    }

    public User getUser(int id) throws SQLException, IOException{
        return userJdbcDao.selectByID(id);
    }
    public List<User> getAllUsers() throws SQLException, IOException{
        return userJdbcDao.selectAll();
    }

    public void changeUserRole(User user, Role role) throws SQLException, IOException {
        user.setRole(role);
        userJdbcDao.update(user);
    }

    List<Advertisement> getMyAdvertisement(User user) throws SQLException, IOException {
        return userJdbcDao.selectAllAdvertisementByUserId(user.getId());
    }
}
