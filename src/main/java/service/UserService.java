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

    public User getUserByEmail(String email) throws IOException, SQLException {
        return userJdbcDao.selectByEmail(email);
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

    public boolean isUserAuthorized(String email, String password){
        boolean f = false;
        try {
            f = userJdbcDao.isUserAuthorizedSuccessfully(email,password);
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
