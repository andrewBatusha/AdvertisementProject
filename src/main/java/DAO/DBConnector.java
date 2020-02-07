package DAO;

import java.io.IOException;
import java.sql.*;
import java.util.PropertyResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class DBConnector {

    public static Connection connect() throws IOException {
        Connection conn = null;
        Statement stmt = null;
        try {
            PropertyResourceBundle resourceBundle = (PropertyResourceBundle) getBundle("db");
            Class.forName("org.postgresql.Driver");
            String url = resourceBundle.getString("url");
            String user = resourceBundle.getString("user");
            String pass = resourceBundle.getString("password");
            conn = DriverManager.getConnection(url, user, pass);

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("cannot connected to bd");
        }
        return conn;
    }

    //for creating and dropping tables
    public static boolean query(String SQLquery) throws IOException {
        Connection connection = DBConnector.connect();
        boolean hasException = false;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(SQLquery);
        } catch (SQLException e) {
            System.out.println("Something wrong in sql query ");
            hasException = true;
        }
        return !hasException;
    }

    //for update,insert,delete
    public static int protectedQuery(PreparedStatement preparedStatement) {
        int rows = 0;
        try {
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Something wrong in sql query with prepared statement ");
        }
        return rows;
    }

    public static ResultSet databaseProtectedSelect(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }

}

