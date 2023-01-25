package com.example.DAO;

import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.DAO.JDBC.connection;

public class Query {
    private static String query;
    private static Statement stmt;
    private static ResultSet result;

    public static void makeQuery(String q) {
        query = q;
        try{
            stmt = connection.createStatement();
            //Determines which type of Query
            if (query.toLowerCase().startsWith("select")) {
                result = stmt.executeQuery(q);
            }
            if (query.toLowerCase().startsWith("delete") || query.toLowerCase().startsWith("insert") || query.toLowerCase().startsWith("update")) {
                stmt.executeUpdate(q);
            }
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
    public static ResultSet getResult() {return result;}
}
