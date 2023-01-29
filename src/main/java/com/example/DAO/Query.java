package com.example.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.DAO.JDBC.connection;
/** Query DOE used for creation/updating/deleting objects by creating a template for Queries to the DB.*/

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

    public static PreparedStatement getPreStmt(String sqlStr) {

        try {
            PreparedStatement ps = connection.prepareStatement(sqlStr);
            return ps;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void preparedStmt(PreparedStatement ps) {

        try {
            int res = ps.executeUpdate();
            if (res == 1) {
                System.out.println("Updated 1");
            } else System.out.println("None updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
