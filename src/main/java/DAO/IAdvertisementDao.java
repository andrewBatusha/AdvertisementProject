package DAO;

import DAO.JdbcBaseDao;
import enums.Theme;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public interface IAdvertisementDao<T> extends JdbcBaseDao<T> {

    List<T> selectAllByTheme(Theme advertisementTheme) throws SQLException, IOException;

}
