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

public class UserJdbcDao implements JdbcBaseDao<User> {
    private static final String DROP_TABLE = "DROP TABLE users;";

    private static final String CREATE_TABLE = "CREATE TABLE users " +
            "(id SERIAL PRIMARY KEY ," +
            "firstname VARCHAR(20)," +
            "lastname VARCHAR (30)," +
            "email VARCHAR (30)," +
            "password VARCHAR (30)," +
            "role VARCHAR (30)," +
            "login VARCHAR (30);";

    private static final String INSERT = "INSERT INTO users(id, firstname, lastname, email, password, role, login) " +
            "VALUES ( ? , ? , ?, ?, ? , ?, ?);";

    private static final String UPDATE = "UPDATE users " +
            "SET firstname = ? , lastname = ? , " +
            " email = ?, password = ?, role = ?, login = ? WHERE id = ?;";

    private static final String DELETE = "DELETE FROM users " +
            "WHERE id = ?;";

    private static final String SELECT_ALL_USERS = "SELECT * FROM  users ORDER BY id;";

    private static final String SELECT_USER_BY_LOGIN = "SELECT COUNT(*) FROM users WHERE email = ? AND login = ? ;";
    // DO LATER
    private static final String SELECT_ALL_USERS_ADVERTISEMENT = "SELECT  Advertisement.id, headline, description, theme, email, phonenumber, status, visibility" +
            " FROM coherence, Advertisement " +
            "Where user.id = ?;";

    private static final String SELECT_USER_BY_ID = "SELECT firstname,lastname, email, password, role, login FROM users WHERE id = ? ";




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
            int ID = rs.getInt("id");
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
            String email = rs.getString("email");
            String password = rs.getString("password");
            Role role = Role.valueOf(rs.getString("role"));
            String login = rs.getString("login");
            users.add(new User(ID, firstName,lastName, email, password, role, login));
        }
        return users;
    }


    @Override
    public User selectByID(int id) throws SQLException, IOException {
        PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(SELECT_USER_BY_ID);
        preparedStatement.setInt(1,id);
        ResultSet rs = databaseProtectedSelect(preparedStatement);
        rs.next();
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
            String email = rs.getString("email");
            String password = rs.getString("password");
            Role role =  Role.valueOf(rs.getString("role"));
            String login = rs.getString("login");
            return new User(id, firstName, lastName, email, password, role, login);
    }

    public boolean isEntityExistInDatabase(User user) throws SQLException, IOException {
        PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(SELECT_USER_BY_LOGIN);
        preparedStatement.setString(1,user.getEmail());
        preparedStatement.setString(2,user.getLogin());
        ResultSet rs = databaseProtectedSelect(preparedStatement);
        rs.next();
        return rs.getInt(1) != 0;
    }


    @Override
    public int insert(User user) throws SQLException, IOException {
        try(PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(INSERT)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.setString(6, user.getLogin());
            return protectedQuery(preparedStatement);
        }
    }

    public int update(User user) throws SQLException, IOException {
        try(PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(UPDATE)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.setString(6, String.valueOf(user.getLogin()));
            return protectedQuery(preparedStatement);
        }
    }

    @Override
    public int deleteByID(int id) throws SQLException, IOException {
        PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(DELETE);
        preparedStatement.setInt(1,id);
        return protectedQuery(preparedStatement);
    }

    public List<Advertisement> selectUsersAdvertisement() throws SQLException, IOException {
        PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(SELECT_ALL_USERS_ADVERTISEMENT);
        ResultSet rs = databaseProtectedSelect(preparedStatement);
        List<Advertisement> usersAdvertisement = new ArrayList<>();
        while (rs.next()) {
            int ID = rs.getInt("id");
            String headLine = rs.getString("headline");
            String description = rs.getString("description");
            Theme theme = Theme.valueOf(rs.getString("email"));
            String email = rs.getString("email");
            String phone = rs.getString("phonenumber");
            Status status = Status.valueOf(rs.getString("status"));
            boolean visibility = rs.getInt("visibility") > 0;
            usersAdvertisement.add(new Advertisement(ID, headLine,description, email, phone,theme,status, visibility));
        }
        return usersAdvertisement;
    }

}
