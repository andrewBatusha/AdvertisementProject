package DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface JdbcBaseDao<T> {
    List<T> selectAll() throws SQLException, IOException;

    T selectByID(int id) throws SQLException, IOException;

    int insert(T entity) throws SQLException, IOException;

    int deleteByID(int id) throws SQLException, IOException;
}