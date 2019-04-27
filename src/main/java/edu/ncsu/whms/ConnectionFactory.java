package edu.ncsu.whms;


// Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * JDBC connection factory.
 */
public class ConnectionFactory {

    // Connection url and credentials
    private static String jdbcUrl;
    private static String username;
    private static String password;

    /**
     * Initiate a new database connection.
     *
     * @return Connection.
     */
    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(jdbcUrl, username, password);

        }
        catch (SQLException e) {

            System.out.println(e.getMessage());
            System.exit(1);
            return null;

        }

    }

    /**
     * Set JDBC connection url.
     *
     * @param jdbcUrl JDBC url value.
     */
    public static void setJdbcUrl(String jdbcUrl) { ConnectionFactory.jdbcUrl = jdbcUrl; }

    /**
     * Set connection username.
     *
     * @param username Username value.
     */
    public static void setUsername(String username) { ConnectionFactory.username = username; }

    /**
     * Set connection password.
     *
     * @param password Password value.
     */
    public static void setPassword(String password) { ConnectionFactory.password = password; }

}
