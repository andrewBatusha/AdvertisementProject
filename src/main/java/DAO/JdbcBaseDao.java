package DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface JdbcBaseDao {
    public <T> List<T> selectAll() throws SQLException, IOException;

    public boolean isExist(String entityName) throws IOException, SQLException;

    public <T> T selectByID(int id) throws SQLException, IOException;

    public <T> int insert(T entity) throws SQLException, IOException;

    public int deleteByID(int id) throws SQLException, IOException;
}