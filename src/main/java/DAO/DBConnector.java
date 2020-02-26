package DAO;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.PropertyResourceBundle;

import static java.util.ResourceBundle.getBundle;

public abstract class DBConnector {
    public DBConnector(String name) {
        this.name = name;
    }

    private String name;

    final static Logger logger = Logger.getLogger(DBConnector.class);

    public Connection connect() {
        Connection conn = null;
        try {
            PropertyResourceBundle resourceBundle = (PropertyResourceBundle) getBundle(name);
            Class.forName(resourceBundle.getString("driver"));
            String url = resourceBundle.getString("url");
            String user = resourceBundle.getString("user");
            String pass = resourceBundle.getString("password");
            conn = DriverManager.getConnection(url, user, pass);

        } catch (SQLException | ClassNotFoundException ex) {
            logger.error("cannot connected to bd",ex);
        }
        return conn;
    }

    //for creating and dropping tables
    public boolean query(String SQLquery)  {
        Connection connection = connect();
        boolean hasException = false;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(SQLquery);
        } catch (SQLException e) {
            logger.error("Something wrong in sql query ");
            hasException = true;
        }
        return !hasException;
    }

    //for update,insert,delete
    public int protectedQuery(PreparedStatement preparedStatement) {
        int rows = 0;
        try {
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Something wrong in sql query with prepared statement ");
        }
        return rows;
    }

    public ResultSet databaseProtectedSelect(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }
}
