package com.apress.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/catalog"; // 1
    static final String USER = "postgres"; // 2
    static final String PASS = "postgres"; // 2
    static final String QUERY = "SELECT id, code, name FROM country"; // 3

    public static void main(String[] args) {
        // Open a connection and close it when finish the execution
        // The use of try/catch in this way autoclose the resources
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {

            // Obtain the information of one row
            while (rs.next()) {
                // Retrieve the data by column
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String name = rs.getString("name");

                System.out.println(String.format("ID: %s, Code: %s, Name: %s", id, code, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
