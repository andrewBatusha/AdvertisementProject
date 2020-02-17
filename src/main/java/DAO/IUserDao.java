package DAO;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IUserDao<T, V> extends JdbcBaseDao<T> {
    List<V> selectAllAdvertisementByUserId(int id) throws SQLException, IOException;

    T selectByEmail(String email) throws SQLException, IOException;
    boolean isEntityExistInDatabase(String email, String password) throws SQLException, IOException;
}
