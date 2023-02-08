package com.example.DAO;

import java.sql.*;

/**
 * JDBC Helper Class.
 */
public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "caymenpope.mysql.database.azure.com";
    private static final String databaseName = "client_schedule";
    private static final String url = "jdbc:mysql://caymenpope.mysql.database.azure.com:3306/client_schedule?useSSL=true";
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static String userName = "caymenpope"; // Username
    private static String password = "Tiffanie2022!!"; // Password
    public static Connection connection;  // Connection Interface

    //Opens DB Connection
    public static void openConnection() {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(url, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    //Close DB Connection
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
