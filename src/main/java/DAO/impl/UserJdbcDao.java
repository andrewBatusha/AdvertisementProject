package DAO.impl;

import DAO.DBConnector;
import DAO.IUserDao;
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
    private DBConnector dbConnector;

    public UserJdbcDao(DBConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    private static final String DROP_TABLE = "DROP TABLE users;";

    private static final String CREATE_TABLE = "CREATE TABLE users " +
            "(id SERIAL PRIMARY KEY ," +
            "firstname VARCHAR(20)," +
            "lastname VARCHAR (30)," +
            "email VARCHAR (30)," +
            "password VARCHAR (30)," +
            "role VARCHAR (30)," +
            "banstatus Int;)";

    private static final String INSERT = "INSERT INTO users(firstname, lastname, email, password, role, banstatus) " +
            "VALUES ( ? , ? , ?, ?, ?, ?);";

    private static final String UPDATE = "UPDATE users " +
            "SET firstname = ? , lastname = ? , " +
            " email = ?, password = ?, role = ?, banstatus = ? WHERE id = ?;";

    private static final String DELETE = "DELETE FROM users " +
            "WHERE id = ?;";

    private static final String SELECT_ALL_USERS = "SELECT * FROM  users ORDER BY id;";

    private static final String SELECT_IF_USER_AUTHORIZED_SUCCESSFULLY = "SELECT COUNT(*) FROM users WHERE email = ? and password = ?;";

    private static final String SELECT_IF_USER_EXIST = "SELECT COUNT(*) FROM users WHERE email = ?";

    private static final String SELECT_IF_USER_Banned = "SELECT banstatus FROM users WHERE email = ?";

    private static final String SELECT_User_ID_BY_ADVETISEMENT = "SELECT id_user FROM advertisement WHERE id = ?";

    private static final String SELECT_ALL_USERS_ADVERTISEMENT = "SELECT  Advertisement.id, headline, description, theme, phonenumber, status, id_user" +
            " FROM Advertisement " +
            "Where id_user = ?;";


    private static final String SELECT_USER_BY_ID = "SELECT firstname,lastname, email, password, role, banstatus FROM users WHERE id = ? ";

    private static final String SELECT_USER_BY_EMAIL = "SELECT id, firstname,lastname, password, role, banstatus FROM users WHERE email = ?";
    public boolean createUsersTable() throws IOException {
        return dbConnector.query(CREATE_TABLE);
    }

    public boolean dropUsersTable() throws IOException {
        return dbConnector.query(DROP_TABLE);
    }

    @Override
    public List<User> selectAll() throws SQLException, IOException {
        PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(SELECT_ALL_USERS);
        ResultSet rs = dbConnector.databaseProtectedSelect(preparedStatement);
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("firstname"));
            user.setSurname(rs.getString("lastname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(Role.valueOf(rs.getString("role")));
            user.setBanStatus(rs.getInt("banstatus") > 0);
            users.add(user);
        }
        return users;
    }


    @Override
    public User selectByID(int id) throws SQLException, IOException {
        PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(SELECT_USER_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet rs = dbConnector.databaseProtectedSelect(preparedStatement);
        User user = new User();
        if(rs.next()) {
            user.setId(id);
            user.setName(rs.getString("firstname"));
            user.setSurname(rs.getString("lastname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(Role.valueOf(rs.getString("role")));
            user.setBanStatus(rs.getInt("banstatus") > 0);
        }
        return user;
    }

    @Override
    public User selectByEmail(String email) throws SQLException, IOException {
        PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(SELECT_USER_BY_EMAIL);
        preparedStatement.setString(1, email);
        ResultSet rs = dbConnector.databaseProtectedSelect(preparedStatement);
        User user = new User();
        if(rs.next()) {
            user.setId(rs.getInt("id"));
            user.setEmail(email);
            user.setName(rs.getString("firstname"));
            user.setSurname(rs.getString("lastname"));
            user.setPassword(rs.getString("password"));
            user.setRole(Role.valueOf(rs.getString("role")));
            user.setBanStatus(rs.getInt("banstatus") > 0);
        }
        return user;
    }


    public boolean isUserAuthorizedSuccessfully(String email, String password) throws SQLException {
        PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(SELECT_IF_USER_AUTHORIZED_SUCCESSFULLY);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet rs = dbConnector.databaseProtectedSelect(preparedStatement);
        rs.next();
        return rs.getInt(1) != 0;
    }

    @Override
    public boolean isUserBanned(String email) throws SQLException {
        PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(SELECT_IF_USER_Banned);
        preparedStatement.setString(1, email);
        ResultSet rs = dbConnector.databaseProtectedSelect(preparedStatement);
        rs.next();
        return rs.getInt("banstatus") > 0;
    }

    @Override
    public int getUserByAdvertisement(Advertisement adv) throws SQLException {
        PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(SELECT_User_ID_BY_ADVETISEMENT);
        preparedStatement.setInt(1, adv.getIdAdvertisement());
        ResultSet rs = dbConnector.databaseProtectedSelect(preparedStatement);
        rs.next();
        return rs.getInt("id_user");
    }

    @Override
    public boolean isUserExistInDatabase(String email) throws SQLException, IOException {
        PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(SELECT_IF_USER_EXIST);
        preparedStatement.setString(1, email);
        ResultSet rs = dbConnector.databaseProtectedSelect(preparedStatement);
        rs.next();
        return rs.getInt(1) != 0;
    }


    @Override
    public int insert(User user) throws SQLException, IOException {
        try (PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(INSERT)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.setInt(6, user.isBanStatus() ? 1 : 0);
            return dbConnector.protectedQuery(preparedStatement);
        }
    }

    @Override
    public int update(User user) throws SQLException, IOException {
        try (PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(UPDATE)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.setInt(6, user.isBanStatus() ? 1 : 0);
            preparedStatement.setInt(7, user.getId());
            return dbConnector.protectedQuery(preparedStatement);
        }
    }

    @Override
    public User deleteByID(int id) throws SQLException, IOException {
        User user = selectByID(id);
        PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(DELETE);
        preparedStatement.setInt(1, id);
        dbConnector.protectedQuery(preparedStatement);
        return user;
    }

    @Override
    public List<Advertisement> selectAllAdvertisementByUserId(int id) throws SQLException, IOException {
        PreparedStatement preparedStatement = dbConnector.connect().prepareStatement(SELECT_ALL_USERS_ADVERTISEMENT);
        preparedStatement.setInt(1, id);
        ResultSet rs = dbConnector.databaseProtectedSelect(preparedStatement);
        List<Advertisement> usersAdvertisement = new ArrayList<>();
        while (rs.next()) {
            Advertisement advertisement = new Advertisement();
            advertisement.setIdAdvertisement(id);
            advertisement.setHeadline(rs.getString("headline"));
            advertisement.setDescription(rs.getString("description"));
            advertisement.setTheme(Theme.valueOf(rs.getString("theme")));
            advertisement.setNumber(rs.getString("phonenumber"));
            advertisement.setStatus(Status.valueOf(rs.getString("status")));
            advertisement.setIdUser(rs.getInt("id_user"));
            usersAdvertisement.add(advertisement);
        }
        return usersAdvertisement;
    }
}
