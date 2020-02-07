package DAO;

import enums.Role;
import enums.Status;
import enums.Theme;
import model.Advertisement;
import model.User;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DAO.DBConnector.*;

public class UserJdbcDao implements IUserDao<User, Advertisement> {
    private static final String DROP_TABLE = "DROP TABLE users;";

    private static final String CREATE_TABLE = "CREATE TABLE users " +
            "(id SERIAL PRIMARY KEY ," +
            "firstname VARCHAR(20)," +
            "lastname VARCHAR (30)," +
            "email VARCHAR (30)," +
            "password VARCHAR (30)," +
            "role VARCHAR (30)," +
            "login VARCHAR (30);";

    private static final String INSERT = "INSERT INTO users(firstname, lastname, email, password, role, login) " +
            "VALUES ( ? , ? , ?, ?, ? , ?);";

    private static final String UPDATE = "UPDATE users " +
            "SET firstname = ? , lastname = ? , " +
            " email = ?, password = ?, role = ?, login = ? WHERE id = ?;";

    private static final String DELETE = "DELETE FROM users " +
            "WHERE id = ?;";

    private static final String SELECT_ALL_USERS = "SELECT * FROM  users ORDER BY id;";

    private static final String SELECT_IF_USER_EXIST = "SELECT COUNT(*) FROM users WHERE id = ? ;";

    private static final String SELECT_ALL_USERS_ADVERTISEMENT = "SELECT  Advertisement.id, headline, description, theme, email, phonenumber, status, visibility" +
            " FROM Advertisement " +
            "Where id_user = ?;";

    private static final String SELECT_USER_BY_ID = "SELECT id, firstname,lastname, email, password, role, login FROM users WHERE id = ? ";


    public static boolean createUsersTable() throws IOException {
        return query(CREATE_TABLE);
    }

    public static boolean dropUsersTable() throws IOException {
        return query(DROP_TABLE);
    }

    @Override
    public List<User> selectAll() throws SQLException, IOException {
        PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(SELECT_ALL_USERS);
        ResultSet rs = databaseProtectedSelect(preparedStatement);
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("firstname"));
            user.setSurname(rs.getString("lastname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(Role.valueOf(rs.getString("role")));
            user.setLogin(rs.getString("login"));
            users.add(user);
        }
        return users;
    }


    @Override
    public User selectByID(int id) throws SQLException, IOException {
        PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(SELECT_USER_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet rs = databaseProtectedSelect(preparedStatement);
        rs.next();
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("firstname"));
        user.setSurname(rs.getString("lastname"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setLogin(rs.getString("login"));
        return user;
    }

    @Override
    public boolean isEntityExistInDatabase(int id) throws SQLException, IOException {
        PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(SELECT_IF_USER_EXIST);
        preparedStatement.setInt(1, id);
        ResultSet rs = databaseProtectedSelect(preparedStatement);
        rs.next();
        return rs.getInt(1) != 0;
    }


    @Override
    public int insert(User user) throws SQLException, IOException {
        try (PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(INSERT)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.setString(6, user.getLogin());
            return protectedQuery(preparedStatement);
        }
    }

    @Override
    public int update(User user) throws SQLException, IOException {
        try (PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(UPDATE)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.setString(6, String.valueOf(user.getLogin()));
            preparedStatement.setInt(7, user.getId());
            return protectedQuery(preparedStatement);
        }
    }

    @Override
    public User deleteByID(int id) throws SQLException, IOException {
        User user = selectByID(id);
        PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(DELETE);
        preparedStatement.setInt(1, id);
        protectedQuery(preparedStatement);
        return user;
    }

    @Override
    public List<Advertisement> selectAllAdvertisementByUserId(int id) throws SQLException, IOException {
        PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(SELECT_ALL_USERS_ADVERTISEMENT);
        preparedStatement.setInt(1, id);
        ResultSet rs = databaseProtectedSelect(preparedStatement);
        List<Advertisement> usersAdvertisement = new ArrayList<>();
        while (rs.next()) {
            Advertisement advertisement = new Advertisement();
            advertisement.setIdAdvertisement(id);
            advertisement.setHeadline(rs.getString("headline"));
            advertisement.setDescription(rs.getString("description"));
            advertisement.setTheme(Theme.valueOf(rs.getString("theme")));
            advertisement.setMail(rs.getString("email"));
            advertisement.setNumber(rs.getString("phonenumber"));
            advertisement.setStatus(Status.valueOf(rs.getString("status")));
            advertisement.setVisibility(rs.getInt("visibility") > 0);
            advertisement.setIdUser(rs.getInt("id_user"));
            usersAdvertisement.add(advertisement);
        }
        return usersAdvertisement;
    }
}
