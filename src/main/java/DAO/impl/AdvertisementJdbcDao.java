package DAO.impl;

import DAO.DBConnector;
import DAO.IAdvertisementDao;
import enums.Status;
import enums.Theme;
import model.Advertisement;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementJdbcDao implements IAdvertisementDao<Advertisement> {
    private DBConnector dbConnector;

    public AdvertisementJdbcDao(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    private static final String DROP_TABLE = "DROP TABLE advertisement;";

    private static final String CREATE_TABLE = "CREATE TABLE advertisement " +
            "(id SERIAL PRIMARY KEY ," +
            "headline VARCHAR(30)," +
            "description VARCHAR (300)," +
            "theme VARCHAR (30)," +
            "phonenumber VARCHAR (30)," +
            "status VARCHAR (30)," +
            "date_published TIMESTAMP," +
            "id_user int," +
            "FOREIGN KEY (id_user) REFERENCES Users (Id));";

    private static final String INSERT = "INSERT INTO advertisement( headline, description, theme, phonenumber, status, id_user, date_published) " +
            "VALUES ( ? , ? , ?, ?, ? , ?, ?);";

    private static final String UPDATE = "UPDATE advertisement " +
            "SET headline = ? , description = ? , " +
            " theme = ?, phonenumber = ?, status = ?, id_user = ?, date_published = ? WHERE id = ?;";

    private static final String DELETE = "DELETE FROM advertisement " +
            "WHERE id = ?;";

    private static final String SELECT_IF_ADVERTISEMENT_EXIST = "SELECT COUNT(*) FROM advertisement WHERE id = ? ;";

    private static final String SELECT_ALL_ADVERTISEMENT = "SELECT * FROM  advertisement ORDER BY id;";

    private static final String SELECT_ADVERTISEMENT_BY_THEME = "SELECT * FROM advertisement WHERE theme = ?;";

    private static final String SELECT_ADVERTISEMENT_BY_ID = "SELECT  headline, description, theme, phonenumber, status, id_user, date_published FROM advertisement WHERE id = ? ";

    public boolean createAdvertisementTable() throws IOException {
        return dbConnector.query(CREATE_TABLE);
    }

    public boolean dropAdvertisementTable() throws IOException {
        return dbConnector.query(DROP_TABLE);
    }


    @Override
    public List<Advertisement> selectAll() throws SQLException, IOException {
        PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(SELECT_ALL_ADVERTISEMENT);
        ResultSet rs = dbConnector.databaseProtectedSelect(preparedStatement);
        List<Advertisement> advertisements = new ArrayList<>();
        while (rs.next()) {
            Advertisement advertisement = new Advertisement();
            advertisement.setIdAdvertisement(rs.getInt("id"));
            advertisement.setHeadline(rs.getString("headline"));
            advertisement.setDescription(rs.getString("description"));
            advertisement.setTheme(Theme.valueOf(rs.getString("theme")));
            advertisement.setNumber(rs.getString("phonenumber"));
            advertisement.setStatus(Status.valueOf(rs.getString("status")));
            advertisement.setIdUser(rs.getInt("id_user"));
            advertisement.setDateOfPublished(rs.getTimestamp("date_published").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            advertisements.add(advertisement);
        }
        return advertisements;
    }

    @Override
    public Advertisement selectByID(int id) throws SQLException, IOException {
        PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(SELECT_ADVERTISEMENT_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet rs = dbConnector.databaseProtectedSelect(preparedStatement);
        Advertisement advertisement = new Advertisement();
        if (rs.next()) {
            advertisement.setIdAdvertisement(id);
            advertisement.setHeadline(rs.getString("headline"));
            advertisement.setDescription(rs.getString("description"));
            advertisement.setTheme(Theme.valueOf(rs.getString("theme")));
            advertisement.setNumber(rs.getString("phonenumber"));
            advertisement.setStatus(Status.valueOf(rs.getString("status")));
            advertisement.setIdUser(rs.getInt("id_user"));
            advertisement.setDateOfPublished(rs.getTimestamp("date_published").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }
        return advertisement;
    }

    @Override
    public List<Advertisement> selectAllByTheme(Theme advertisementTheme) throws SQLException, IOException {
        PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(SELECT_ADVERTISEMENT_BY_THEME);
        preparedStatement.setString(1, String.valueOf(advertisementTheme));
        ResultSet rs = dbConnector.databaseProtectedSelect(preparedStatement);
        List<Advertisement> advertisementsByTheme = new ArrayList<>();
        while (rs.next()) {
            Advertisement advertisement = new Advertisement();
            advertisement.setIdAdvertisement(rs.getInt("id"));
            advertisement.setHeadline(rs.getString("headline"));
            advertisement.setDescription(rs.getString("description"));
            advertisement.setTheme(Theme.valueOf(rs.getString("theme")));
            advertisement.setNumber(rs.getString("phonenumber"));
            advertisement.setStatus(Status.valueOf(rs.getString("status")));
            advertisement.setIdUser(rs.getInt("id_user"));
            advertisement.setDateOfPublished(rs.getDate("date_published").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            advertisementsByTheme.add(advertisement);
        }
        return advertisementsByTheme;
    }


    @Override
    public int insert(Advertisement entity) throws SQLException, IOException {
        try (PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(INSERT)) {
            preparedStatement.setString(1, entity.getHeadline());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setString(3, String.valueOf(entity.getTheme()));
            preparedStatement.setString(4, entity.getNumber());
            preparedStatement.setString(5, String.valueOf(entity.getStatus()));
            preparedStatement.setInt(6, entity.getIdUser());
            preparedStatement.setTimestamp(7, (Timestamp.valueOf(entity.getDateOfPublished())));
            return dbConnector.protectedQuery(preparedStatement);
        }
    }

    @Override
    public Advertisement deleteByID(int id) throws SQLException, IOException {
        Advertisement adv = selectByID(id);
        PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(DELETE);
        preparedStatement.setInt(1, id);
        dbConnector.protectedQuery(preparedStatement);
        return adv;
    }

    @Override
    public int update(Advertisement entity) throws SQLException, IOException {
        try (PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getHeadline());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setString(3, String.valueOf(entity.getTheme()));
            preparedStatement.setString(4, entity.getNumber());
            preparedStatement.setString(5, String.valueOf(entity.getStatus()));
            preparedStatement.setInt(6, entity.getIdUser());
            preparedStatement.setDate(7, java.sql.Date.valueOf(entity.getDateOfPublished().toLocalDate()));
            preparedStatement.setInt(8, entity.getIdAdvertisement());
            return dbConnector.protectedQuery(preparedStatement);
        }
    }
}
