//package amzalDBConnect;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBconnect {
//    private static final String URL = "jdbc:mysql://localhost:3306/musicstore";
//    private static final String USER = "root";
//    private static final String PASSWORD = "";
//
//    public static Connection getConnection() {
//        Connection conn = null;
//        try {
//            // Load the JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Establish the connection
//            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Database connected successfully!");
//        } catch (ClassNotFoundException e) {
//            System.out.println("MySQL JDBC Driver not found.");
//            e.printStackTrace();
//        } catch (SQLException e) {
//            System.out.println("Connection failed.");
//            e.printStackTrace();
//        }
//        return conn;
//    }
//}

package amzalDBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
    private static final String URL = "jdbc:mysql://localhost:3306/musicstore";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Prevent instantiation of this class
    private DBconnect() {}

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

