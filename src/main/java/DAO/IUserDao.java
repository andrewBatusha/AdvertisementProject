package DAO;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IUserDao<T, V> extends JdbcBaseDao<T> {
    List<V> selectAllAdvertisementByUserId(int id) throws SQLException, IOException;
}
