package DAO;


import model.Advertisement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IUserDao<T, V> extends JdbcBaseDao<T> {
    List<V> selectAllAdvertisementByUserId(int id) throws SQLException, IOException;

    T selectByEmail(String email) throws SQLException, IOException;
    boolean isUserExistInDatabase(String email) throws SQLException, IOException;
    boolean isUserAuthorizedSuccessfully(String email, String password) throws SQLException;
    boolean isUserBanned(String email) throws SQLException;
    int getUserByAdvertisement(Advertisement adv) throws SQLException;
    boolean isUserActivated(String email) throws SQLException;
    boolean isUserRegistratedCorrectly(int id, String token) throws SQLException;
}
