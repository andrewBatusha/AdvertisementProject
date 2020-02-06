package DAO;

import enums.Status;
import enums.Theme;
import model.Advertisement;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DAO.DBConnector.databaseProtectedSelect;
import static DAO.DBConnector.query;

public class AdvertisementJdbcDao implements JdbcBaseDao<Advertisement>{

    private static final String DROP_TABLE = "DROP TABLE advertisement;";

    private static final String CREATE_TABLE = "CREATE TABLE advertisement " +
            "(id SERIAL PRIMARY KEY ," +
            "headline VARCHAR(30)," +
            "description VARCHAR (300)," +
            "theme VARCHAR (30)," +
            "email VARCHAR (30)," +
            "phonenumber VARCHAR (30)," +
            "status VARCHAR (30)," +
            "visibility INT;";

    private static final String INSERT = "INSERT INTO advertisement(id, headline, description, theme, email, phonenumber, status, visibility) " +
            "VALUES ( ? , ? , ?, ?, ? , ?, ?, ?);";

    private static final String UPDATE = "UPDATE advertisement " +
            "SET headline = ? , description = ? , " +
            " theme = ?, email = ?, phonenumber = ?, status = ?, visibility = ? WHERE id = ?;";

    private static final String DELETE = "DELETE FROM advertisement " +
            "WHERE id = ?;";

    private static final String SELECT_ALL_ADVERTISEMENT = "SELECT * FROM  advertisement ORDER BY id;";

    private static final String SELECT_USER_BY_THEME = "SELECT * FROM advertisement WHERE theme = ?;";

    private static final String SELECT_ADVERTISEMENT_BY_ID = "SELECT  headline, description, theme, email, phonenumber, status, visibility FROM advertisement WHERE id = ? ";

    public static boolean createUsersTable() throws IOException {
        return query(CREATE_TABLE);
    }

    public static boolean dropUsersTable() throws IOException {
        return query(DROP_TABLE);
    }


    @Override
    public List<Advertisement> selectAll() throws SQLException, IOException {
        PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(SELECT_ALL_ADVERTISEMENT);
        ResultSet rs = databaseProtectedSelect(preparedStatement);
        List<Advertisement> users = new ArrayList<>();
        while (rs.next()) {
            int ID = rs.getInt("id");
            String headline = rs.getString("headline");
            String description = rs.getString("description");
            Theme theme = Theme.valueOf(rs.getString("theme"));
            String email = rs.getString("email");
            String phonenumber = rs.getString("phonenumber");
            Status status = Status.valueOf(rs.getString("status"));
            boolean visibility = rs.getInt("visibility") > 0;
            users.add(new Advertisement(ID, headline, description, email, phonenumber, theme, status, visibility));
        }
        return users;
    }

    @Override
    public Advertisement selectByID(int id) throws SQLException, IOException {
        return null;
    }

    @Override
    public boolean isEntityExistInDatabase(Advertisement entity) throws SQLException, IOException {
        return false;
    }

    @Override
    public int insert(Advertisement entity) throws SQLException, IOException {
        return 0;
    }

    @Override
    public int deleteByID(int id) throws SQLException, IOException {
        return 0;
    }
}
