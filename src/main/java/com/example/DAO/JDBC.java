package com.example.DAO;

import java.sql.*;

/**
 * JDBC Helper Class.
 */
public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

//Opens DB Connection
    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
//Close DB Connection
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
//User Login Verification Method. Returns a boolean for flag on caller controller.
//    public static boolean loginVerification(String userName, String userPass) throws SQLException {
//        final String SELECT_QUERY = "SELECT * FROM client_schedule.users WHERE user_name = ? AND password = ?";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
//            preparedStatement.setString(1, userName);
//            preparedStatement.setString(2, userPass);
//
//            System.out.println(preparedStatement);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return true;
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//
//        return false;
//    }
}
