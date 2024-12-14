package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {

    private static final Logger logger = Logger.getLogger(DBConnect.class.getName());

    // Database credentials and URL (externalize these in a properties file or environment variables)
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:oracledb";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "hpsystem";

    private DBConnect() {
        // Private constructor to prevent instantiation
    }

    // Method to create a new connection for every request
    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.info("Database connected successfully");
            return conn;
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Oracle JDBC Driver not found", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection failed", e);
        }
        return null;
    }

    // Utility method to close resources safely
    public static void closeResource(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Failed to close resource", e);
            }
        }
    }
}
