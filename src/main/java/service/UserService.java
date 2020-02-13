package service;

import DAO.IUserDao;
import DAO.impl.UserJdbcDao;
import enums.Role;
import model.Advertisement;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static IUserDao<User, Advertisement> userJdbcDao = new UserJdbcDao();

    public static boolean addUser(User user) throws SQLException, IOException {
            userJdbcDao.insert(user);
            return true;
    }

    public static boolean deleteUser(int id) throws SQLException, IOException {
            userJdbcDao.deleteByID(id);
            return true;
    }

    public static User getUser(int id) throws SQLException, IOException{
        return userJdbcDao.selectByID(id);
    }

    public static List<User> getAllUsers() throws SQLException, IOException{
        return userJdbcDao.selectAll();
    }

    public static boolean changeUserRole(User user, Role role) throws SQLException, IOException {
        user.setRole(role);
        userJdbcDao.update(user);
        return true;
    }

    static List<Advertisement> getMyAdvertisement(User user) throws SQLException, IOException {
        return userJdbcDao.selectAllAdvertisementByUserId(user.getId());
    }
}
