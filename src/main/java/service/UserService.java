package service;

import DAO.UserJdbcDao;
import enums.Role;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

public class UserService {
    private static UserJdbcDao userJdbcDao;

    public void addUser(User user) throws SQLException, IOException{
        if(!userJdbcDao.isEntityExistInDatabase(user)) {
            userJdbcDao.insert(user);
        }
    }

    public void deleteUser(User user) throws SQLException, IOException{
        if(userJdbcDao.isEntityExistInDatabase(user)) {
            userJdbcDao.deleteByID(user.getIdUser());
        }
    }

    void changeRole(User user, Role role) throws SQLException, IOException {
        user.setRole(role);
        userJdbcDao.update(user);
    }


}
