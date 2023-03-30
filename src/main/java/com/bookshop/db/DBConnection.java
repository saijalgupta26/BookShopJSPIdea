package com.bookshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saijalgupta", "root", "root");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("inside getConnection catch");
        }
        return connection;
    }
}
